package ex2.geo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represents a 2D Triangle in the plane.
 * @author Roy Naor
 * ID - 323917104
 */
public class Triangle_2D implements GeoShape{
	private Point_2D p1, p2, p3;

	/**
	 * Constructor to initialize a triangle with three distinct points.
	 * @param p1 the first point of the triangle
	 * @param p2 the second point of the triangle
	 * @param p3 the third point of the triangle
	 */
	public Triangle_2D(Point_2D p1, Point_2D p2, Point_2D p3) {
		this.p1 = new Point_2D(p1);
		this.p3 = new Point_2D(p3);
		this.p2 = new Point_2D(p2);
	}

	/**
	 * Copy constructor to create a new triangle identical to an existing one.
	 * @param t1 the triangle object to copy from
	 */
	public Triangle_2D(Triangle_2D t1) {this(t1.p1, t1.p2, t1.p3);}

	/**
	 * Returns all three points of the triangle as an array.
	 * @return an array containing all three points of the triangle
	 */
	public Point_2D[] getAllPoints() {
		// Return an array containing all points of the triangle.
		return new Point_2D[] {p1, p2, p3};
	}

	/**
	 * Checks whether a given point is inside the triangle.
	 * This method uses the Barycentric coordinate system approach.
	 * @param ot the point to check for containment within the triangle
	 * @return true if the point is inside the triangle; false otherwise
	 */
	@Override
	public boolean contains(Point_2D ot) {
		double area1=new Triangle_2D(ot,p2,p3).area();
		double area2=new Triangle_2D(p1,ot,p3).area();
		double area3=new Triangle_2D(p1,p2,ot).area();
		double bary1 = area1 / this.area();
		double bary2 = area2 / this.area();
		double bary3 = area3 / this.area();
		return bary1 >= 0 && bary2 >= 0 && bary3 >= 0 &&
				(bary1 + bary2 + bary3) <= 1;
	}

	/**
	 * Calculates the area of the triangle using the Shoelace formula.
	 * @return the area of the triangle
	 */
	@Override
	public double area() {
		// Calculate the area of the triangle using determinant method (Shoelace formula).
		return Math.abs((p1.x() - p3.x()) * (p2.y() - p3.y()) - (p1.y() - p3.y()) * (p2.x() - p3.x())) / 2.0;
	}

	/**
	 * Calculates the perimeter of the triangle by summing the lengths of its sides.
	 * @return the perimeter of the triangle
	 */
	@Override
	public double perimeter() {
		// Calculate the perimeter by summing the distances between all three vertices.
		double distance1 = p1.distance(p2);
		double distance2 = p1.distance(p3);
		double distance3 = p3.distance(p2);
		return distance1 + distance2 + distance3; // Sum of all sides lengths
	}

	/**
	 * Translates the triangle by a given vector. This shifts all three points of the triangle.
	 * @param vec the vector by which to translate the triangle
	 */
	@Override
	public void translate(Point_2D vec) {
		// Move all points of the triangle by the translation vector 'vec'.
		this.p1.move(vec);
		this.p3.move(vec);
		this.p2.move(vec);
	}

	/**
	 * Creates a copy of this triangle.
	 * @return a new instance of Triangle_2D with identical vertices
	 */
	@Override
	public GeoShape copy() {
		// Create and return a new Triangle_2D object that is a copy of this triangle.
		return new Triangle_2D(this); // Assumes Triangle_2D has a copy constructor
	}

	/**
	 * Scales the triangle from a specific point by a given ratio. All three points of the triangle are scaled.
	 * @param center the center point from which the scaling is applied
	 * @param ratio the factor by which the triangle is scaled
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		// scaling
		p1.scale(center, ratio);
		p2.scale(center, ratio);
		p3.scale(center, ratio);
	}

	/**
	 * Determines whether another object is equal to this triangle.
	 * Checks if all three points of the triangles are equal.
	 * @param ob the object to compare with this triangle
	 * @return true if the specified object is a triangle with the same points; false otherwise
	 */
	@Override
	public boolean equals(Object ob) {
		if (ob.getClass() != this.getClass())
			return false;
		int count = 0;
		Point_2D[] expected = this.getAllPoints();
		Point_2D[] actual = ((Triangle_2D) ob).getAllPoints();
		for (int i = 0; i < expected.length; i++){
			for(int j = 0; j < actual.length; j++)
			{
				if (expected[i].equals(actual[j]))
					count++;
			}
		}
		return (count == 3);
	}

	/**
	 * Rotates all three points of the triangle around a specified center by a given angle.
	 * @param center the center point around which the triangle is rotated
	 * @param angleDegrees the angle in degrees by which the triangle is rotated
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		// use the rotate method from Point_2d to rotate all the points of the triangle
		this.p1.rotate(center, angleDegrees);
		this.p2.rotate(center, angleDegrees);
		this.p3.rotate(center, angleDegrees);
	}

	/**
	 * Converts the triangle into a string.
	 * @return a string representing the triangle, formatted as 'point1, point2, point3'
	 */
	@Override
	public String toString() {return p1 + ", " + p2 + ", " + p3;}
}
