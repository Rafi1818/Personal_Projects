public class QuickSort {
    public static Integer[] sort(Integer[] arr) {
        quicksort(arr, 0, arr.length - 1);
        return arr;
    }

    private static void quicksort(Integer[] arr, Integer low, Integer high) {
        if (low < high) {
            Integer pivotIndex = partition(arr, low, high);
            quicksort(arr, low, pivotIndex - 1);
            quicksort(arr, pivotIndex + 1, high);
        }
    }

    private static Integer partition(Integer[] arr, Integer low, Integer high) {
        Integer pivot = arr[high];
        Integer i = low - 1;
        for (Integer j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                Integer temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Integer temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}