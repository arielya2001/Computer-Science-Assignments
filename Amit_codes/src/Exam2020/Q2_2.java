package Exam2020;

import java.util.Arrays;

public class Q2_2 {
    public static void main (String[]args)
    {
        int[][]mat={{1,2,3,4},{5,1,2,3},{6,5,1,2},{7,6,5,1,}};
        System.out.println(sameNumbers(mat));
    }





    public static boolean sameNumbers(int[][]mat)
    {
        for (int i=0;i<mat.length-1;i++)
        {
            for (int j=0;j<mat.length-1;j++)
            {
                if (mat[i][j]!=mat[i+1][j+1])
                {
                    return false;
                }
            }
        }
        return true;
    }
}
