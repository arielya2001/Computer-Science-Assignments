package FromDrive;

import java.util.Arrays;

public class Q_4 {
    public static void main(String[]args)
    {
        int[]arr={4,2,3,5,0,1};
        int [] a=Q12(arr);
        System.out.println(Arrays.toString(a));
    }

    public static int[] Q12(int[] arr)
    {
        int maxStart=0;
        int maxLength=1;
        int currentStart=0;
        int currentLength=1;

        for (int i = 1; i <arr.length ; i++)
        {
            if (arr[i]>arr[i-1])
            {
                currentLength++;
            }
            else{
                if (currentLength>maxLength)
                {
                    maxStart=currentStart;
                    maxLength=currentLength;
                }
                currentStart=i;
                currentLength=1;
            }
        }
        if (currentLength>maxLength)
        {
            maxStart=currentStart;
            maxLength=currentLength;
        }
        return Arrays.copyOfRange(arr,maxStart,maxStart+maxLength);

    }
}
