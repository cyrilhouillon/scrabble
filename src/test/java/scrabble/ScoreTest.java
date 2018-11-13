package scrabble;

import static java.util.Comparator.comparingInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class ScoreTest {

	Scrabble scrabble = new Scrabble(Scorings.ENGLISH_SCORING);
	List<String> dictionary;
	List<String> playedwords;

	@Test
	void score_should_return_the_sum_of_each_letter_value() {
		String word = "Hello";

		int score = scrabble.computeScore(word);

		assertEquals(8, score);
	}

	@Test
	void verify_the_best_word_played() {

		String bestWord = getPlayedWords().stream()//
				.filter(word -> getDictionary().contains(word))//
				.sorted(comparingInt(scrabble::computeScore).reversed())//
				.findFirst()//
				.get();

		assertEquals("whizzing", bestWord);
		assertEquals(33, scrabble.computeScore(bestWord));
	}

	@Test
	void histogram_should_group_the_words_by_score() {

		Map<Integer, Collection<String>> histogram = scrabble.computeHistogram(getPlayedWords(), getDictionary());

		assertEquals(29, histogram.size());
		assertEquals(1, histogram.get(33).size());
		assertEquals(9, histogram.get(28).size());
		assertEquals(360, histogram.get(16).size());
		assertEquals(1459, histogram.get(8).size());
		assertEquals(26, histogram.get(2).size());
	}

	private List<String> getDictionary() {
		if (dictionary == null) {
			try {
				dictionary = Files.readAllLines(
						Paths.get(this.getClass().getClassLoader().getResource("dictionary.txt").toURI()));
			} catch (IOException | URISyntaxException e) {
				throw new RuntimeException(e);
			}
		}
		return dictionary;
	}

	private List<String> getPlayedWords() {
		if (playedwords == null) {
			try {
				playedwords = Files.readAllLines(
						Paths.get(this.getClass().getClassLoader().getResource("playedwords.txt").toURI()));
			} catch (IOException | URISyntaxException e) {
				throw new RuntimeException(e);
			}
		}
		return playedwords;
	}
}
