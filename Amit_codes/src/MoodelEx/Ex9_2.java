package MoodelEx;

public class Ex9_2 {
    public static void main(String[]args)
    {
        System.out.println(ToBinary(8));
    }





    public static String ToBinary(int num)
    {
        String res="";
        if(num==0)
        {
            return res;
        }
        return ToBinary(num/2)+num%2;


    }

}
