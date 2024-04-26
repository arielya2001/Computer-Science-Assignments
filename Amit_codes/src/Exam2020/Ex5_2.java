package Exam2020;

public class Ex5_2 {


    public static boolean rowsSorted(int [][]mat)
    {
        for (int i=0;i<mat.length;i++)
        {
            for (int j=0;j<mat[0].length-1;j++)
            {
                if (mat[i][j]>mat[i][j+1])
                    return false;

            }
        }
        return true;
    }

}
