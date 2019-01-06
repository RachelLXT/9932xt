package sorts;

/***
 * 数据结构 ：堆;数组第一个元素不存储数据 堆中的元素个数：count; count = arr.length-1
 *
 * @author admin
 *
 */
public class BigHeap {
	private String[] arr;
	private int count;

	public BigHeap(int size) {
		this.arr = new String[size];
		this.count = 0;
	}

	/**
	 * 堆排
	 *
	 * @param arr
	 */
	public static void heapSort(String[] arr) {
		buildHeap(arr, arr.length - 1);
		for (int i = arr.length - 1; i > 1; i--) {
			swap(arr, 1, i);
			heapify(arr, i - 1, 1);
		}
	}

	/**
	 * 建堆
	 *
	 * @param arr
	 * @param n
	 */
	public static void buildHeap(String[] arr, int n) {
		for (int i = n / 2; i > 0; i--) {
			heapify(arr, n, i);
		}
	}

	/**
	 * 堆化（从上往下）
	 *
	 * @param arr
	 * @param n
	 * @param i
	 */
	public static void heapify(String[] arr, int n, int i) {
		while (true) {
			int maxPos = i;
			if (i * 2 <= n && arr[i].compareTo(arr[i * 2]) < 0) {
				maxPos = i * 2;
			}
			if (i * 2 + 1 <= n && arr[maxPos].compareTo(arr[i * 2 + 1]) < 0) {
				maxPos = i * 2 + 1;
			}
			if (maxPos == i) {
				break;
			}
			swap(arr, i, maxPos);
			i = maxPos;
		}
	}

	/**
	 * 插入
	 *
	 * @param value
	 */
	public void insert(String value) {
		if (count == 0) {
			arr[++count] = value;
			return;
		}
		if (count >= arr.length) {
			throw new Error("arrayIndexOutOfRange");
		}
		int n = ++count;
		arr[n] = value;
		while (n / 2 > 0 && arr[n / 2].compareTo(arr[n]) < 0) {
			swap(arr, n / 2, n);
			n = n / 2;
		}
	}

	/**
	 * 删除堆顶元素
	 */
	public void deleteFirst() {
		if (count == 0) {
			throw new Error("array is empty");
		}
		arr[1] = arr[count];
		arr[count] = "";
		count--;
		heapify(arr, count, 1);
	}

	public static void swap(String[] arr, int n, int m) {
		String tmp = arr[n];
		arr[n] = arr[m];
		arr[m] = tmp;
	}

	public static void main(String[] args) {
		BigHeap heap = new BigHeap(15);
		for (int i = 1; i < 10; i++) {
			heap.insert(i + "");
		}
		heap.print();

		for (int i = 0; i < 9; i++) {
			heap.deleteFirst();
			heap.print();
		}
//		int[] arr = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
//		heapSort(arr);
//		for (int i : arr) {
//			System.out.print(i);
//		}
//		System.out.println();
	}

	public void print() {
		for (String i : arr) {
			System.out.print(i + "  ");
		}
		System.out.println(count);
	}
}
