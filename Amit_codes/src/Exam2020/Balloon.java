package Exam2020;

public class Balloon {
    private double x,y,z,radius;

    public Balloon(double x,double y,double z,double radius)
    {
        this.x=x;
        this.y=y;
        this.z=z;
        this.radius=radius;
    }
    public Balloon(Balloon b)
    {
        this(b.x,b.y,b.z,b.radius);
    }

    public double volume()
    {
        double v=Math.PI*0.75*Math.pow(radius,3);
        return v;
    }

    public boolean isIn(double x,double y ,double z)
    {
        double dist= Math.sqrt(
                Math.pow(this.x-x,2)+
                Math.pow(this.y-y,2)+
                Math.pow(this.z-z,2));
        return dist<=radius;

    }
}
