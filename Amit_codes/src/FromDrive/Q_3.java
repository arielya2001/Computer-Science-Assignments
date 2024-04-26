package FromDrive;

public class Q_3 {
    public static void main(String[]args)
    {
        System.out.println(Q5("acacaacaca","aca"));

    }


    public static int Q5(String s1, String s2)
    {
        int counter=0;
        for (int i = 0; i <= s1.length()-s2.length(); i++)
        {
            if (s1.charAt(i)==s2.charAt(0))
            {
                if ((s1.substring(i,s2.length()+i).contains(s2)))
            {
                counter++;
            }

            }

        }
        return counter;
    }

}
