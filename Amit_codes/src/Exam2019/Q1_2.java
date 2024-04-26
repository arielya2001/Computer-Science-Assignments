package Exam2019;

public class Q1_2 {


    public static boolean isDivisible(int[] a)
    {
        for (int i = 0; i <a.length ; i++) {
            for (int j = a.length-1; j>0 ; j--)
            {
                if (leftMul(a,i)*rightMul(a,j)==betweenMul(a,i,j))
                {
                    return true;
                }
            }
        }
        return false;

    }


    private static int leftMul(int[] a, int j)
    {
        int ans=1;
        for (int i=0;i<j;i++)
        {
            ans*=a[j];
        }
        return ans;
    }

    private static int rightMul(int[] a, int i)
    {
        int ans=1;
        for (int j = a.length-1; j >=i ; j--)
        {
            ans*=a[j];
        }
        return ans;
    }

    private static int betweenMul(int[] a, int i, int j)
    {
        int ans=1;
        for (int k = i+1; k <j ; k++) {
            {
                ans*=a[k];
            }
        }
        return ans;

    }
}
