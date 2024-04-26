package MoedA2023;

public class Q_1 {




    public static void shuffle(double[]arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            int r=(int)Math.random()*(arr.length-i);
            swap(arr,i,r);

        }
    }
    public static void swap(double[]arr,int i,int j)
    {
        double temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }


}
