package MoodelEx;

public class Ex4_10 {
    public static void main (String[]args) {
        starTriangle(10);
    }







    public static void starLine(int num)
    {
        if (num<=1)
            System.out.println("*");

     else starLine(num-1);
        System.out.print("*");

    }

    public static void starTriangle(int num)
    {
      if (num<=1) {
          System.out.println("*");
      }
     else {
          starTriangle(num - 1);
          starLine(num);
      }



    }


}
