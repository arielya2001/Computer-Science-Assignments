package MoodelEx;

public class Ex5_9 {
    public static void main (String[]args)
    {
        int[][]mat={{1,2,3},{0,3,4},{3,4,5}};
        System.out.println(colsSorted(mat));
    }





public static boolean colsSorted(int [][]mat) {
    for (int i = 0; i < mat[0].length; i++) {
        for (int j = 0; j < mat.length - 1; j++) {
            if (mat[j][i] > mat[j + 1][i])
                return false;
        }

    }
    return true;
}

}
