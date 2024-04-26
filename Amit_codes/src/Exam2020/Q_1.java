package Exam2020;

public class Q_1 {
    public static void main (String[]args)
    {
        System.out.println(Binary2Dec1("111"));
    }






    public static int Binary2Dec(String s)
    {
        int res=0;
        for (int i=0;i<s.length();i++)
        {
            int c= Character.getNumericValue(s.charAt(s.length()-1-i));
            if (c==1||c==0)
            {
                res+=(int) (Math.pow(2,i)*c);
            }
            else return -1;

        }
        return res;

    }
    public static int Binary2Dec1(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int c = Integer.parseInt(String.valueOf(s.charAt(s.length() - 1 - i)));
            if (c == 1 || c == 0) {
                res += (int) (Math.pow(2, i) * c);
            } else return -1;

        }
        return res;
    }


    }
