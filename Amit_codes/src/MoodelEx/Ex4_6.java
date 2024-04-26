package MoodelEx;

public class Ex4_6 {
    public static void main (String[]args) {
        String s1 = "abdc";
        System.out.println(sort(s1));
    }



public static String sort(String str)
{
    char[] arr=str.toCharArray();
    for (int i=0;i<str.length()-1;i++)
    {
        for (int j=i+1;j<str.length();j++)
        {
            if (arr[i]>arr[j])
            {
                char temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
            }
        }

    }
    return new String(arr);
}

}
