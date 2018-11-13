package scrabble;

import java.util.HashMap;
import java.util.Map;

public class Scrabble {

	public static Map<Letter, Integer> ENGLISH_SCORING = new HashMap<Letter, Integer>() {
		{
			put(new Letter('A'), 1);
			put(new Letter('B'), 3);
			put(new Letter('C'), 3);
			put(new Letter('D'), 2);
			put(new Letter('E'), 1);
			put(new Letter('F'), 4);
			put(new Letter('G'), 2);
			put(new Letter('H'), 4);
			put(new Letter('I'), 1);
			put(new Letter('J'), 8);
			put(new Letter('K'), 5);
			put(new Letter('L'), 1);
			put(new Letter('M'), 3);
			put(new Letter('N'), 1);
			put(new Letter('O'), 1);
			put(new Letter('P'), 3);
			put(new Letter('Q'), 10);
			put(new Letter('R'), 1);
			put(new Letter('S'), 1);
			put(new Letter('T'), 1);
			put(new Letter('U'), 1);
			put(new Letter('V'), 4);
			put(new Letter('W'), 4);
			put(new Letter('X'), 8);
			put(new Letter('Y'), 4);
			put(new Letter('Z'), 10);
		}
	};
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
