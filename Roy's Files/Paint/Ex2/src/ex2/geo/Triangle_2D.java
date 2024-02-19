package ex2.geo;

/**
 * This class represents a 2D Triangle in the plane.
 * @author Roy Naor
 * ID - 323917104
 */
public class Triangle_2D implements GeoShape{
	private Point_2D p1, p2, p3;
	private final Point_2D originalP1;
	private final Point_2D originalP2;
	private final Point_2D originalP3;
	public int negativeOneScaleCount = 0;
	public double height;

	public Triangle_2D(Point_2D p1, Point_2D p2, Point_2D p3) {
		this.p1 = new Point_2D(p1);
		this.p3 = new Point_2D(p3);
		this.p2 = new Point_2D(p2);
		this.originalP1 = new Point_2D(p1);
		this.originalP2 = new Point_2D(p2);
		this.originalP3 = new Point_2D(p3);
	}

	public Triangle_2D(Triangle_2D t1) {this(t1.p1, t1.p2, t1.p3);}

	public Point_2D[] getAllPoints() {
		// Return an array containing all points of the triangle.
		return new Point_2D[] {p1, p2, p3};
	}

	@Override
	public boolean contains(Point_2D ot) {
		// Calculate barycentric coordinates to determine if point 'ot' is inside the triangle.
		double areaTotal, area1, area2, area3;
		areaTotal = this.area(); // Total area of the triangle

		// Calculate areas of sub-triangles formed with 'ot' and two of the triangle's vertices.
		area1 = Math.abs((ot.x() - p3.x()) * (p2.y() - p3.y()) - (ot.y() - p3.y()) * (p2.x() - p3.x())) / areaTotal;
		area2 = Math.abs((p1.x() - ot.x()) * (p2.y() - ot.y()) - (p1.y() - ot.y()) * (p2.x() - ot.x())) / areaTotal;
		area3 = 1 - area1 - area2; // Remaining area portion

		// Point 'ot' is inside the triangle if all barycentric coordinates are between 0 and 1.
		return area1 >= 0 && area1 <= 1 && area2 >= 0 && area2 <= 1 && area3 >= 0 && area3 <= 1;
	}

	@Override
	public double area() {
		// Calculate the area of the triangle using determinant method (Shoelace formula).
		return Math.abs((p1.x() - p3.x()) * (p2.y() - p3.y()) - (p1.y() - p3.y()) * (p2.x() - p3.x())) / 2.0;
	}

	@Override
	public double perimeter() {
		// Calculate the perimeter by summing the distances between all three vertices.
		double distance1 = p1.distance(p2);
		double distance2 = p1.distance(p3);
		double distance3 = p3.distance(p2);
		return distance1 + distance2 + distance3; // Sum of all sides lengths
	}

	@Override
	public void translate(Point_2D vec) {
		// Move all points of the triangle by the translation vector 'vec'.
		this.p1.move(vec);
		this.p3.move(vec);
		this.p2.move(vec);
	}

	@Override
	public GeoShape copy() {
		// Create and return a new Triangle_2D object that is a copy of this triangle.
		return new Triangle_2D(this); // Assumes Triangle_2D has a copy constructor
	}

	@Override
	public void scale(Point_2D center, double ratio) {
		if (ratio == 1) {return;} // exit the method

		if (ratio == -1) {
			negativeOneScaleCount++;
			if (negativeOneScaleCount == 2) {
				// Restore to original scale and reset counter
				this.p1.set_x(originalP1.x());
				this.p2.set_x(originalP2.x());
				this.p3.set_x(originalP3.x());
				this.p1.set_y(originalP1.x());
				this.p2.set_y(originalP2.y());
				this.p3.set_y(originalP3.x());
				negativeOneScaleCount = 0;
				return;
			}
		}
		// Reset the counter if -1 is not twice in a row
		else {negativeOneScaleCount = 0;}

		// scaling
		p1.scale(center, ratio);
		p2.scale(center, ratio);
		p3.scale(center, ratio);
	}

	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		// use the rotate method from Point_2d to rotate all the points of the triangle
		this.p1.rotate(center, angleDegrees);
		this.p2.rotate(center, angleDegrees);
		this.p3.rotate(center, angleDegrees);
	}

	@Override
	public String toString() {return p1 + ", " + p2 + ", " + p3;}
}
