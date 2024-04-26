package MoodelEx;

public class Ex4_3
{  public static void main (String[]args) {
    String s1 = "amit";
    System.out.println(reverse1(s1));
}







    public static String reverse(String str)
    {
        if (str.length()<=1)
            return str;
        return reverse(str.substring(1))+str.charAt(0);
    }

    public static String reverse1(String str)
    {
        String res="";
        for (int i=str.length()-1;i>=0;i--)
        {
           res+= str.charAt(i);
        }
        return res;
    }



}



