package MoodelEx;

import java.util.ArrayList;

public class Ex10_2 {
    public static void main (String[]args)
    {
        System.out.println(avgDistPrime(10,19));
    }




    public static double avgDistPrime(int n1,int n2)
    {
        ArrayList<Integer>primeList=new ArrayList<>();
        for (int i = n1; i <=n2 ; i++)
        {
            if (isPrime(i))
                primeList.add(i);
        }
        double sum=0;

        for (int i = 0; i < primeList.size()-1; i++)
        {
            sum+=primeList.get(i+1)-primeList.get(i);

        }
        return sum/(primeList.size()-1);

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
