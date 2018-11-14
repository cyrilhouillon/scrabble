package scrabble;

import java.util.Map;

public class UnlimitedScoring implements Scoring {

	private final Map<Letter, Integer> scoring;

	public UnlimitedScoring(Map<Letter, Integer> scoring) {
		this.scoring = scoring;
	}

	@Override
	public int scoreForLetter(Letter l) {
		return scoring.getOrDefault(l, 0);
	}
}
