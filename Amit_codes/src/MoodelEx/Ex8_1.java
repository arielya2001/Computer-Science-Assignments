package MoodelEx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Ex8_1 {
    public static void main(String[]args)
    {
        ArrayList<String>amitList=new ArrayList<>();
        String s1="amen";
        String s2="titpozez";
        String s3="sharmut";
        String s4="mabsut";
        amitList.add(s1);
        amitList.add(s2);
//        amitList.add(s3);
//        amitList.add(s4);
        String line="titpozez amen";
        System.out.println(isPer(amitList,line));
    }




    public static boolean isPer(ArrayList<String> words,String line)
    {
        return checkPer(words,0,line);

    }
    public static boolean checkPer(ArrayList<String> words,int currentIndex,String line)
    {
        if (currentIndex==words.size())
        {
            String shirshur=String.join(" ",words);
            return shirshur.equals(line);
        }
        for (int i = currentIndex; i <words.size() ; i++)
        {
         Collections.swap(words,i,currentIndex);
         if (checkPer(words,currentIndex+1,line))
         {
             return true;
         }
            Collections.swap(words,i,currentIndex);
        }
        return false;

    }


    public static void swap(String s,int i,int j)
    {
        char []charArray=s.toCharArray();
        char temp=charArray[i];
        charArray[i]=charArray[j];
        charArray[j]=temp;

    }
}
