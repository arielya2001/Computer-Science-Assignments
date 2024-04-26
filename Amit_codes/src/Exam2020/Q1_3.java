package Exam2020;

public class Q1_3 {
    public static void main (String[]args)
    {
        System.out.println(semi(5));
    }







    public static boolean semi(int n)
    {
        for (int i=2;i<n;i++)
        {
            for (int j=i;j<n;j++)
            {
                if (i*j==n &&isPrime(i) &&isPrime(j))
                    return true;
            }
        }
        return false;

    }


    public static boolean isPrime(int n)
    {
        if (n<=1)
            return false;

        for (int i=2;i<=Math.sqrt(n);i++)
        {
            if (n%i==0)
                return false;
        }
        return true;
    }
}
