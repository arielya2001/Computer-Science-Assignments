package Exam2020;

public class Q1_2 {
    public static void main (String[]args)
    {
        System.out.println(decimal2Binary(7));
    }




    public static String decimal2Binary(int n)
    {
        String res="";
        if (n==0)
            return res;
        return n%2+decimal2Binary(n/2);

    }
}
