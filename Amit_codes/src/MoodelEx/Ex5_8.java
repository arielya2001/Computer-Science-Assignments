package MoodelEx;

public class Ex5_8 {
    public static void main(String[]args) {
        int[] arr = {-1, -2, 3, -4, -5, 6, -7, -8, -9, -6};
        System.out.println(biggest2(arr));
    }






public static int biggest2(int[]arr)
{
    int max=Integer.MIN_VALUE;
    int max2=Integer.MIN_VALUE;
    for (int i = 0; i < arr.length-1; i++)
    {
        if (arr[i]>max) {
            max2 = max;
            max = arr[i];
        }
        else if (arr[i]>max2)
        {
            max2=arr[i];
        }

    }
    return max2;
}
}
