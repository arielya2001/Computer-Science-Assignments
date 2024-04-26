package ExampleExam;

import java.util.ArrayList;
import java.util.Collections;

public class F2 implements Parabula {

    private double a,b,c;
    public F2(double a,double b ,double c)
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
    public Parabula add(Parabula p) {
        double [] arrP=p.get();
        double na=this.a+arrP[0];
        double nb=this.a+arrP[1];
        double nc=this.a+arrP[2];
        return new F2(na,nb,nc);
    }

    @Override
    public double[] get() {
        return new double[]{a,b,c};
    }

    @Override
    public double extream(Parabula p) {
        double [] arrP=p.get();
        double nb=-arrP[1];
        double na=2*arrP[0];
        if (na==0) {
            throw new ArithmeticException("cant divide by 0");
        }
        return nb/na;

    }


    public static int numberOfRealRoots(Parabula p)
    {
        double [] arrP=p.get();
        double nb=Math.pow(arrP[1],2);
        double nac=-4*arrP[0]*arrP[2];
        double root=nb+nac;
        if (root==0)
            return 1;
        if (root>0)
            return 2;
        else return -1;

    }
    public static void sort(ArrayList<Parabula> a)
    {
        boolean hasChanged=true;
        for (int i = a.size()-1; i>0&&hasChanged ; i++)
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
