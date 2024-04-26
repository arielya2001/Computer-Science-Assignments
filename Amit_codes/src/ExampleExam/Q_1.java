package ExampleExam;

import java.util.ArrayList;
import java.util.Arrays;

public class Q_1 {
    public static void main(String[]args)
    {
        int[]arr=d_range(5);
        System.out.println(Arrays.toString(arr));


    }

public static int[] d_range(int n)
{
    if (isPrime(n))
        return new int[0];
    ArrayList<Integer>notPrimes=new ArrayList<>();
    notPrimes.add(n);
    int i=n+1;
    int j=n-1;
    while(!isPrime(i))
    {
        notPrimes.add(i);
        i++;
    }
    while(!isPrime(j))
    {
        notPrimes.add(j);
        j--;
    }
    int [] res=new int[notPrimes.size()];
    for (int k = 0; k <notPrimes.size(); k++)
    {
        res[k]=notPrimes.get(k);
    }
     Arrays.sort(res);
    return res;
}



public static boolean isPrime(int n)
{
    if (n<=1)
        return false;
    for (int i=2;i<=Math.sqrt(n);i++) {
        if (n % i == 0)
        {
            return false;
        }
    }
    return true;
}

}
