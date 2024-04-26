package Exam2020;

public class Q2_3 {
    public static void main (String[]args)
    {
        System.out.println(findMaxNum("93&ab1234crt70"));
    }





    public static int findMaxNum(String s) {
        int max=-1;
        String [] str=s.split("[^0-9]");
        for (int i=0;i<str.length;i++) {
            if (!str[i].isEmpty()) {
                int s1 = Integer.valueOf(str[i]);
                if (s1 > max) {
                    max = s1;
                }

            }
        }
        return max;
    }
}
