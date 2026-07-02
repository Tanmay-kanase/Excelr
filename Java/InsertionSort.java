import java.util.Arrays;

public class InsertionSort {

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] >= key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = { 5, 7, 8, 2, 3, 1 };

        // insertionSort(arr);
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            int pivotIndex = partititon(arr, start, end);
            quickSort(arr, start, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, end);

        }
    }

    private static int partititon(int[] arr, int start, int end) {
        int pivot = arr[end];
        int idx = start - 1;

        for (int i = start; i < end; i++) {
            if (arr[i] <= pivot) {
                idx++;
                swap(arr, i, idx);
            }
        }

        swap(arr, idx + 1, end);

        return idx + 1;
    }

    private static void swap(int[] arr, int i, int idx) {
        int temp = arr[i];
        arr[i] = arr[idx];
        arr[idx] = temp;
    }
}
