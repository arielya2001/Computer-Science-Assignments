package MoodelEx;

public class Ex2_4 {
    public static void main (String[]args)
    {
        System.out.println(zeroCounter(50040000));
    }





    public static int zeroCounter(int n)
    {
        int counter=0;
        if (n==0)
            return counter;
        if (n%10==0)
        {
            counter++;
        }
            return counter+zeroCounter(n/10);
        }

}
