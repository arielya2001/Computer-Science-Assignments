package MoodelEx;

public class Ex4_4 {
    public static void main (String[]args) {
        String s1 = "aabcdxabybza";
        System.out.println(firstChar(s1));
    }


 public static int firstChar(String s)
 {
     char first=s.charAt(0);
     int counter=1;

     for (int i=1;i<s.length();i++)
     {
         if (s.charAt(i)==first)
             counter++;
     }

     return counter;
 }

}
