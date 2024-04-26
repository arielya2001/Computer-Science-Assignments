package MoedA2023;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Q_1Test {


public static double EPS=0.1;
    private static double[] generate(int size)
    {
        double []arr=new double[size];
        for (int i = 0; i < size; i++)
        {
            arr[i]=Math.random();
        }
        return arr;
    }
    private static int sameCounter(double[]a1,double[]a2)
    {
        int counter=0;
        for (int i = 0; i <a1.length ; i++) {{
                if (Math.abs(a1[i]-a2[i])<EPS)
                    counter++;
            }

        }
        return counter;
    }


    @Test
    void shuffle() {
        int times = 50;
        int size = 100;
        for (int i = 0; i < times; i++) {
            double[] arr = generate(size);
            double[] copyArr = Arrays.copyOf(arr, size);
            Q_1.shuffle(copyArr);
            int ratio=sameCounter(arr,copyArr) /size;
            assertTrue(ratio<0.5);

        }
    }

}