package MoodelEx;

import java.util.ArrayList;
import java.util.Collections;

public class F2 implements Parabula
{
    private double a,b,c;
    public F2(double a,double b,double c)
    {
        this.a=a;
        this.b=b;
        this.c=c;
    }


    @Override
    public double f(double x) {
        return a*Math.pow(x,2)+b*x+c;
    }

    @Override
    public Parabula add(Parabula p)
    {
        double []arr= p.get();
        double nA=a+arr[0];
        double nB=b+arr[1];
        double nC=c+arr[2];
        return new F2(nA,nB,nC);
    }

    @Override
    public double[] get()
    {
        return new double[] {a,b,c};
    }

    @Override
    public double extream(Parabula p)
    {
            double[] arr = p.get();
            double nB = -arr[1];
            double nA = 2 * arr[0];
            if (nA==0)
            {
                throw new ArithmeticException("divide by zero - extream point not exist");
            }
            return nB / nA;
    }
    public static int numberOfRealRoot(Parabula p)
    {
        double[] arr = p.get();
        double res=Math.pow(arr[1],2)-4*arr[0]*arr[2];
        if (res>0)
            return 2;
        else if (res==0)
        {
            return 1;
        }
        else return -1;
    }

    public static void sort(ArrayList<Parabula>a)
    {
        boolean hasChanged=true;
        for (int i = a.size()-1; i >0&&hasChanged ;i--)
        {
            hasChanged=false;
            for (int j = 0; j < i; j++)
            {
                if (a.get(j).extream(a.get(j))>a.get(j+1).extream(a.get(j+1)))
                {
                    Collections.swap(a,j,j+1);
                    hasChanged=true;
                }
            }
        }
    }
}
