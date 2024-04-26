package MoodelEx;

public class Ex3_4 {
    public static void main (String[]args) {
        int[][] mat = {{1, 2, 3, 4},{1, 2, 3, 4}};
        print(transpose(mat));

    }

    public static int sum(int [][]array)
    {
        int sum=0;
     for (int i=0;i<array.length;i++)
     {
         for (int j=0;j<array[0].length;j++)
         {
             sum+=array[i][j];
         }
     }
     return sum;
    }

    public static int[][] transpose(int[][] array)
    {
        int [][]mat=new int[array[0].length][array.length];

        for (int i=0;i<array[0].length;i++) {
            for (int j = 0; j < array.length; j++) {
                mat[i][j] = array[j][i];
            }
        }
        return mat;
    }

    public static void print(int[][]mat)
    {
        for (int i=0;i<mat.length;i++) {
            System.out.println("");
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(" "+mat[i][j]);

            }
        }

    }

}
