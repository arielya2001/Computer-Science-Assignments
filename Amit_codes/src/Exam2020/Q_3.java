package Exam2020;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Q_3 {
    public static void main (String[]args)
    {
        int []a={1,-8,5};
        int [] b={2,6,4};
        int []res=interlace(a,b);
        System.out.println(Arrays.toString(res));

    }

    public static int[] interlace(int[] a, int b[])
    {
        int i=0;
        int j=0;
        int k=0;
        int [] res=new int[a.length+b.length];


        while (i<a.length &&j<b.length)
        {
            res[k]=a[i];
            i++;
            k++;
            res[k]=b[j];
            k++;
            j++;
        }
        while (i<a.length)
        {
            res[k]=a[i];
            i++;
            k++;
        }
        while (i<b.length)
        {
            res[k]=a[i];
            i++;
            k++;

        }return res;
    }


}
