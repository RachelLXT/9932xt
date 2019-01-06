package sorts;
//package sort;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.RandomAccessFile;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 合并文件
// *
// * @author APP
// */
//public class MergeFiles {
//    /**
//     * 有序文件列表
//     **/
//    public static ArrayList<File> fileList = new ArrayList<>();
//    /**
//     * 小顶堆
//     **/
//    private static SmallHeap heap = new SmallHeap(102);
//    /**
//     * file -> file pointer
//     **/
//    private static HashMap<File, Integer> fileReaded = new HashMap<>();
//
//    /**
//     * 已读完的文件个数
//     */
//    private static int emptyFiles = 0;
//
//    /**
//     * 创建文件并写入内容
//     *
//     * @param dic
//     */
//    public static void create(String dic) {
//        File file = new File(dic);
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//        if (file.isDirectory()) {
//            String fileName = dic + "\\test";
//            for (int i = 0; i < 100; i++) {
//                File f = new File(fileName + i + ".txt");
//                if (!f.exists()) {
//                    try {
//                        f.createNewFile();
//                        writeFileContent(f.getAbsolutePath(), i);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }
//
//    /**
//     * 写入内容，每行相差100
//     *
//     * @param fileName
//     * @param i
//     */
//    public static void writeFileContent(String fileName, long i) {
//        File file = new File(fileName);
//        if (file.exists()) {
//            try (PrintWriter pw = new PrintWriter(new FileOutputStream(file))) {
//                for (int j = 0; j < 100000; j++) {
//                    i = i + 100000;
//                    pw.write(i + "\r\n");
//                }
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * 初始化fileList和fileReaded
//     *
//     * @param dirName
//     */
//
//    public static void init(File file) {
//        if (file.isDirectory()) {
//            File[] files = file.listFiles();
//            for (File f : files) {
//                if (f.isDirectory()) {
//                    init(f);
//                } else {
//                    fileList.add(f);
//                    fileReaded.put(f, 0);
//                }
//            }
//        } else {
//            fileList.add(file);
//            fileReaded.put(file, 0);
//        }
//    }
//
//    public static File createBigFile() {
//        File bigFile = new File("E:\\data\\mergeFileData\\bigFile.txt");
//        if (!bigFile.exists()) {
//            try {
//                bigFile.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            bigFile.delete();
//            try {
//                bigFile.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return bigFile;
//    }
//    
//    public static void initHeap(File bigFile) throws FileNotFoundException {
//    	 try (PrintWriter pw = new PrintWriter(new FileOutputStream(bigFile))) {
//             for (File file : fileList) {
//                 try (RandomAccessFile raf = new RandomAccessFile(file.getAbsolutePath(), "rw")) {
//                     raf.seek(fileReaded.get(file));
//                     String word = raf.readLine();
//                     if (word != null) {
//                         fileReaded.put(file, fileReaded.get(file) + word.length() + 2);
//                         heap.insert(word);
//                     }
//                 } catch (IOException e) {
//                     e.printStackTrace();
//                 }
//             }
//         }
//    }
//
//    public static void main(String[] args) throws FileNotFoundException {
//        String path = "E:\\data\\mergeFileData";
//        create(path);
//        init(new File(path));
//        File bigFile = createBigFile();
//        initHeap(bigFile);
//        heap.print();
//        try (PrintWriter pw = new PrintWriter(new FileOutputStream(bigFile))) {
//            while (emptyFiles < 100) {
//                for (File file : fileList) {
//                    try (RandomAccessFile raf = new RandomAccessFile(file.getAbsolutePath(), "rw")) {
//                        raf.seek(fileReaded.get(file));
//                        String word = raf.readLine();
//                        if (word != null && word != "\r\n") {
//                            fileReaded.put(file, fileReaded.get(file) + word.length() + 2);
//                            heap.insert(word);
//                            String minWord = heap.deleteFirst();
//                            pw.write(minWord + "\r\n");
//
//                            // 如果对顶元素来自于file文件中读取的元素，重复读取
//                            // TODO 这里不支持有重复元素
//                            while (minWord.equals(word)) {
//                                System.out.println(minWord);
//                                raf.seek(fileReaded.get(file));
//                                word = raf.readLine();
//                                if (word != null && word != "\r\n") {
//                                    fileReaded.put(file, fileReaded.get(file) + word.length() + 2);
//                                    heap.insert(word);
//                                    minWord = heap.deleteFirst();
//                                    pw.write(minWord + "\r\n");
//                                } else {
//                                    emptyFiles++;
//                                    break;
//                                }
//                            }
//                        } else {
//                            emptyFiles++;
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            heap.print();
//            while (heap.getCount() > 0) {
//                pw.write(heap.deleteFirst() + "\r\n");
//            }
//            heap.print();
//        }
//    }
//}
