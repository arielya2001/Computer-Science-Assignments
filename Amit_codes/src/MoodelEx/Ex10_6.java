package MoodelEx;

public class Ex10_6 {
    public static void main (String[]args)
    {
        int [][]arr={{1,2,3},{4,5,6},{-1,-1,-2}};
        System.out.println(calcSumDiag(arr));

    }








    public static int calcSumDiag(int[][] arr)
    {
        return helpCalc(arr,0);
    }
    public static int helpCalc(int[][]arr, int i)
    {
        if (i==arr.length-1)
            return arr[i][i];

        return arr[i][i]+helpCalc(arr,1+i);
    }
}
