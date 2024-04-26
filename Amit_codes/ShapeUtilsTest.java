package ex2.geo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ShapeUtilsTest {
    Point_2D p1=new Point_2D(2,3);
    Point_2D p2=new Point_2D(5,4);
    Point_2D p3=new Point_2D(2,1);
    Point_2D p4=new Point_2D(0,7);
    Point_2D p5=new Point_2D(8,4);
    Point_2D p6=new Point_2D(10, 10);
    Point_2D p7=new Point_2D(9,2);
    Point_2D p8=new Point_2D(3,3);

    Circle_2D c1=new Circle_2D(p1,5);
    Rect_2D r1=new Rect_2D(p1,p5);
    Rect_2D r2=new Rect_2D(p5,p7);

    Segment_2D s1=new Segment_2D(p2,p3);
    Polygon_2D pol1=new Polygon_2D();
    Triangle_2D t1=new Triangle_2D(p3,p4,p5);



    @Test
    void sameClass()
    {
        assertFalse(ShapeUtils.sameClass(s1,r1));
        assertTrue(ShapeUtils.sameClass(r1,r2));
    }

    @Test
    void numOfClasses()
    {
        GeoShape[]arr={r1,r2,s1,c1,pol1};
        int size=ShapeUtils.numOfClasses(arr);
        assertEquals(4,size);
    }
    @Test
    void biggerThen()
    {
        ArrayList<GeoShape>shapes=new ArrayList<>();
        shapes.add(r1);
        shapes.add(r2);
        shapes.add(t1);
        shapes.add(c1);
        ArrayList<GeoShape>shapes1=new ArrayList<>();
        ArrayList<GeoShape>res=ShapeUtils.biggerThan(7,shapes);
        ArrayList<GeoShape>res1=ShapeUtils.biggerThan(7,shapes1);

        assertEquals(2,res.size());
        assertEquals(new ArrayList<>(),res1);
    }

    @Test
    void newShape()
    {
        ArrayList<GeoShape> shapes=new ArrayList<>();
        shapes.add(c1);
        shapes.add(s1);
        Polygon_2D p=new Polygon_2D();
        p.add(c1.getCenter());
        p.add(s1.get_p1());
        p.add(s1.get_p2());
        Polygon_2D res=ShapeUtils.newShape(shapes);
        assertTrue(p.equals(res));
    }
    @Test
    void newPer()
    {
        ArrayList<GeoShape> shapes=new ArrayList<>();
        shapes.add(c1);
        shapes.add(r1);
        shapes.add(r2);
        GeoShape p=ShapeUtils.bigPer(shapes);
        assertTrue(p instanceof Circle_2D);

    }
    @Test
    void shapesArea()
    {
        ArrayList<GeoShape> shapes=new ArrayList<>();
        shapes.add(c1);
        shapes.add(r1);
        shapes.add(r2);
        ArrayList<Double>area=ShapeUtils.shapesArea(shapes);
        assertEquals(2,area.size());
        System.out.println(area.get(0));
        System.out.println(c1.area());
        System.out.println(r1.area());
        System.out.println(r2.area());
        System.out.println(area.get(1));




    }

}