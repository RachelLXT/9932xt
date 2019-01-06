package sorts;

import java.io.File;

public class Node {
	private String word;
	private File file;

	public Node(String word,File file) {
		this.word = word;
		this.setFile(file);
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
