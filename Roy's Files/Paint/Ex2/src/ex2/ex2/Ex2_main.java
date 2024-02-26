package ex2.ex2;

import ex2.geo.*;
import ex2.gui.Ex2;
import ex2.gui.GUIShape;
import ex2.gui.GUI_Shape;

import java.awt.*;
import java.awt.font.ShapeGraphicAttribute;
import java.util.Comparator;

/**
 * This class is a very simple main for starting with Ex2
 * You may update this file, but do noteL your Ex2 will be run using our own "main" class
 * Make sure you test the loadDemo(); function once you have implemented it (do test the "save" as well)
 */
public class Ex2_main {

	public static void main(String[] args) {
		simpleShapes();
		//loadDemo();
	}

	// Two simple GeoShapes
	public static void simpleShapes() {
		Ex2 ex2 = Ex2.getInstance();
		GUI_Shape_Collection shapes = ex2.getShape_Collection();

		Point_2D p1 = new Point_2D(6.0, 2.0);
		double radius = 1.0;
		double radius2 = 2.0;
		double radius3 = 3.0;

		Circle_2D c1 = new Circle_2D(p1, radius);
		Circle_2D c2 = new Circle_2D(p1, radius2);
		Circle_2D c3 = new Circle_2D(p1, radius3);

		GUI_Shape gs1 = new GUIShape(c1, true, Color.blue, 1);
		GUI_Shape gs2 = new GUIShape(c2, true, Color.red, 2);
		GUI_Shape gs3 = new GUIShape(c3, true, Color.green, 3);

		System.out.println(c2.toString().compareTo(c1.toString()));
		System.out.println(c3.toString().compareTo(c2.toString()));
		System.out.println(c2.toString().compareTo(c3.toString()));

		shapes.add(gs1);
		shapes.add(gs2);
		shapes.add(gs3);

		ex2.init(shapes);
		ex2.show();
	}


	// Loads a file from file a1.txt.
	public static void loadDemo() {
		Ex2 ex2 = Ex2.getInstance();
		GUI_Shape_Collection shapes = ex2.getShape_Collection();
		String file = "a0.txt"; //make sure the file is your working directory.
		shapes.load(file);
		ex2.init(shapes);
		ex2.show();
	}

}
