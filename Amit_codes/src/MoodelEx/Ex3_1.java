package MoodelEx;

public class Ex3_1 {
    public static void main (String[]args)
    {
        int[]arr={1,2,3,4};
        System.out.println(arrayMean(arr));
    }





    public static int sumArray(int[]arr)
    {
        int sum=0;
        for (int a:arr)
        {
            sum+=a;

        }
        return sum;
    }
    public static double arrayMean(int[]arr)
    {
        double sum=0;
        for (int a:arr)
        {
            sum+=a;

        }
        return sum/arr.length;
    }
}
