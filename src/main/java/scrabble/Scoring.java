package scrabble;

import java.util.Map;

public class Scoring {

	private final Map<Letter, Integer> scoring;

	public Scoring(Map<Letter, Integer> scoring) {
		this.scoring = scoring;
	}

	public int scoreForLetter(Letter l) {
		return scoring.getOrDefault(l, 0);
	}
}
