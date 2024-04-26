package Exam2019;

public class Q2_3 {

    public static void switchDiags(int[][] a)
    {
        int r=a.length-1;
        for (int i = 0; i <r ; i++)
        {
                int temp=a[i][i];
                a[i][i]=a[i][r-i];
                a[i][r-i]=temp;
            }

        }
    }
