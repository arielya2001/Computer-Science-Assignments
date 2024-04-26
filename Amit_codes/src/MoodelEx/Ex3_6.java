package MoodelEx;

import java.util.Arrays;

public class Ex3_6 {
    public static void main (String[]args) {
        int[] arr = {1, 2, 3, 4 ,5};
        evenOddSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void evenOddSort(int [] arr)
    {
        int j=-1;
        for (int i=0;i<arr.length-1;i++)
        {
            if (arr[i]%2==0)
            {
                j++;
                swap(arr,i,j);
            }
        }
    }
    public static void swap(int []arr, int i,int j)
    {
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

}
