package FromDrive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;

public class Q_5 {
    public static void main(String[]args)
    {
        String[]s=allCodes();
        System.out.println(Arrays.toString(s));


    }

    public static String[] allCodes()
    {
        String s="12345#";
        ArrayList<String>a=new ArrayList<>();
        ArrayList<String>b=per(a,0,s.length()-1,s);
        String[] res= new String[b.size()];

        for (int i = 0; i < b.size(); i++)
        {
            res[i]=b.get(i);
        }
        return res;
    }

    public static ArrayList<String> per(ArrayList<String>a ,int left,int right,String str)
    {
        if (left==right) {
            a.add(str);
            return a;
        }


        for (int i = left; i <right ; i++)
        {
            String swapped=swap(str,left,i);
            per(a,left+1,right,swapped);
        }
        return a;
    }

    public static String swap(String s,int i,int j)
    {
        char[]arr=s.toCharArray();
        char temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
        return String.valueOf(arr);
    }
}
