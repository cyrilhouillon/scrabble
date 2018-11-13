package scrabble;

import static java.util.Comparator.comparingInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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
