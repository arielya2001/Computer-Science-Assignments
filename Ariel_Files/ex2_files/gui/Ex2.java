package ex2.gui;

import ex2.ex2.Ex2_Const;
import ex2.ex2.GUI_Shape_Collection;
import ex2.ex2.ShapeCollection;
import ex2.geo.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * This class is a simple "interlayer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a
 * "Singleton-like" implementation.
 *
 * Assignment 2 README:
 * This project implements a comprehensive set of classes and interfaces for geometric shapes, shape containers, a basic
 * GUI, and basic save/load capabilities.
 * The main objective is to provide a user-friendly drawing interface, made for required graphical geometric shapes:
 * circles, rectangles, polygons, and segments within a 2D space.
 * It allows for efficient visualization, manipulation, and persistence of geometric objects through operations such as:
 * adding, removing, sorting, modifying, and saving/loading the shape collection from files.
 * The Ex2 class serves as the core component of the project, facilitating the creation of a drawing interface
 * where users can interactively create, modify, and visualize geometric shapes. Each shape is represented as a
 * GUI_Shape object, offering functionalities like drawing, coloring, and filling. The interface enables users to
 * perform various actions such as mouse clicks and button presses to manipulate shapes, apply transformations that was
 * elaborated before, Additionally, the project includes JUnit tests to ensure the correctness of implemented
 * functionalities, ensuring robustness and reliability. These tests cover various aspects of the implementation,
 * including tests for each shape, the Ex2 class, and the GUIShape class, ensuring that the application functions as
 * intended across different scenarios and inputs.
 *
 * Sources used in the assignment:
 * Wikipedia -
 * Stream method in java: <a href="https://www.geeksforgeeks.org/stream-in-java/">...</a>
 * Shoelace formula: <a href="https://en.wikipedia.org/wiki/Shoelace_formula">...</a>
 * Rotate & scale: <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#atan2-double-double-">...</a>
 * Polygon: <a href="https://en.wikipedia.org/wiki/Polygon">...</a>
 * polygon contains a point: <a href="https://en.wikipedia.org/wiki/Point_in_polygon">...</a>
 * Files handling in java: <a href="https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html">...</a>
 * JUnit5 guide: <a href="https://www.vogella.com/tutorials/JUnit/article.html">...</a>
 * ChatGPT 3.5 - for ray casting, Shoelace formula, Barycentric coordinate system and spelling mistakes
 *
 * @author boaz.ben-moshe And Ariel Ya'acobi
 * ID - 318727187
 */
public class Ex2 implements Ex2_GUI {
    private GUI_Shape_Collection _shapes = new ShapeCollection();
    public GUI_Shape _gs;
    public Polygon_2D _pp;
    public Color _color = Color.blue;
    public boolean _fill = false;
    public String _mode = "";
    public Point_2D _p1;

    private static Ex2 _winEx2 = null;

    private Ex2() {
        init(null);
    }

    /**
     * Initializes the Ex2 instance with the given GUI shape collection.
     * If the given collection is null, a new one is created.
     * @param s The GUI shape collection to initialize with.
     */
    public void init(GUI_Shape_Collection s) {
        if (s == null) {
            _shapes = new ShapeCollection();
        } else {
            _shapes = s;
        }
        GUI_Shape _gs = null;
        Polygon_2D _pp = null;
        _color = Color.blue;
        _fill = false;
        _mode = "";
        Point_2D _p1 = null;
    }
    /**
     * Shows the GUI with the given scale.
     * @param d The scale to set for the GUI.
     */
    public void show(double d) {
        StdDraw_Ex2.setScale(0, d);
        StdDraw_Ex2.show();
        drawShapes();
    }
    /**
     * Returns the instance of the Ex2 class.
     * If the instance does not exist, it creates one.
     * @return The Ex2 instance.
     */
    public static Ex2 getInstance() {
        if (_winEx2 == null) {
            _winEx2 = new Ex2();
        }
        return _winEx2;
    }

    /* private static void drawGrid(int x, int y, int delta) {
         for(int i=0;i<x;i+=delta) {
             StdDraw_Ex2.line(i, 0, i, y);
         }
         for(int i=0;i<y;i+=delta) {
             StdDraw_Ex2.line(0, i, x, i);
         }
    } */
    /**
     * Draws all the shapes in the GUI.
     */
    public void drawShapes() {
        StdDraw_Ex2.clear();
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape sh = _shapes.get(i);
            System.out.println(sh);
            drawShape(sh);
        }
        if (_gs != null) {
            drawShape(_gs);
        }
        StdDraw_Ex2.show();
    }

    /**
     * Draws a given GUI shape on the GUI canvas. I added "if" statement for rectangle.
     * @param g The GUI shape to draw.
     */
    private static void drawShape(GUI_Shape g) {
        StdDraw_Ex2.setPenColor(g.getColor());
        if (g.isSelected()) {
            StdDraw_Ex2.setPenColor(Color.gray);
        }
        GeoShape gs = g.getShape();
        boolean isFill = g.isFilled();
        if (gs instanceof Circle_2D) {
            Circle_2D c = (Circle_2D) gs;
            Point_2D cen = c.getCenter();
            double rad = c.getRadius();
            if (isFill) {
                StdDraw_Ex2.filledCircle(cen.x(), cen.y(), rad);
            } else {
                StdDraw_Ex2.circle(cen.x(), cen.y(), rad);
            }
        }
        if (gs instanceof Segment_2D) {
            Segment_2D c = (Segment_2D) gs;
            Point_2D m0 = c.get_p1();
            Point_2D m1 = c.get_p2();
            StdDraw_Ex2.line(m0.x(), m0.y(), m1.x(), m1.y());
        }
        Point_2D[] ps = null;
        if (gs instanceof Polygon_2D) {
            ps = ((Polygon_2D) gs).getAllPoints();
        }
        if(gs instanceof Rect_2D){
            Rect_2D r= (Rect_2D) gs;
            ps=new Point_2D[]{r.getP1(),r.getP3(),r.getP2(),r.getP4()};
        }
        if (gs instanceof Triangle_2D) {
            ps = ((Triangle_2D) gs).getAllPoints();
        }
        if (ps != null) {
            double[] xx = new double[ps.length];
            double[] yy = new double[ps.length];
            for (int i = 0; i < xx.length; i++) {
                xx[i] = ps[i].x();
                yy[i] = ps[i].y();
            }
            if (isFill) {
                StdDraw_Ex2.filledPolygon(xx, yy);
            } else {
                StdDraw_Ex2.polygon(xx, yy);
            }
        }
    }
    /**
     * Sets the color of selected shapes to the specified color.
     * @param c The color to set for selected shapes.
     */
    public void setColor(Color c) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            if (s.isSelected()) {
                s.setColor(c);
            }
        }
    }
    /**
     * Sets the fill property of selected shapes.
     */
    public void setFill() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            if (s.isSelected()) {
                s.setFilled(_fill);
            }
        }
    }
    /**
     * Removes selected shapes from the GUI.
     */
    public void remove() {
        for (int i = _shapes.size() - 1; i >= 0; i--) {
            GUI_Shape s = _shapes.get(i);
            if (s.isSelected()) {
                _shapes.removeElementAt(i);
            }
        }
    }
    /**
     * Performs actions based on the input string representing different operations.
     * @param p The input string representing the action to perform.
     */
    public void actionPerformed(String p) {
        _mode = p;
        if (p.equals("Blue")) {
            _color = Color.BLUE;
            setColor(_color);
        }
        if (p.equals("Red")) {
            _color = Color.RED;
            setColor(_color);
        }
        if (p.equals("Green")) {
            _color = Color.GREEN;
            setColor(_color);
        }
        if (p.equals("White")) {
            _color = Color.WHITE;
            setColor(_color);
        }
        if (p.equals("Black")) {
            _color = Color.BLACK;
            setColor(_color);
        }
        if (p.equals("Yellow")) {
            _color = Color.YELLOW;
            setColor(_color);
        }
        if (p.equals("Fill")) {
            _fill = true;
            setFill();
        }
        if (p.equals("Empty")) {
            _fill = false;
            setFill();
        }
        if (p.equals("Remove")) {
            remove();
        }
        if (p.equals("Save")) {
            save();
        }
        if (p.equals("Load")) {
            load();
        }
        if (p.equals("ByArea")) {
            _shapes.sort(ShapeComp.CompByArea);
        }
        if (p.equals("ByAntiArea")) {
            _shapes.sort(ShapeComp.CompByAntiArea);
        }
        if (p.equals("ByPerimeter")) {
            _shapes.sort(ShapeComp.CompByPerimeter);
        }
        if (p.equals("ByAntiPerimeter")) {
            _shapes.sort(ShapeComp.CompByAntiPerimeter);
        }
        if (p.equals("ByTag")) {
            _shapes.sort(ShapeComp.CompByTag);
        }
        if (p.equals("ByAntiTag")) {
            _shapes.sort(ShapeComp.CompByAntiTag);
        }
        if (p.equals("Clear")) {
            _shapes.removeAll();
        }
        if (p.equals("None")) {
            selectNone();
        }
        if (p.equals("All")) {
            selectAll();
        }
        if (p.equals("Anti")) {
            selectAnti();
        }
        if (p.equals("Info")) {
            System.out.println(getInfo());
        }

        drawShapes();

    }
    /**
     * Saves the current state of the GUI to a text file.
     */
    private void save() {
        FileDialog chooser = new FileDialog(StdDraw_Ex2.getFrame(), "Save to Text file", FileDialog.SAVE);
        chooser.setVisible(true);
        String filename = chooser.getFile();
        if (filename != null) {
            _shapes.save(chooser.getDirectory() + File.separator + chooser.getFile());
        }
    }
    /**
     * Loads a previously saved state of the GUI from a text file.
     */
    private void load() {
        FileDialog chooser = new FileDialog(StdDraw_Ex2.getFrame(), "Load from Text file", FileDialog.LOAD);
        chooser.setVisible(true);
        String filename = chooser.getFile();
        if (filename != null) {
            _shapes.load(chooser.getDirectory() + File.separator + chooser.getFile());
        }
    }
    /**
     * Handles mouse click events on the GUI.
     * @param p The coordinates of the mouse click.
     */
    public void mouseClicked(Point_2D p) {
        System.out.println("Mode: " + _mode + "  " + p);
        if (_mode.equals("Rect") || _mode.equals("Circle") || _mode.equals("Segment")) {
            if (_gs == null) {
                _p1 = new Point_2D(p);
            } else {
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                _shapes.add(_gs);
                _gs = null;
                _p1 = null;
            }
        }
        if (_mode.equals("Move")) {
            if (_p1 == null) {
                _p1 = new Point_2D(p);
            } else {
                _p1 = new Point_2D(p.x() - _p1.x(), p.y() - _p1.y());
                move();
                _p1 = null;
            }
        }
        if (_mode.equals("Copy")) {
            if (_p1 == null) {
                _p1 = new Point_2D(p);
            } else {
                _p1 = new Point_2D(p.x() - _p1.x(), p.y() - _p1.y());
                copy();
                _p1 = null;
            }
        }
        if (_mode.equals("Rotate")) {
            if (_p1 == null) {
                _p1 = new Point_2D(p);
            } else {
                rotate(p);
                _p1 = null;
            }
        }


        if (_mode.equals("Polygon")) {
            if (_pp == null) {
                _pp = new Polygon_2D();
            }
            _p1 = new Point_2D(p);
            _pp.add(p);
        }

        if (_mode.equals("Triangle")) {
            if (_pp == null) {
                _pp = new Polygon_2D();
            }
            _p1 = new Point_2D(p);
            _pp.add(p);
            if (_pp.getAllPoints().length == 3) {
                Point_2D[] pp = _pp.getAllPoints();
                Triangle_2D tt = new Triangle_2D(pp[0], pp[1], pp[2]);
                GUI_Shape s = new GUIShape(tt, _fill, _color, 0);
                _shapes.add(s);
                _pp = null;
                _p1 = null;
                _gs = null;
            }
        }
        if (_mode.equals("Point")) {
            select(p);
        }
        //
        if (_mode.equals("Scale_90%")) {
            scale(p, 0.9);
        }
        if (_mode.equals("Scale_110%")) {
            scale(p, 1.10);
        }
        drawShapes();
    }
    /**
     * Scales the selected shapes around a specified point by a given ratio.
     * @param p The center point of scaling.
     * @param ratio The ratio of scaling.
     */
    public void scale(Point_2D p, double ratio) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (s.isSelected() && g != null) {
                g.scale(p, ratio);
            }
        }
    }
    /**
     * Selects or deselects a shape based on whether the given point is contained within it.
     * @param p The point to check for containment.
     */
    public void select(Point_2D p) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (g != null && g.contains(p)) {
                s.setSelected(!s.isSelected());
            }
        }
    }
    /**
     * Moves the selected shapes by a specified vector.
     */
    public void move() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (s.isSelected() && g != null) {
                g.translate(_p1);
            }
        }
    }
    /**
     * Copies and translates the selected shapes by a specified vector.
     */
    public void copy() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            if (s.isSelected()) {
                GUI_Shape s1 = s.copy();
                GeoShape g = s1.getShape();
                g.translate(_p1);
                _shapes.add(s1);
            }
        }
    }
    /**
     * Rotates the selected shapes around a specified point by a given angle.
     * @param ang The point to rotate the shapes around.
     */
    public void rotate(Point_2D ang) {
        double dx = ang.x() - _p1.x();
        double dy = ang.y() - _p1.y();
        double da = Math.atan2(dy, dx);
        da = Math.toDegrees(da);
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (s.isSelected()) {
                g.rotate(_p1, da);
            }
        }
    }
    /**
     * Selects all shapes.
     */
    public void selectAll() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            s.setSelected(true);
        }
    }
    /**
     * Prints information about the selected shapes.
     */
    //printInfo
    public void printInfo() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            if (s.isSelected()) {
                System.out.println(i + ") " + s.toString());
            }
        }
    }
    /**
     * Deselects all shapes.
     */
    public void selectNone() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            s.setSelected(false);
        }
    }
    /**
     * Selects or deselects all shapes.
     */
    public void selectAnti() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            s.setSelected(!s.isSelected());
        }
    }
    /**
     * Handles right-click event by adding a polygon shape if in polygon mode.
     * Clears temporary variables used for drawing shapes.
     * Calls drawShapes() to update the GUI.
     * @param p The point of the right-click event.
     */
    public void mouseRightClicked(Point_2D p) {
        if (_mode.equals("Polygon") && _pp != null) {
            GUIShape s = new GUIShape(_pp, _fill, _color, 0);
            _shapes.add(s);
        }
        _pp = null;
        _gs = null;
        _p1 = null;
        drawShapes();


    }
    /**
     * Handles mouse movement event by updating the shape being drawn if in drawing mode.
     * Calls drawShapes() to update the GUI.
     * @param e The mouse event.
     */
    public void mouseMoved(MouseEvent e) {
        if (_p1 != null) {
            double x1 = StdDraw_Ex2.mouseX();
            double y1 = StdDraw_Ex2.mouseY();
            GeoShape gs = null;
            //	System.out.println("M: "+x1+","+y1);
            Point_2D p = new Point_2D(x1, y1);
            if (_mode.equals("Circle")) {
                double r = _p1.distance(p);
                gs = new Circle_2D(_p1, r);
            }
            if (_mode.equals("Rect")) {
                gs = new Rect_2D(_p1, p);
            }
            if (_mode.equals("Segment")) {
                gs = new Segment_2D(_p1, p);
            }

            if (_mode.equals("Polygon") || _mode.equals("Triangle")) {
                if (_pp == null) {
                    _pp = new Polygon_2D();
                    _pp.add(_p1);
                }
                Polygon_2D gg = new Polygon_2D(_pp);
                gg.add(p);
                gs = gg;
            }
            _gs = new GUIShape(gs, false, Color.pink, 0);
            drawShapes();
        }
    }
    /**
     * Retrieves the shape collection.
     * @return The shape collection.
     */
    @Override
    public GUI_Shape_Collection getShape_Collection() {
        // TODO Auto-generated method stub
        return this._shapes;
    }
    /**
     * Shows the GUI with default dimensions.
     */
    @Override
    public void show() {
        show(Ex2_Const.DIM_SIZE);
    }

    /**
     * Retrieves information about the shapes.
     * @return Information about the shapes.
     */
    @Override
    public String getInfo() {
        // TODO Auto-generated method stub
        String ans = "";
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            ans += s.toString() + "\n";
            //	ans +=s.toString()+"  area: "+s.getShape().area()+"  per: "+s.getShape().perimeter()+"\n";
        }
        return ans;
    }
}

