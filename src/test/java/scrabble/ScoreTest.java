package scrabble;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ScoreTest {

	Scrabble scrabble = new Scrabble(Scrabble.ENGLISH_SCORING);

	@Test
	void test() {
		String word = "Hello";

		int score = scrabble.computeScore(word);

		assertEquals(8, score);
	}

}
