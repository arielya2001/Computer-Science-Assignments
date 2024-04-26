package ex2.geo;

import java.util.ArrayList;
import java.util.Arrays;

public class ShapeUtils {


    public static boolean sameClass(GeoShape s1, GeoShape s2) {
        if (s1 == null || s2 == null) {
            return false;
        }
        return s1.getClass().getSimpleName().equals(s2.getClass().getSimpleName());

    }

    public static int numOfClasses(GeoShape[] shape) {
        if (shape.length == 0 || shape == null) {
            return 0;

        }
        ArrayList<String> names = new ArrayList<>();
        for (GeoShape s : shape) {
            if (s != null) {
                String className = s.getClass().getSimpleName();
                if (!names.contains(className)) {
                    names.add(className);
                }
            }
        }
        return names.size();
    }


    public static ArrayList<GeoShape> biggerThan(double num, ArrayList<GeoShape> geoList) {
        if (geoList == null)
            return new ArrayList<>();
        ArrayList<GeoShape> checked = new ArrayList<>();
        for (GeoShape g : geoList) {
            if (g.area() >= num) {
                checked.add(g);
            }
        }
        return checked;
    }

    public static Polygon_2D newShape(ArrayList<GeoShape> geoList) {
        ArrayList<Point_2D> newPoly = new ArrayList<>();
        for (GeoShape shape : geoList) {
            if (shape instanceof Circle_2D) {
                Circle_2D c = (Circle_2D) shape;
                newPoly.add(c.getCenter());
            }
            if (shape instanceof Rect_2D) {
                Rect_2D r = (Rect_2D) shape;
                newPoly.add(r.getP1());
                newPoly.add(r.getP2());
                newPoly.add(r.getP3());
                newPoly.add(r.getP4());
            }
            if (shape instanceof Segment_2D) {
                Segment_2D s = (Segment_2D) shape;
                newPoly.add(s.get_p1());
                newPoly.add(s.get_p2());

            }
            if (shape instanceof Triangle_2D) {
                Triangle_2D t = (Triangle_2D) shape;
                newPoly.addAll(Arrays.asList(t.getAllPoints()));
            }
            if (shape instanceof Polygon_2D) {
                Polygon_2D p = (Polygon_2D) shape;
                newPoly.addAll(Arrays.asList(p.getAllPoints()));
            }
        }
        Polygon_2D res = new Polygon_2D();
        for (Point_2D p : newPoly) {
            res.add(p);
        }
        return res;
    }

    public static GeoShape bigPer(ArrayList<GeoShape> shapes) {
        if (shapes == null || shapes.isEmpty()) {
            return null;
        }
        double maxPer = Double.MIN_VALUE;
        GeoShape maxPerShape = null;
        for (GeoShape s : shapes) {
            if (s != null && s.perimeter() > maxPer) {
                maxPer = s.perimeter();
                maxPerShape = s;

            }
        }
        return maxPerShape;

    }

    public static ArrayList<GeoShape[]> splitToClasses(GeoShape[] shapes) {
        ArrayList<ArrayList<GeoShape>> listOfList = new ArrayList<>();

        for (GeoShape shape : shapes) {
            boolean found = false;
            for (ArrayList<GeoShape> list : listOfList) {
                if (!list.isEmpty() && list.get(0).getClass().getSimpleName().equals(shape.getClass().getSimpleName())) {
                    list.add(shape);
                    found = true;
                    break;
                }
            }
            if (!found) {
                ArrayList<GeoShape> newList = new ArrayList<>();
                newList.add(shape);
                listOfList.add(newList);
            }
        }
        ArrayList<GeoShape[]> result = new ArrayList<>();
        for (ArrayList<GeoShape> list : listOfList) {
            GeoShape[] array = new GeoShape[list.size()];
            list.toArray(array);
            result.add(array);
        }
        return result;
    }


    public static int countSame(ArrayList<GeoShape> a1, ArrayList<GeoShape> a2) {
        ArrayList<GeoShape> shapes = new ArrayList<>();
        if (a1 == null || a2 == null)
            return 0;
        for (GeoShape shape1 : a1) {
            for (GeoShape shape2 : a2) {
                if (shape1.equals(shape2) && !shapes.contains(shape1)) {
                    shapes.add(shape1);
                }
            }
        }
        return shapes.size();
    }

    public static ArrayList<GeoShape> areaAvg(ArrayList<GeoShape> s1) {
        if (s1 == null || s1.size() == 0)
            return new ArrayList<>();
        double sum = 0;
        int counter = 0;
        for (GeoShape shape : s1) {
            if (s1 != null) {
                sum += shape.area();
                counter++;
            }
        }
        double avg = sum / counter;

        ArrayList<GeoShape> shapesArea = new ArrayList<>();
        for (GeoShape shape : s1) {
            if (shape.area() > avg) {
                shapesArea.add(shape);
            }
        }
        return shapesArea;
    }


    public static double maxDist(Point_2D[] ps, Point_2D p)
    {
        double max=p.distance(ps[0]);
        for (int i = 1; i <ps.length; i++) {
            double temp=p.distance(ps[i]);
            if (temp>max)
                max=temp;

        }
        return max;
    }
    public static Circle_2D minContainingCircle(Point_2D[] ps)
    {
        double radius=maxDist(ps,ps[0]);
        Circle_2D minAreaCircle=new Circle_2D(ps[0],radius);
        for (int i = 1; i < ps.length ; i++)
        {
            double tempRadius=maxDist(ps,ps[i]);
            Circle_2D tempCircle=new Circle_2D(ps[i],tempRadius);
            if(tempCircle.area()<minAreaCircle.area()) {
                minAreaCircle = tempCircle;
            }
        }
        return minAreaCircle;
    }

    public static boolean containsClass(ArrayList<GeoShape> sh, GeoShape s) {
        boolean found=false;
        for (GeoShape shape:sh)
        {
            if (s.getClass().getSimpleName().equals(shape.getClass().getSimpleName()))
                found= true;
        }
        return found;
    }

    public static ArrayList<String> intersection(ArrayList<GeoShape> sh1, ArrayList<GeoShape> sh2) {
        ArrayList<String> inter = new ArrayList<>();
        for (GeoShape shape1 : sh1) {
            String shapeName = shape1.getClass().getSimpleName();
            if (containsClass(sh2, shape1) && !inter.contains(shapeName)) {
                inter.add(shapeName);
            }
        }
        return inter;
    }


    public static GeoShape findLargestSameClass(ArrayList<GeoShape>shapes,GeoShape s){
        double area=s.area();
        String shapeClass=s.getClass().getSimpleName();
        for (GeoShape shape1:shapes)
        {
            String findClass=shape1.getClass().getSimpleName();
            if (findClass.equals(shapeClass))
            {
                double tempArea=shape1.area();
                if (tempArea>area)
                {
                    s=shape1;
                }
            }
        }
        return s;
    }

    public static ArrayList<Double> shapesArea(ArrayList<GeoShape>shapes) {
        ArrayList<ArrayList<GeoShape>> listOfLists = new ArrayList<>();
        for (GeoShape currentShape : shapes) {
            boolean found = false;

            for (ArrayList<GeoShape> list : listOfLists) {
                if (!list.isEmpty() && list.get(0).getClass().getSimpleName().equals(currentShape.getClass().getSimpleName())) {
                    list.add(currentShape);
                    found = true;
                }
            }
            if (!found) {
                ArrayList<GeoShape> newList = new ArrayList<>();
                newList.add(currentShape);
                listOfLists.add(newList);
            }
        }
        ArrayList<Double> result = new ArrayList<>();

        for (ArrayList<GeoShape> list : listOfLists) {
            int sum = 0;
            for (GeoShape s : list) {
                sum += s.area();
            }
            double avg = sum / list.size();
            result.add(avg);

        }
        return result;
    }
    public static ArrayList<Double> maxDist (ArrayList<GeoShape>shapes)
    {

    }

    public static Point_2D findMin(GeoShape shape)
    {


    }






    }
























