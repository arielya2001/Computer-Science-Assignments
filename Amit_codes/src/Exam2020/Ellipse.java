package Exam2020;

public class Ellipse {
    private Point_2D q,p;
    private int n;

    public Ellipse (Point_2D q,Point_2D p,int n)
    {
        this.q=new Point_2D(q);
        this.p=new Point_2D(p);
        this.n=n;
    }
    public Ellipse(int xP,int yP, int xQ,int yQ,int n)
    {
        this.q=new Point_2D(xP,yP);
        this.p=new Point_2D(xP,yP);
        this.n=n;
    }
    public Ellipse(Ellipse e)
    {
        this(e.p,e.q,e.n);
    }

    public int where(Point_2D r)
    {
        double dist1=r.distance(p);
        double dist2=r.distance(q);
        double sum=dist1+dist2;
        if (sum==n)
            return 0;
        if (sum>n)
            return 1;
        else return -1;
    }

    public boolean equals(Ellipse e)
    {
        if (p.equals(e.p)&&q.equals(e.q)&& n==e.n)
        {
            return true;
        }
        else return false;
    }
}
