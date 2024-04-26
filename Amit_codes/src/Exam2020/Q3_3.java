package Exam2020;

public class Q3_3 {
    public static void main (String[]args) {
        int[][] mat = {{1, 2, 3, 4}, {5, 1, 2, 3}, {9, 5, 1, 2}};
        System.out.println(diagonal(mat));
    }





    public static boolean diagonal(int[][]a)
    {
        int row=a.length;
        int col=a[0].length;
        for (int i=0;i<row;i++)
        {
            for (int j=0;j<col;j++)
            {
                if(i+1<row && j+1<col)
                    if (a[i][j]!=a[i+1][j+1])
                    return false;
            }
        }
        return true;
    }
}
