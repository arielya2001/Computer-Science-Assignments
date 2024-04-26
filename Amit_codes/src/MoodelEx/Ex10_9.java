package MoodelEx;

public class Ex10_9 {
    public static void main (String[]args)
    {
        System.out.println(square(246));

    }





    public static boolean square(int a)
    {
        int powSum=0;
        for (int i = 1; i <=a ; i++)
        {
            if (a%i==0)
            {
                powSum+=i*i;
            }
        }
        return Math.sqrt(powSum)==Math.floor(Math.sqrt(powSum));

    }
}
