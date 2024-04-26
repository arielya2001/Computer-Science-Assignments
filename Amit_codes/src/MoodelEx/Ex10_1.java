package MoodelEx;

public class Ex10_1 {





    public static boolean sameNumbers(int[][]mat)
    {
        for (int i = 0; i <mat.length-1; i++)
        {
            for (int j = 0; j < mat.length-1 ; j++)
            {
                if (mat[i][j]!=mat[i+1][j+1])
                    return false;
            }
        }
        return true;
    }
}
