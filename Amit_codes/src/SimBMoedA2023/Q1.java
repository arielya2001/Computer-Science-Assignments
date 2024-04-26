package SimBMoedA2023;

import java.util.ArrayList;
import java.util.Arrays;

public class Q1 {
    public static void main (String[]args)
    {
        System.out.println(maxDistPrimes(11,13));
    }



    public static int maxDistPrimes(int n1, int n2)
    {
        ArrayList<Integer>primes=new ArrayList<>();
        for (int i = n1; i <=n2 ; i++) {
            if ( isPrime(i))
                primes.add(i);
        }

        int max=0;
        for (int i = 1; i < primes.size(); i++)
        {
            int temp=primes.get(i)-primes.get(i-1);
            if (temp>max)
            {
                max = temp;
            }

        }
        return max;

    }


    public static boolean isPrime(int num)
    {
        if (num<=1)
            return false;

        for (int i = 2; i <=Math.sqrt(num) ; i++)
        {
            if (num%i==0)
                return false;

        }
        return true;
    }
}
