package Exam2019;

public class Q1_1   {





    public static double heartBeat(int n)
    {
        if (n==1)
            return 55;
        if (n==2)
            return 57;
         if (n%5==0)
             return 3+heartBeat(n-1);
         else  return (heartBeat(n-1)+heartBeat(n-2)+2)/2;


    }
}
