package MoodelEx;

public class Ex2_3 {
    public static void main (String[]args)
    {
        print(10);
    }






    public static void print(int n)
    {
      if (n>0)
          print(n-1);
      for (int i=0;i<n;i++)
      {
          System.out.print("*");

      }
        System.out.println("");
    }
}
