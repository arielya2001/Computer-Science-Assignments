package MoodelEx;

public class Ex4_1 {
    public static void main (String[]args)
    {
        String s1="abcd";
        String s2="abdd";
        System.out.println(isEqual1(s1,s2));

    }

    public static Boolean isEqual(String a, String b)
    {
        if (a.length()!=b.length()) {
            return false;
        }

        for (int i=0; i<a.length();i++)
        {
            if (a.charAt(i)!=b.charAt(i))
            {
                return false;

            }
        }
        return true;
    }

    public static Boolean isEqual1(String a, String b)
    {
        if (a.length()!=b.length())
        {
            return false;
        }

        if (a.charAt(0)!=b.charAt(0))
        {
            return false;
        }
        return isEqual(a.substring(1),b.substring(1));

    }




}
