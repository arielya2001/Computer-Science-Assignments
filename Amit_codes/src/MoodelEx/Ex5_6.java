package MoodelEx;

public class Ex5_6 {
    public static void main(String[]args)
    {
        int[]arr={1,2,3,4,5,6,7,8,9,6};
        System.out.println(isSortedUp(arr,1,10));
    }



public static boolean isSortedUp(int arr[], int start, int end) {
    for (int i = start; i < end - 1; i++) {
        if (arr[i] > arr[i + 1])
            return false;
    }
    return true;
}

}


