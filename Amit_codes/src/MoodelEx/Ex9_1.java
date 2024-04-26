package MoodelEx;

public class Ex9_1 {
    public static void main(String[]args)
    {
        String s="ABBcA";
        System.out.println(isPalindrome(s));
    }





    public static Boolean isPalindrome(String str)
    {
        if (str.length()<=1)
        {
            return true;
        }
        if (str.charAt(0)!=str.charAt(str.length()-1))
        {
            return false;
        }
        else return isPalindrome(str.substring(1,(str.length()-1)));
    }


}
