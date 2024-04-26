package MoodelEx;

public class Ex2_5 {
    public static void main (String[]args)
    {
        System.out.println(pow(4,4));
    }








    public static int pow(int m, int n)
    {
        if (n<=1)
            return m;
        return m*pow(m,n-1);
    }
}
