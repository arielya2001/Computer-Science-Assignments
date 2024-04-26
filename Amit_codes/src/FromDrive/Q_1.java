package FromDrive;

import java.util.Arrays;

public class Q_1 {
    public static void main(String[]args)
    {
        int[][]mat=Q1(2,3);
        for (int i = 0; i < mat.length; i++) {
            System.out.println(Arrays.toString(mat[i]));

        }

    }




    public static int[][] Q1(int n, int m)
    {
        int [][]mat=new int[n][m];
        for (int i = 0; i < m; i++)
        {
            mat[0][i]=1;
        }
        for (int i = 0; i < n; i++)
        {
            mat[i][0]=1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                mat[i][j]=mat[i-1][j]+mat[i][j-1];
            }
        }
        return mat;
    }
}
