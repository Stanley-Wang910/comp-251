package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {0, 99, 0, 5, 20, 123, 0, -1, 72, 21, 22, 13, 8, 7, 67, 29, 1, 2, 4};
        // will skip over 0-index
        HeapSort HeapSort = new HeapSort();
        HeapSort.heapSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    public void heapSort(int[] arr) {
        BuildMaxHeap(arr);

        for (int i = arr.length - 1; i > 0; i--) {
            // swap largest with last element
            int temp = arr[i];
            arr[i] = arr[1];
            arr[1] = temp;

            // after swap last element is sorted, but root may break max-heap condition
            // call maxHeapify with smaller heap size
            maxHeapify(arr, i, 1);
        }

    }

    private void BuildMaxHeap(int[] arr) {
        int heapSize = arr.length;

        for (int i = (heapSize / 2) - 1; i >= 1; i--) {
            maxHeapify(arr, heapSize, i);
        }

    }

    private void maxHeapify(int[] arr, int heapSize, int i) {
        int left = 2 * i;
        int right = 2 * i + 1;

        int largest = i;

        if (left < heapSize && arr[left] > arr[i]) {
            largest = left;
        }
        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            // swap elements
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            maxHeapify(arr, heapSize, largest);
        }
    }
}
