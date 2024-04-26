package FromDrive;

public class Q_2 {

    public static void main(String[]args)
    {
        System.out.println(q2("aaabbbcccxxyaaa"));

    }



    public static String q2(String str)
    {

        if (str.length()<=1)
            return str;

        if (str.charAt(0)!=str.charAt(1)) {
            return str.charAt(0) + q2(str.substring(1));
        }
        else return q2(str.substring(1));


    }
}
