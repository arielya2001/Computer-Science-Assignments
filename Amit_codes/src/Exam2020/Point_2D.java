package Exam2020;


public class Point_2D {
    //public static final double EPS1 = 0.001, EPS2 = Math.pow(EPS1,2), EPS=EPS2;
    public static final Point_2D ORIGIN = new Point_2D(0, 0);
    private double _x, _y;

    public Point_2D(double x, double y) {
        _x = x;
        _y = y;
    }

    public Point_2D(Point_2D p) {
        this(p.x(), p.y());
    }

    public Point_2D(String s) {
        try {
            String[] a = s.split(",");
            _x = Double.parseDouble(a[0]);
            _y = Double.parseDouble(a[1]);
        } catch (IllegalArgumentException e) {
            System.err.println("ERR: got wrong format string for Point2D init, got:" + s + "  should be of format: x,y");
            throw (e);
        }
    }

    public double x() {
        return _x;
    }

    public double y() {
        return _y;
    }

    public int ix() {
        return (int) _x;
    }

    public int iy() {
        return (int) _y;
    }

    public Point_2D add(Point_2D p) {
        Point_2D a = new Point_2D(p.x() + x(), p.y() + y());
        return a;
    }

    public String toString() {
        return _x + "," + _y;
    }

    public double distance() {
        return this.distance(ORIGIN);
    }

    public double distance(Point_2D p2) {
        double dx = this.x() - p2.x();
        double dy = this.y() - p2.y();
        double t = (dx * dx + dy * dy);
        return Math.sqrt(t);
    }



    public void sortPoint(Point_2D[]arr,Point_2D point)
    {
        boolean hasChanged=true;
        for (int i = arr.length-1; i >0&&hasChanged; i--)
        {
            hasChanged=false;
            for (int j = 0; j < i; j++)
            {
                if (arr[j].distance(point)>arr[j+1].distance(point))
                {
                    Point_2D temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                    hasChanged=true;
                }
            }
        }
    }
}