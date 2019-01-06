package sorts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 合并文件
 *
 * @author APP
 */
public class MergeFiles2 {
	/**
	 * 有序文件列表
	 **/
	public static ArrayList<File> fileList = new ArrayList<>();
	/**
	 * 小顶堆
	 **/
	private static SmallHeap heap = new SmallHeap(101);
	/**
	 * 
	 * file -> file pointer
	 **/
	private static HashMap<File, BufferedReader> readerMap = new HashMap<>();

	/**
	 * 已读完的文件个数
	 */
	private static int emptyFiles = 0;
	private static int fileLine = 100000;

	/**
	 * 创建小文件(如果存在，将其删除）并写入内容
	 *
	 * @param dic
	 */
	public static void create(String dic) throws IOException {
		File file = new File(dic);
		if (!file.exists()) {
			file.mkdirs();
		}
		if (file.isDirectory()) {
			String fileName = dic + "\\test";
			for (int i = 0; i < 100; i++) {
				File f = new File(fileName + i + ".txt");
				if (!f.exists()) {
					f.createNewFile();
					writeFileContent(f.getAbsolutePath(), i);
				} else {
					f.delete();
					f.createNewFile();
					writeFileContent(f.getAbsolutePath(), i);
				}
			}
		}
	}

	/**
	 * 写入内容，每行相差<!--fileLine-->
	 *
	 * @param fileName
	 * @param i
	 */
	public static void writeFileContent(String fileName, long i) {
		File file = new File(fileName);
		if (file.exists()) {
			try (PrintWriter pw = new PrintWriter(new FileOutputStream(file))) {
				for (int j = 0; j < fileLine; j++) {
					pw.write(i + "\r\n");
					i = i + fileLine;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 初始化fileList和fileReaded
	 *
	 * @param dirName
	 * @throws FileNotFoundException
	 */

	public static void init(File file) throws FileNotFoundException {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				if (f.isDirectory()) {
					init(f);
				} else {
					if (f.getName().indexOf("bigFile") < 0) {
						fileList.add(f);
						readerMap.put(f, new BufferedReader(new InputStreamReader(new FileInputStream(f))));
					}
				}
			}
		} else {
			if (file.getName().indexOf("bigFile") < 0) {
				fileList.add(file);
				readerMap.put(file, new BufferedReader(new InputStreamReader(new FileInputStream(file))));
			}
		}
	}

	/**
	 * 新建大文件，如果存在，将其删除
	 * 
	 * @return
	 * @throws IOException
	 */
	public static File createBigFile() throws IOException {
		File bigFile = new File("D:\\data\\mergeFileData\\bigFile.txt");
		if (!bigFile.exists()) {
			bigFile.createNewFile();
		}
		try (FileWriter fileWriter = new FileWriter(bigFile)) {
			fileWriter.write("");
			fileWriter.flush();
		}
		return bigFile;
	}

	/**
	 * 初始化堆
	 * 
	 * @param bigFile
	 * @throws FileNotFoundException
	 */
	public static void initHeap(File bigFile) throws FileNotFoundException {
		for (int i = 0; i < fileList.size(); i++) {
			BufferedReader reader = readerMap.get(fileList.get(i));
			String word;
			try {
				word = reader.readLine();
				if (word != null) {
					heap.insert(new Node(word, fileList.get(i)));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 搬移数据到大文件
	 * 
	 * @param bigFile
	 */
	public static void move(File bigFile) {
		try (PrintWriter pw = new PrintWriter(new FileOutputStream(bigFile))) {
			while (heap.getCount() > 0) {
				Node minNode = heap.deleteFirst();
				pw.write(minNode.getWord() + " " + minNode.getFile() + "\r\n");
				File file = minNode.getFile();
				BufferedReader reader = readerMap.get(file);
				String word = reader.readLine();
				if (word != null && word != "\r\n") {
					Node node = null;
					node = new Node(word, file);
					heap.insert(node);
				}
			}
			heap.print();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		String path = "D:\\data\\mergeFileData";
		create(path);
		init(new File(path));
		File bigFile = createBigFile();
		initHeap(bigFile);
		move(bigFile);
		long end = System.currentTimeMillis();
		System.out.println("spend time:" + (end - start) / 1000 + "s");
	}
}
