package ex2.geo;

import java.util.Arrays;
import java.util.List;

/**
 * This class represents a 2D axis parallel rectangle.
 * @author Roy Naor
 * ID - 323917104
 */
public class Rect_2D implements GeoShape {

	private Point_2D p1, p2, p3, p4;

	public Point_2D getP1() {return p1;}

	public Point_2D getP2() {return p2;}

	public Point_2D getP3() {return p3;}

	public Point_2D getP4() {return p4;}

	public Rect_2D(Point_2D p1, Point_2D p2) {
		this.p1 = new Point_2D(p1);
		this.p2 = new Point_2D(p2);
		this.p3 = new Point_2D(p2.x(), p1.y());
		this.p4 = new Point_2D(p1.x(), p2.y());
	}
public Rect_2D(Point_2D p1, Point_2D p2, Point_2D p3, Point_2D p4) {
		this.p1 = new Point_2D(p1);
		this.p2 = new Point_2D(p2);
		this.p3 = new Point_2D(p3);
		this.p4 = new Point_2D(p4);

	}

	public Rect_2D(Rect_2D t1) {this(t1.p1, t1.p2);}

	@Override
	public boolean contains(Point_2D ot) {
		// Check if p1's y-coordinate is less than p2's y-coordinate
		if (this.p1.y() < this.p2.y()) {
			// Check if the y-coordinate of point 'ot' is between p1's y and p2's y
			if (this.p1.y() <= ot.y() && ot.y() <= this.p2.y()) {
				// Check if p1's x-coordinate is less than p2's x-coordinate
				if (this.p1.x() < this.p2.x()) {
					// Check if the x-coordinate of point 'ot' is between p1's x and p2's x
					if (this.p1.x() <= ot.x() && ot.x() <= this.p2.x())
						return true; // Point 'ot' is within the rectangle
				} else if (this.p2.x() <= ot.x() && ot.x() <= this.p1.x())
					return true; // Point 'ot' is within the rectangle for the case where p2's x-coordinate
				// is less than p1's x-coordinate
			}
		}
		else {
			// If p2's y-coordinate is less than p1's, then check if 'ot's y-coordinate is between them
			if (this.p2.y() <= ot.y() && ot.y() <= this.p1.y()) {
				// Again, check the relative positioning of p1's and p2's x-coordinates and 'ot's x-coordinate
				if (this.p1.x() < this.p2.x()) {
					if (this.p1.x() <= ot.x() && ot.x() <= this.p2.x())
						return true; // 'ot' falls within the rectangle
				} else {
					// The case where p1's x-coordinate is greater than p2's x-coordinate
					if (this.p2.x() <= ot.x() && ot.x() <= this.p1.x())
						return true; // 'ot' falls within the rectangle
				}
			}
		}
		return false; // If none of the conditions are met, 'ot' does not fall within the rectangle
	}


	@Override
	public double area() {
		Point_2D bottomLeft = new Point_2D(p1.x(), p2.y()); // use the rectangle area formula
		// find the width and length and mul them
		return this.p1.distance(bottomLeft) * this.p2.distance(bottomLeft);
	}

	@Override
	public double perimeter() {return (2 * this.p2.distance(p4)) +
			(2 * this.p1.distance(p4));} // use the rectangle perimeter formula
	// find the 2 of width and 2 of length and sum the m

	@Override
	public void translate(Point_2D vec) {
		// use the move method from Point_2d to move all the rectangle points
		this.p1.move(vec);
		this.p2.move(vec);
		this.p3.move(vec);
		this.p4.move(vec);
	}

	@Override
	public GeoShape copy() {
		// use the second contractor method to create new object
		// with the same values as the current
		Rect_2D copyRect = new Rect_2D(new Point_2D(this.p1), new Point_2D(this.p2));
		copyRect.p3 = new Point_2D(this.p3);
		copyRect.p4 = new Point_2D(this.p4);
		return copyRect;
	}

	@Override
	public void scale(Point_2D center, double ratio) {
		// scaling
		p1.scale(center, ratio);
		p2.scale(center, ratio);
		p3.scale(center, ratio);
		p4.scale(center, ratio);
	}

	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		if (angleDegrees == 0){return;}
		p1.rotate(center, angleDegrees);
		p2.rotate(center, angleDegrees);
		p3.rotate(center, angleDegrees);
		p4.rotate(center, angleDegrees);
	}

	@Override
	public boolean equals(Object ob) {
		if (this == ob) return true;
		if (ob == null || getClass() != ob.getClass()) return false;

		Rect_2D other = (Rect_2D) ob;

		Point_2D[] thisPoints = {this.p1, this.p2, this.p3, this.p4};
		Point_2D[] otherPoints = {other.getP1(), other.getP2(), other.getP3(), other.getP4()};

		List<Point_2D> thisPointsList = Arrays.asList(thisPoints);
		List<Point_2D> otherPointsList = Arrays.asList(otherPoints);

		return thisPointsList.containsAll(otherPointsList) && otherPointsList.containsAll(thisPointsList);
	}

	@Override
	public String toString() {return p1 + "," + p3 + "," + p2 + "," + p4;}
	// use the rotate method from Point_2d to rotate all the points of the rectangle

}
