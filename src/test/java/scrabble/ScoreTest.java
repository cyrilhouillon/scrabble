package scrabble;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

		Map<Integer, ? extends Collection<String>> histogram = scrabble.computeHistogram(getPlayedWords(),
				getDictionary());

		assertEquals(29, histogram.size());
		assertEquals(1, histogram.get(33).size());
		assertEquals(9, histogram.get(28).size());
		assertEquals(360, histogram.get(16).size());
		assertEquals(1459, histogram.get(8).size());
		assertEquals(26, histogram.get(2).size());
	}

	@Test
	void find_the_words_having_best_3_scores() {

		Map<Integer, ? extends Collection<String>> histogram = scrabble.computeHistogram(getPlayedWords(),
				getDictionary());

		List<Entry<Integer, ? extends Collection<String>>> bestScores = histogram.entrySet().stream()//
				.sorted(comparing((Entry<Integer, ? extends Collection<String>> entry) -> entry.getKey()).reversed())//
				.limit(3)//
				.collect(toList());

		assertEquals(33, bestScores.get(0).getKey().intValue());
		assertEquals(29, bestScores.get(1).getKey().intValue());
		assertEquals(28, bestScores.get(2).getKey().intValue());

		assertTrue(bestScores.get(0).getValue().contains("whizzing"));
		assertTrue(bestScores.get(1).getValue().contains("buzzards"));
		assertTrue(bestScores.get(2).getValue().contains("mazzard"));
		assertTrue(bestScores.get(2).getValue().contains("dazzling"));
		assertTrue(bestScores.get(2).getValue().contains("grizzled"));
		assertTrue(bestScores.get(2).getValue().contains("puzzled"));
		assertTrue(bestScores.get(2).getValue().contains("unmuzzle"));
		assertTrue(bestScores.get(2).getValue().contains("drizzled"));
		assertTrue(bestScores.get(2).getValue().contains("muzzled"));
		assertTrue(bestScores.get(2).getValue().contains("buzzard"));
		assertTrue(bestScores.get(2).getValue().contains("buzzing"));
	}

	@Test
	void only_count_available_letters() {

		assertEquals(26, newLimitedScrabble().computeScore("squeezes"));
		assertEquals(25, newLimitedScrabble().computeScore("quickly"));
		assertEquals(23, newLimitedScrabble().computeScore("whizzing"));
		assertEquals(19, newLimitedScrabble().computeScore("buzzards"));
		assertEquals(7, newLimitedScrabble().computeScore("delated"));

	}

	private Scrabble newLimitedScrabble() {
		return new Scrabble(new LimitedLettersScoring(Scorings.ENGLISH_SCORING, getAvailableLetters()));
	}

	private Map<Letter, Integer> getAvailableLetters() {
		return new HashMap<Letter, Integer>() {
			{
				put(new Letter('A'), 99);
				put(new Letter('B'), 2);
				put(new Letter('C'), 2);
				put(new Letter('D'), 1);
				put(new Letter('E'), 12);
				put(new Letter('F'), 2);
				put(new Letter('G'), 3);
				put(new Letter('H'), 2);
				put(new Letter('I'), 99);
				put(new Letter('J'), 1);
				put(new Letter('K'), 1);
				put(new Letter('L'), 4);
				put(new Letter('M'), 2);
				put(new Letter('N'), 6);
				put(new Letter('O'), 8);
				put(new Letter('P'), 2);
				put(new Letter('Q'), 1);
				put(new Letter('R'), 6);
				put(new Letter('S'), 4);
				put(new Letter('T'), 6);
				put(new Letter('U'), 4);
				put(new Letter('V'), 2);
				put(new Letter('W'), 2);
				put(new Letter('X'), 1);
				put(new Letter('Y'), 2);
				put(new Letter('Z'), 1);
			}
		};
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
