package MoodelEx;

public class Ex10_4 {
    public static void main (String[]args)
    {
        System.out.println(isSymmetricX("abcwercba",2));
    }





    public static boolean isSymmetricX(String str,int x)
    {
        if(x==0)
            return true;
        if (str.charAt(0)!=str.charAt(str.length()-1))
            return false;
        return isSymmetricX(str.substring(1,str.length()-1),x-1);
    }
}
