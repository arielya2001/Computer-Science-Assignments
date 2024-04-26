package MoodelEx;

public class Ex10_5 {
    public static void main (String[]args)
    {
        System.out.println(mergeStrings("2abcde22","xyz2aaa2"));

    }





    public static String mergeStrings(String a,String b)
    {
        String res="";
        for (int i = 0; i < a.length(); i++)
        {
            String temp=String.valueOf(a.charAt(i));
            if(b.contains(temp)&&!res.contains(temp))
            {
                res+=a.charAt(i);
            }
        }
        return res;
    }
}
