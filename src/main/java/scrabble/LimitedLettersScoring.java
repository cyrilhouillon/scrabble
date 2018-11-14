package scrabble;

import java.util.Map;

public class LimitedLettersScoring implements Scoring {

	private Scoring scoring;
	private Map<Letter, Integer> availableLetters;

	public LimitedLettersScoring(Scoring scoring, Map<Letter, Integer> availableLetters) {
		this.scoring = scoring;
		this.availableLetters = availableLetters;
	}

	@Override
	public int scoreForLetter(Letter l) {
		if (availableLetters.get(l) > 0) {
			availableLetters.compute(l, (k, v) -> v - 1);
			return scoring.scoreForLetter(l);
		}
		return 0;
	}

}
