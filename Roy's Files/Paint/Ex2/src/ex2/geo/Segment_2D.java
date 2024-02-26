package ex2.geo;

/**
 * This class represents a 2D segment on the plane.
 * @author Roy Naor
 * ID - 323917104
 */
public class Segment_2D implements GeoShape{
	private Point_2D a, b;
	public Segment_2D(Point_2D a, Point_2D b) {
		this.a = new Point_2D(a);
		this.b = new Point_2D(b);
	}
	public Segment_2D(Segment_2D t1) {this(t1.a, t1.b);}


	public Point_2D get_p1() {return this.a;}
	public Point_2D get_p2() {return this.b;}

	/**
	 * Check if a given point is contained within the segment.
	 * @param ot the point to check
	 * @return true if the point lies on the segment, false otherwise
	 */
	@Override
	public boolean contains(Point_2D ot) {
		// Check if the sum of distances from 'ot' to endpoints 'a' and 'b' is approximately equal to the length of segment 'ab'.
		// This method assumes the segment is infinitesimally thin.
		if (this.a.distance(ot) + this.b.distance(ot) - this.a.distance(this.b) < 0.01)
			return true;  // The point 'ot' is close enough to be considered on the segment.
		return false;  // Otherwise, 'ot' is not on the segment.
	}

	/**
	 * Calculates the area of the segment.
	 * @return the area of the segment, which is always zero as a line segment does not have an area
	 */
	@Override
	public double area() {
		// A line segment has no area.
		return 0;
	}

	/**
	 * Calculates the perimeter of the segment.
	 * @return the length of the segment, considered here as the "perimeter"
	 */
	@Override
	public double perimeter() {
		// The "perimeter" of a segment is considered as its length here.
		return a.distance(b) * 2;
	}

	/**
	 * Translates both endpoints of the segment by a given vector.
	 * @param vec the vector by which to translate the endpoints
	 */
	@Override
	public void translate(Point_2D vec) {
		// Move both endpoints 'a' and 'b' of the segment by the vector 'vec'.
		a.move(vec);
		b.move(vec);
	}

	/**
	 * Creates a copy of this line segment.
	 * @return a new Segment_2D object with the same endpoints as the current segment
	 */
	@Override
	public GeoShape copy() {
		// Create and return a new Segment_2D object that is a copy of this segment.
		return new Segment_2D(new Point_2D(this.a), new Point_2D(this.b));
	}

	/**
	 * Scales the line segment by a given ratio from a specified center point.
	 * @param center the center point from which to scale
	 * @param ratio the ratio by which to scale the segment
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		// scaling
		a.scale(center, ratio);
		b.scale(center, ratio);
	}

	/**
	 * Compares this segment to another object for equality.
	 * @param ob the object to compare with this segment
	 * @return true if the specified object is a segment with the same endpoints, false otherwise
	 */
	@Override
	public boolean equals(Object ob) {
		if (ob.getClass() != this.getClass())
			return false;
		Point_2D ob_a = ((Segment_2D) ob).get_p1();
		Point_2D ob_b = ((Segment_2D) ob).get_p2();
		int count = 0;
		if (this.a.equals(ob_a) || this.a.equals(ob_b))
			count++;
		if (this.b.equals(ob_a) || this.b.equals(ob_b))
			count++;
		return (count == 2);
	}

	/**
	 * Rotates both endpoints of the segment around a given center by a specified angle.
	 * @param center the center point around which to rotate
	 * @param angleDegrees the angle by which to rotate the endpoints, in degrees
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		// use the rotate method from Point_2d to rotate the points
		a.rotate(center, angleDegrees);
		b.rotate(center, angleDegrees);
	}

	/**
	 * Converts the segment to a string representation.
	 * @return a string of the segment, formatted as 'endpointA, endpointB'
	 */
	@Override
	public String toString() {return a + ", " + b;}
}