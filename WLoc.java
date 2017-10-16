package F28DA_CW1_2;

/**
 * In-file word-location
 */
public class WLoc implements ILocation {
	private String fileName;
	private int lineNumber;
	private String word;

	/**
	 * Creates a new word-location object give a file name, a line number and a word.
	 */
	WLoc(String fileName, int lineNumber, String word) {
		this.fileName = fileName;
		this.lineNumber = lineNumber;
		this.word = word;
	}

	/**
	 * Returns the file name of the word-location.
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Returns the line number of the word-location.
	 */
	public int getLine() {
		return lineNumber;
	}
	
	/**
	 * Returns the word of the word-location.
	 */
	public String getWord() {
		return word;
	}
	
	/**
	 * Defines the default way a location is printed to a string.
	 */
	public String toString() {
		return String.format("%s:%d:%s", this.fileName, this.lineNumber, this.word);
		
	}
	
	/**
	 * Defines word location equality. Two locations are equal if the word the
	 * refer, the file name and the line number are all the same.
	 */
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (o instanceof WLoc) {
			WLoc loc = (WLoc) o;
			boolean sameFileName = this.getFileName().equals(loc.getFileName());
			boolean sameLineNumber = this.getLine() == loc.getLine();
			boolean sameWord = this.getWord().equals(loc.getWord());
			return  sameFileName && sameLineNumber && sameWord;
		} else {
			return false;
		}
	}

}
