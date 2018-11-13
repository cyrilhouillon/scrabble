package scrabble;

public class Letter {

	private final char c;

	public Letter(char c) {
		this.c = Character.toUpperCase(c);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + c;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Letter other = (Letter) obj;
		if (c != other.c)
			return false;
		return true;
	}

}
