package sorts;

import java.io.File;

/***
 * 数据结构 ：堆;数组第一个元素不存储数据 堆中的元素个数：count; count = arr.length-1
 *
 * @author admin
 *
 */
public class SmallHeap {
	private Node[] arr;
	private int count;

	public int getCount() {
		return this.count;
	}

	public SmallHeap(int size) {
		this.arr = new Node[size];
		this.count = 0;
	}

	/**
	 * 堆排
	 *
	 * @param arr
	 */
	public static void heapSort(Node[] arr) {
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
	public static void buildHeap(Node[] arr, int n) {
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
	public static void heapify(Node[] arr, int n, int i) {
		while (true) {
			int maxPos = i;
			if (i * 2 <= n && Long.valueOf(arr[i].getWord()) > Long.valueOf(arr[i * 2].getWord())) {
				maxPos = i * 2;
			}
			if (i * 2 + 1 <= n && Long.valueOf(arr[maxPos].getWord()) > Long.valueOf(arr[i * 2 + 1].getWord())) {
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
	public void insert(Node value) {
		if (count == 0) {
			arr[++count] = value;
			return;
		}
		if (count >= arr.length) {
			throw new Error("arrayIndexOutOfRange");
		}
		int n = ++count;
		arr[n] = value;
		while (n / 2 > 0 && Long.valueOf(arr[n / 2].getWord()) > Long.valueOf(arr[n].getWord())) {
			swap(arr, n / 2, n);
			n = n / 2;
		}
	}

	/**
	 * 删除堆顶元素
	 */
	public Node deleteFirst() {
		if (count == 0) {
			throw new Error("array is empty");
		}
		Node res = arr[1];
		arr[1] = arr[count];
		arr[count] = null;
		count--;
		heapify(arr, count, 1);
		return res;
	}

	public static void swap(Node[] arr, int n, int m) {
		Node tmp = arr[n];
		arr[n] = arr[m];
		arr[m] = tmp;
	}

	public static void main(String[] args) {
		SmallHeap heap = new SmallHeap(15);
		for (int i = 9; i > 0; i--) {
			heap.insert(new Node(i + "",new File("")));
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
		for (Node i : arr) {
			if (i != null)
				System.out.print(i.getWord() + "  ");
		}
		System.out.println(count);
	}
}
