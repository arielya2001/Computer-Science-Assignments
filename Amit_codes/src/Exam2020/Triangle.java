package Exam2020;

public class Triangle {

    private double a,b;
    private Point_2D p;


    public Triangle(double a, double b, Point_2D p)
    { if (a ==0 || b==0)
        throw new RuntimeException("cannot be 0");
        this.a=a;
        this.b=b;
        this.p=new Point_2D(p);
    }
    public void setA(double a)
    {
        this.a=a;
    }
    public void setB(double b) {

        this.b = b;
    }
    public double getA()
    {
        return a;
    }

    public double getB() {
        return b;
    }

    public double calculate()
    {
        double sum=Math.pow(a,2)+Math.pow(b,2);
        double c=Math.sqrt(sum);
        return c;
    }

    public double calculateAng()
    {
        double c=calculate();
        if (c==0)
            throw new ArithmeticException("can divide by 0");
        double ang=Math.asin(a/c);
        return Math.toDegrees(ang);

    }
    public Point_2D centerOfC()
    {
        double c=calculate();
        double xMid=c/2;
        double yMid=xMid*Math.sin(a/c);
        return new Point_2D(xMid,yMid);


    }
}
