package sorts;

import java.io.File;

public class Node {
	private String word;
	private static int num = 0;
	private int flag;
	private File file;

	public Node(String word,File file) {
		this.word = word;
		this.setFile(file);
		this.flag = num++;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "Node [word=" + word + ", flag=" + flag + "]";
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
