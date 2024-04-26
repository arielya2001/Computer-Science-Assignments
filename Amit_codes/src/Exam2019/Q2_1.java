package Exam2019;

import java.util.Arrays;

public class Q2_1 {
    public static void main(String[] args)
    {
        System.out.println(sortDigits(3412072));

    }


        public static long sortDigits(long num)
    {
        String sortNum=String.valueOf(num);
        char[]arr=sortNum.toCharArray();
        Arrays.sort(arr);
        String temp=new String(arr);
        long newNum=Long.valueOf(temp);
        return newNum;
    }
}
