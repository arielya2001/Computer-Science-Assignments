package MoodelEx;

import java.util.ArrayList;

public class Ex9_3 {
    public static void main (String[]args) {
        String str = "abcd";
        ArrayList<String> arr = permutations(str);
        print(arr);
    }




    public static ArrayList<String>permutations(String s)
    {
        ArrayList<String>a=new ArrayList<>();
         perHelper(s,0,s.length()-1,a);
         return a;

    }

    public static void perHelper(String line, int left, int right, ArrayList<String>a)
    {
        if (left==right)
        {
            a.add(line);
        }
        for (int i=left;i<=right;i++)
        {
            String swapped=swap(line,left,i);
            perHelper(swapped,left+1,right,a);
        }

    }

    public static String swap(String s,int i,int j)
    {
        char[]charArray=s.toCharArray();
        char temp=charArray[i];
        charArray[i]=charArray[j];
        charArray[j]=temp;
        return String.valueOf(charArray);

    }
    public static void print(ArrayList<String>a)
    {
        for (int i=0;i<a.size();i++)
        {
            System.out.println(a.get(i));
        }
    }

}
