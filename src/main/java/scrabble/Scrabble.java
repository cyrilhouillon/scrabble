package scrabble;

import static java.util.stream.Collectors.groupingBy;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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

	public Map<Integer, ? extends Collection<String>> computeHistogram(List<String> playedWords,
			List<String> dictionary) {
		return playedWords.stream().filter(dictionary::contains).collect(groupingBy(this::computeScore));
	}

}
