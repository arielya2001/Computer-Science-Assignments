package ExampleExam;

import java.util.ArrayList;
import java.util.Collections;

public class Q_2 {
    public static void main(String[]args)
    {
        String s1="yaBen";
        String s2="Sharmut";
        String s3="Amen";
        String s4="Tamut";
        String s5="Tamut yaBen Sharmut";
        ArrayList<String>words=new ArrayList<>();
        words.add(s1);
        words.add(s2);
        words.add(s3);
        words.add(s4);
        System.out.println(isPer(words,s5));



    }






 public static  boolean isPer(ArrayList<String>words, String line)
 {
     return isPerHelper(words,0,line);

 }

 public static boolean isPerHelper(ArrayList<String>words,int currentIndex, String line)
 {
     if(currentIndex==words.size()) {
         String shirsur = String.join(" ", words);
         return shirsur.equals(line);
     }

     for (int i=currentIndex;i<words.size();i++)
     {
         Collections.swap(words,i,currentIndex);
         if (isPerHelper(words,currentIndex+1,line))
         {
             return true;
         }
         Collections.swap(words,i,currentIndex);
     }
     return false;

 }


}
