package MoodelEx;

public class Ex10_7 {
    public static void main (String[]args)
    {
        System.out.println(reduce("ammmmittt"));
    }


    public static String reduce(String s)
    {
        String res="";
        for (int i = 0; i < s.length()-1; i++)
        {
            if (s.charAt(i)!=s.charAt(i+1))
                res+=s.charAt(i);
        }
        return res+s.charAt(s.length()-1);
    }
}
