package ex2.geo;

import java.util.ArrayList;

public class RegularPolygon extends Polygon_2D{
    private Polygon_2D p;

    public RegularPolygon(Point_2D center,double radius, int n)
    {
        this.p=new Polygon_2D();
        double ang=Math.toRadians(360/n);


        for (int i = 0; i < n; i++)
        {
            double currentAng=i*ang;
            double x=center.x()+radius*Math.cos(currentAng);
            double y=center.y()+radius*Math.sin(currentAng);
            p.add(new Point_2D(x,y));
        }

    }



//    public static ArrayList<RegularPolygon> createPolygonNetwork(int numPolygons, int numSides, double sideLength)
//    {
//        ArrayList<RegularPolygon>polygons=new ArrayList<>();
//        if (numPolygons<1||numSides<3)
//            return polygons;
//
//        double radius=sideLength/(Math.sin(Math.PI/numPolygons)*2);
//        double angle=360/numSides;
//
//        RegularPolygon firstPolygon=new RegularPolygon(new Point_2D(0,0),radius,numSides);
//        polygons.add(firstPolygon);
//
//        RegularPolygon lastPolygon=firstPolygon;
//
//        for (int i = 0; i <numSides; i++)
//        {
//            RegularPolygon newPolygon=(RegularPolygon) lastPolygon.copy();
//            newPolygon.rotate()
//
//
//        }
//    }

    }
