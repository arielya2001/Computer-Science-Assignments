package ex2.geo;

/**
 * This class represents a 2D segment on the plane.
 * @author Roy Naor
 * ID - 323917104
 */
public class Segment_2D implements GeoShape{
	private Point_2D a, b;
	private final Point_2D originalA;
	private final Point_2D originalB;
	private int negativeOneScaleCount = 0;
	public Segment_2D(Point_2D a, Point_2D b) {
		this.a = new Point_2D(a);
		this.b = new Point_2D(b);
		this.originalA = new Point_2D(a);
		this.originalB = new Point_2D(b);
	}
	public Segment_2D(Segment_2D t1) {this(t1.a, t1.b);}


	public Point_2D get_p1() {return this.a;}
	public Point_2D get_p2() {return this.b;}

	@Override
	public boolean contains(Point_2D ot) {
		// Check if the sum of distances from 'ot' to endpoints 'a' and 'b' is approximately equal to the length of segment 'ab'.
		// This method assumes the segment is infinitesimally thin.
		if (this.a.distance(ot) + this.b.distance(ot) - this.a.distance(this.b) < 0.01)
			return true;  // The point 'ot' is close enough to be considered on the segment.
		return false;  // Otherwise, 'ot' is not on the segment.
	}

	@Override
	public double area() {
		// A line segment has no area.
		return 0;
	}

	@Override
	public double perimeter() {
		// The "perimeter" of a segment is considered as its length here.
		return a.distance(b) * 2;
	}

	@Override
	public void translate(Point_2D vec) {
		// Move both endpoints 'a' and 'b' of the segment by the vector 'vec'.
		a.move(vec);
		b.move(vec);
	}

	@Override
	public GeoShape copy() {
		// Create and return a new Segment_2D object that is a copy of this segment.
		return new Segment_2D(new Point_2D(this.a), new Point_2D(this.b));
	}

	@Override
	public void scale(Point_2D center, double ratio) {
		if (ratio == 1) {return;} // exit the method

		if (ratio == -1) {
			negativeOneScaleCount++;
			if (negativeOneScaleCount == 2) {
				// Restore to original scale and reset counter
				this.a.set_x(originalA.x());
				this.a.set_y(originalA.y());
				this.b.set_x(originalB.x());
				this.b.set_y(originalB.y());
				negativeOneScaleCount = 0;
				return;
			}
		}
		// Reset the counter if -1 is not twice in a row
		else {negativeOneScaleCount = 0;}

		// scaling
		a.scale(center, ratio);
		b.scale(center, ratio);
	}

	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		// use the rotate method from Point_2d to rotate the points
		a.rotate(center, angleDegrees);
		b.rotate(center, angleDegrees);
	}

	@Override
	public String toString() {return a + ", " + b;}
}