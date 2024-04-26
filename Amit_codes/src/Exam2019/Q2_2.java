package Exam2019;

public class Q2_2 {
    public static void main(String[] args)
    {
        System.out.println(isSymmetricX("abcwercb3a",3));

    }



    public static boolean isSymmetricX(String str,int x)
    {
        if (x==0)
            return true;

        if (str.charAt(0)!=str.charAt(str.length()-1))
            return false;
        return isSymmetricX(str.substring(1,str.length()-1),x-1);
    }
}
