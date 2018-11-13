package scrabble;

import java.util.Map;

public class Scrabble {

	private final Map<Letter, Integer> scoreByLetter;

	public Scrabble(Map<Letter, Integer> scoreByLetter) {
		this.scoreByLetter = scoreByLetter;
	}

	public int computeScore(String word) {
		return word.chars().mapToObj(c -> (char) c)//
				.map(Letter::new) //
				.mapToInt(l -> scoreByLetter.getOrDefault(l, 0))//
				.sum();
	}

}
