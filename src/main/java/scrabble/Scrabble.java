package scrabble;

public class Scrabble {

	private final Scoring scoring;

	public Scrabble(Scoring scoring) {
		this.scoring = scoring;
	}

	public int computeScore(String word) {
		return word.chars().mapToObj(c -> (char) c)//
				.map(Letter::new) //
				.mapToInt(scoring::scoreForLetter)//
				.sum();
	}

}
