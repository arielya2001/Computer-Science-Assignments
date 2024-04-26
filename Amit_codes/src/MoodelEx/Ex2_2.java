package MoodelEx;

import java.util.Scanner;

public class Ex2_2 {
    public static void main (String[]args)
    {
        double avg=0;
        Scanner scan=new Scanner(System.in);
        int[]arr=new int[4];
        for (int i=0;i<arr.length;i++)
        {
            System.out.println("enter number");
            int n=scan.nextInt();
            arr[i]=n;

        }
        for (int i=0;i<arr.length;i++)
        {
            avg+=arr[i];
        }
        System.out.println(avg/arr.length);



    }

}
