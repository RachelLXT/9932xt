//package sorts;
//
//public class Sort {
//
//    public void quickSort(int[] arr) {
//        quickSort(arr, 0, arr.length - 1);
//    }
//
//    public void quickSort(int[] arr, int p, int r) {
//        if (p >= r)
//            return;
//        int q = partition(arr, p, r);
//        quickSort(arr, p, q - 1);
//        quickSort(arr, q + 1, r);
//    }
//
//    public int partition(int[] arr, int p, int r) {
//        int pivot = arr[r];
//        int i = p;
//        for (int j = p; j < r; j++) {
//            if (arr[j] < pivot) {
//                swap(arr, i, j);
//                i++;
//            }
//        }
//        swap(arr, i, r);
//        return i;
//    }
//
//    public void swap(int[] arr, int i, int j) {
//        int temp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = temp;
//    }
//
//    public void mergeSort(int[] arr) {
//        mergeSort(arr, 0, arr.length - 1);
//    }
//
//    public void mergeSort(int[] arr, int p, int r) {
//        if (p >= r)
//            return;
//        int q = (p + r) / 2;
//        mergeSort(arr, p, q);
//        mergeSort(arr, q + 1, r);
//        merge(arr, p, q, r);
//    }
//
//    //    // 利用哨兵，传入的后两个数组各在尾部多放一个和原有最后值相同的值。
////    public void merge(int[] arr, int p, int q, int r) {
////        int i = p, j = q + 1, k = 0;
////        arr[p + 1] = arr[p];
////        if (r + 1 < arr.length)
////            arr[r + 1] = arr[r];
////        int[] temp = new int[r - p + 1];
////        while (i <= q || j <= r) {
////            if (i <= q && arr[i] <= arr[j]) {
////                temp[k++] = arr[i++];
////            } else {
////                temp[k++] = arr[j++];
////            }
////        }
////        for (int n = 0; n <= r - p; n++) {
////            arr[p + n] = temp[n];
////        }
////    }
//    public void merge(int[] arr, int p, int q, int r) {
//        int i = p, j = q + 1, k = 0;
//        int[] temp = new int[r - p + 1];
//        while (i <= q && j <= r) {
//            if (arr[i] <= arr[j]) {
//                temp[k++] = arr[i++];
//            } else {
//                temp[k++] = arr[j++];
//            }
//        }
//        int start = i, end = r;
//        if (j <= r) {
//            start = j;
//        } else {
//            end = q;
//        }
//        while (start <= end) {
//            temp[k++] = arr[start++];
//        }
//        for (int n = 0; n <= r - p; n++) {
//            arr[p + n] = temp[n];
//        }
//    }
//
//    public void insertSort(int[] arr) {
//        for (int i = 1; i < arr.length; i++) {
//            int v = arr[i];
//            int j = i - 1;
//            for (; j >= 0; j--) {
//                if (arr[j] > v) {
//                    // 移动元素
//                    arr[j + 1] = arr[j];
//                } else {
//                    break;
//                }
//            }
//            arr[j + 1] = v;
//        }
//    }
//
//    public static void main(String[] args) {
//        int[] arr = new int[]{1, 3, 5, 3, 6, 2, 90, 34, 54};
//        new Sort().quickSort(arr);
////        new Sort().mergeSort(arr);
////        new Sort().insertSort(arr);
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i] + "  ");
//        }
//    }
//}
