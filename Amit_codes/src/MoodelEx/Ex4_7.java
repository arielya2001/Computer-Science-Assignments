package MoodelEx;

public class Ex4_7 {
    public static void main (String[]args) {
        System.out.println(biggestDigit(589));
    }




public static int biggestDigit(int num)
{
    if (num<10) {
        return num;
    }
    int max =num%10;
    if (max <(num/10)%10)
    {
        max =(num/10)%10;
    }
    return max;

}
}
