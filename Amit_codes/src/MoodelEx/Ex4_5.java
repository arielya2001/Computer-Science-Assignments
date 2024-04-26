package MoodelEx;

public class Ex4_5 {
    public static void main (String[]args) {
        String s1 = "Amit Mushlemet";
        System.out.println(howManyWords(s1));
    }


public static int howManyWords(String str)
{
    String []arr=str.split(" ");
    return arr.length;
}

}
