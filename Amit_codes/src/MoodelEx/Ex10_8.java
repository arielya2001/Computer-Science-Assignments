package MoodelEx;

import java.util.Arrays;

public class Ex10_8 {
    public static void main (String[]args)
    {
        char[][] a={{'a','w','x','r','e'},{'f','y','e','s','h'},{'h','y','y','e','a'},{'a','a','h','t','r'},{'t','f','g','z','z'}};
        System.out.println(crossWord(a,"hat"));
        for (int i=0;i<a.length;i++)
        {
            System.out.println(Arrays.toString(a[i]));
        }

    }






    public static boolean crossWord(char[][] a,String s)
    {

        for (int i = 0; i < a.length; i++) {
            String row = String.valueOf(a[i]);
            if (row.contains(s)) {
                return true;
            }
        }

        for (int i = 0; i <a[0].length ; i++)
        {
            String col="";
            for (int j = a.length-1; j>=0 ; j--)
            {
                col=a[j][i]+col;
            }
            if(col.contains(s))
            {
                return true;
            }
        }
        return false;
    }
}
