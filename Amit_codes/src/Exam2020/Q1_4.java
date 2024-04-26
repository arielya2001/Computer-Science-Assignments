package Exam2020;

public class Q1_4 {
    public static void main (String[]args)
    {
        System.out.println(powerful(72));
    }






    public static boolean powerful (int n)
    {
        if (isPrime(n))
        {return false;}

        for (int i=2;i<n;i++)
           if(n%i==0 && isPrime(i))
           {
               if (n%(i*i)!=0)
               { return false;}
           }
        return true;

    }


    public static boolean isPrime(int n)
    {
        if (n<=1)
            return false;
        for (int i=2;i<=Math.sqrt(n);i++)
        {
            if (n%i==0)
            { return false;}
        }
        return true;
    }
}
