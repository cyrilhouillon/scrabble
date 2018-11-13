package scrabble;

import java.util.HashMap;
import java.util.Map;

public class Scrabble {

	public static Map<Character, Integer> ENGLISH_SCORING = new HashMap<Character, Integer>() {
		{
			put('A', 1);
			put('B', 3);
			put('C', 3);
			put('D', 2);
			put('E', 1);
			put('F', 4);
			put('G', 2);
			put('H', 4);
			put('I', 1);
			put('J', 8);
			put('K', 5);
			put('L', 1);
			put('M', 3);
			put('N', 1);
			put('O', 1);
			put('P', 3);
			put('Q', 10);
			put('R', 1);
			put('S', 1);
			put('T', 1);
			put('U', 1);
			put('V', 4);
			put('W', 4);
			put('X', 8);
			put('Y', 4);
			put('Z', 10);
		}
	};
	private final Map<Character, Integer> scoreByChar;

	public Scrabble(Map<Character, Integer> scoreByChar) {
		this.scoreByChar = scoreByChar;
	}

	public int computeScore(String word) {
		return word.chars().mapToObj(c -> (char) c)//
				.map(Character::toUpperCase) //
				.mapToInt(c -> scoreByChar.getOrDefault(c, 0))//
				.sum();
	}

}
