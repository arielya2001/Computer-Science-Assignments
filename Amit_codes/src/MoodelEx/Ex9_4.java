package MoodelEx;

import java.util.Arrays;

public class Ex9_4 {
    public static void main (String[]args) {
        double[] arr = {1, 2, 3, 4};
        shuffle(arr);
        System.out.println(Arrays.toString(arr));
    }



    public static void shuffle(double[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int r = (int) (Math.random()) * (n - i);
            swap(arr,i,r);
        }

    }

    public static void swap(double[] arr, int i, int j) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
