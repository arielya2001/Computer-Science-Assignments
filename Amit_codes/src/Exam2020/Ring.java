package Exam2020;

public class Ring {
    private Point_2D center;
    private double radiusE;
    private double radiusI;


    public Ring(Point_2D center , double radiusE, double radiusI)
    {
        this.center=new Point_2D(center);
        this.radiusE=radiusE;
        this.radiusI=radiusI;
    }
    public Ring(Ring r)
    {
        this(r.center,r.radiusE, r.radiusI);
    }

    public boolean isIn(Point_2D p)
    {
        double dist=p.distance(center);
        return dist>radiusI && dist<radiusE;
    }

    public boolean equals(Ring other)
    {
        return other.radiusE==this.radiusE&&other.radiusI==this.radiusI&& other.center.equals(center);
    }

}
