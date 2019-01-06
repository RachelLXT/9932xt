package sorts;

/***
 * 数据结构 ：堆;数组第一个元素不存储数据 堆中的元素个数：count; count = arr.length-1
 * 
 * @author admin
 *
 */
public class Heap {
	private int[] arr;
	private int count;

	public Heap(int size) {
		this.arr = new int[size];
		this.count = 0;
	}

	/**
	 * 堆排
	 * 
	 * @param arr
	 */
	public static void heapSort(int[] arr) {
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
	public static void buildHeap(int[] arr, int n) {
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
	public static void heapify(int[] arr, int n, int i) {
		while (true) {
			int maxPos = i;
			if (i * 2 <= n && arr[i] < arr[i * 2]) {
				maxPos = i * 2;
			}
			if (i * 2 + 1 <= n && arr[maxPos] < arr[i * 2 + 1]) {
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
	public void insert(int value) {
		if (count == 0) {
			arr[++count] = value;
			return;
		}
		if (count >= arr.length) {
			throw new Error("arrayIndexOutOfRange");
		}
		int n = ++count;
		arr[n] = value;
		while (n / 2 > 0 && arr[n / 2] < arr[n]) {
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
		arr[count] = 0;
		count--;
		heapify(arr, count, 1);
	}

	public static void swap(int[] arr, int n, int m) {
		int tmp = arr[n];
		arr[n] = arr[m];
		arr[m] = tmp;
	}

	public static void main(String[] args) {
		Heap heap = new Heap(15);
		for (int i = 0; i < 10; i++) {
			heap.insert(i);
		}
		heap.print();

		for (int i = 0; i < 10; i++) {
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
		for (int i : arr) {
			System.out.print(i + "  ");
		}
		System.out.println(count);
	}
}
