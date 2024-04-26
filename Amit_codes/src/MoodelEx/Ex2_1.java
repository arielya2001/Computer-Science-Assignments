package MoodelEx;

public class Ex2_1 {
    public static void main (String[]args)
    {
        System.out.println(sumAll(5));
    }



    public static int sumAll(int n)
    {
        int sum =0;
        if (n==0)
        {
            return sum;
        }
        return sumAll(n-1)+n;
    }
}
