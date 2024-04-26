package MoodelEx;

public class Ex4_2 {
    public static void main (String[]args) {
        String s1 = "abcd";
        String s2 = "aabbcbbaa";
        System.out.println(isSymetric1(s2));
    }







        public static boolean isSymetric(String str)
    {
        if (str.length()<=1)
            return true;
        if (str.charAt(0)==str.charAt(str.length()-1))
        {
            return isSymetric(str.substring(1,str.length()-1));
        }
        return false;
    }

    public static boolean isSymetric1(String str)
    {
        if (str.charAt(0)!=str.charAt(str.length()-1))
            return false;

        for (int i=0;i<str.length();i++)
        {
            if (str.charAt(i)!=str.charAt(str.length()-1-i))
            {
                return false;
            }
        }
        return true;
    }


}
