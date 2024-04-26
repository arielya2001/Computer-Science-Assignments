public class QuickSort {

        public static void main(String[] args) {
            int[] arr = {10, 4, 3,2, 6};
            int n = 3; 
            int result = quickselect(arr, 0, arr.length - 1, n - 1);
            System.out.println("האיבר ה-" + n + "י הוא: " + result);
        }

        public static int quickselect(int[] arr, int left, int right, int k) {
            if (left == right) { 
                return arr[left];
            }

            int pivotIndex = partition(arr, left, right);

            if (k == pivotIndex) {
                return arr[k];
            } else if (k < pivotIndex) {
                return quickselect(arr, left, pivotIndex - 1, k);
            } else {
                return quickselect(arr, pivotIndex + 1, right, k);
            }
        }

        private static int partition(int[] arr, int left, int right) {
            int pivot = arr[right];
            int i = left;

            for (int j = left; j < right; j++) {
                if (arr[j] <= pivot) {
                    swap(arr, i, j);
                    i++;
                }
            }

            swap(arr, i, right);
            return i;
        }

        private static void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }


