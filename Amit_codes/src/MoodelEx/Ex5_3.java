package MoodelEx;

public class Ex5_3 {
    public static void main (String[]args)
    {
        System.out.println(gcd(16,4));
    }






    public static int gcd(int x, int y)
    {
        int ans=1;
        if (x==y)
        {
            ans=x;
            return ans;
        }
        if (y<x)
            ans= gcd(x-y,y);

        else ans=gcd(y,x);
        return ans;
    }



    public static long round(double d)
    {
        return (long) (d+0.5);
    }
}
