package Ex6;

public class Interval {
    private double x,y;
    public Interval(double x,double y)
    {

        this.x=Math.max(x,y);
        this.y=Math.min(x,y);
    }

    public double getX() {
        return x;
    }
    public double getY()
    {
        return y;
    }

    public boolean contains(double x)
    {
       return  x>this.x||x<this.y;
    }

    public boolean intersection(Interval b)
    {
        return b.getX()<=this.y &&b.getY()>=this.x;
    }
    public String toString()
    {
        return "right"+ x + "left"+y;
    }

    public double distBetweenMidPoints (Interval b)
    {
        double center1=(this.x+this.y)/2;
        double center2=(b.getX()+b.getY())/2;

        return Math.abs(center1-center2);
    }

}
