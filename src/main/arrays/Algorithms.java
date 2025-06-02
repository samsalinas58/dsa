
package main.arrays;

import java.util.Vector;

final class Sort {

    private Sort() {
    }

    public static int[] bubbleSort(int[] arr) {
        if (arr.length <= 1) return arr;

        for (int i = 0, n = arr.length; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (arr[j] < arr[j - 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }
            }
        }
        return arr;
    }

    public static int[] selectionSort(int[] arr) {
        if (arr.length <= 1) return arr;

        for (int i = 0; i < arr.length; i++) {
            int min = Integer.MAX_VALUE;
            int min_idx = -1;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    min_idx = j;
                }
            }
            int temp = arr[i];
            arr[i] = min;
            arr[min_idx] = temp;
        }

        return arr;
    }

    public static int[] insertionSort(int[] arr) {
        if (arr.length <= 1) return arr;

        // traverse the unsorted sub-list
        for (int i = 1; i < arr.length; i++) {
            // loop through the sorted sub-list
            if (arr[i] > arr[i-1]) continue;
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j]) {
                    int tmp = arr[i];
                    for (int k = i; k > j; k--) arr[k] = arr[k - 1];
                    arr[j] = tmp;
                    break;
                }
            }
        }

        return arr;
    }

    public static int[] mergeSort(int[] arr) {
        if (arr.length <= 1) return arr;
        int l = 0, r = arr.length - 1;

        _mergeSort(arr, l, r);
        return arr;
    }

    private static void  _mergeSort(int[] arr, int l, int r) {
        if (l == r) return;

        int m = (l + r) / 2;

        _mergeSort(arr, l, m);
        _mergeSort(arr, m+1, r);

        int[] L = new int[(m - l) + 1];
        int[] R = new int[(r - (m+1)) + 1];

        for (int i = 0, k = l; k <= m; k++, i++) L[i] = arr[k];
        for (int i = 0, k = m + 1; k <= r; k++, i++) R[i] = arr[k];

        int i = 0, j = 0, k = l;
        for (; i < L.length && j < R.length; k++) {
            if (R[j] <= arr[k]) {
                arr[k] = R[j];
                j++;
            }
            else {
                arr[k] = L[i];
                i++;
            }
        }


        for (; i < L.length; i++, k++)
            arr[k] = L[i];

        for (; j < R.length; j++, k++)
            arr[k] = R[j];
    }

}