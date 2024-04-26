package Exam2020;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    @Test
    void calculate() {
        double a=4;
        double b=3;
        Triangle triangle=new Triangle(a,b,new Point_2D(0,0));
        double c=triangle.calculate();
        assertEquals(5,c);
    }

    @Test
    void calculateAng() {
        double a=4;
        double b=3;
        Triangle triangle=new Triangle(a,b,new Point_2D(0,0));
        double c=triangle.calculate();
        int ang=(int) triangle.calculateAng();
        assertEquals(53,ang);

    }
    @Test
    void centerOfC()
    {
        double a=4;
        double b=3;
        Triangle triangle=new Triangle(a,b,new Point_2D(0,0));
        double c=triangle.calculate();
        Point_2D p=triangle.centerOfC();
        assertEquals(2.5,p.x());
        assertEquals(2,Math.ceil(p.y()));
        Point_2D newP=new Point_2D(2.5,2);


    }
}