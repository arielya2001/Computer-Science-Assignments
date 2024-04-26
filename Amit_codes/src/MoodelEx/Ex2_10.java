package MoodelEx;

public class Ex2_10 {
    public static void main (String[]args)
    {
        System.out.println(sameChar("aaaaabbaaaaaaa"));
    }


    public static boolean sameChar(String s)
    {
        if (s.length()<12) {
            return false;
        }

        if (!s.substring(2,5).equals(s.substring(9,12)))
        {
            return false;
        }
        return true;
    }
}
