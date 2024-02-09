package ex2.geo;

/**
 * This class represents a 2D axis parallel rectangle.
 * @author Roy Naor
 * ID - 323917104
 */
public class Rect_2D implements GeoShape {

	private Point_2D topLeft, bottomRight;
	private final Point_2D originalTopLeft;
	private final Point_2D originalBottomRight;
	private int negativeOneScaleCount = 0;
	private double width, length;

	public Point_2D getTopLeft() {return topLeft;}

	public Point_2D getBottomRight() {return bottomRight;}

	/** -calculateDimensions-
	 * calculate the Width and Length
	 */
	private void calculateDimensions() {
		this.length = Math.abs(bottomRight.x() - topLeft.x());
		this.width = Math.abs(bottomRight.y() - topLeft.y());
	}

	public Rect_2D(Point_2D topLeft, Point_2D bottomRight) {
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;
		this.originalTopLeft = topLeft;
		this.originalBottomRight = bottomRight;
		calculateDimensions();
	}

	/** -Rect_2D-
	 * Creates a new object of Rect_2D that is a copy of the specified Rect_2D object.
	 * creates a new object with the same properties.
	 * @param t1 - the Rect_2D object to copy.
	 */
	public Rect_2D(Rect_2D t1) {this(t1.topLeft, t1.bottomRight);}

	/** -contains-
	 * check if a Point (x,y) is in the area of the rectangle
	 * @param ot - a query 2D point
	 * @return True if the Point is in the area and False if not
	 */
	@Override
	public boolean contains(Point_2D ot) {
		return (ot.x() >= topLeft.x()) && (ot.x() <= bottomRight.x())
				&& ((ot.y() <= topLeft.y() && ot.y() >= bottomRight.y()) ||
				(ot.y() >= topLeft.y() && ot.y() <= bottomRight.y()));
	}

	/** -area-
	 * multiply the width with the length
	 * @return the value of the mul (the area of the rectangle)
	 */
	@Override
	public double area() {return Math.abs(width * length);}

	/** -perimeter-
	 *  sum twice the width and twice the length
	 * @return the value of the perimeter
	 */
	@Override
	public double perimeter() {return (2 * width) + (2 * length);}

	/** -translate-
	 * Move the rectangle by the vector 0,0-->vec
	 * @param vec - a vector from the 0,0
	 */
	@Override
	public void translate(Point_2D vec) {
		this.topLeft.move(vec);
		this.bottomRight.move(vec);
	}

	/** -copy-
	 * Computes a new (deep) copy of this rectangle
	 * @return a new rectangle object with the same properties
	 */
	@Override
	public GeoShape copy() {return new Rect_2D(new Point_2D(this.topLeft), new Point_2D(this.bottomRight));}

	/** -scale-
	 * Rescales the rectangle with respect to the given center point
	 * @param center - center point from which the rescaling is being done.
	 * @param ratio - the ratio of rescaling.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		if (ratio == 1) {return;} // exit the method

		if (ratio == -1) {
			negativeOneScaleCount++;
			if (negativeOneScaleCount == 2) {
				// Restore to original scale and reset counter
				this.topLeft = originalTopLeft;
				this.bottomRight = originalBottomRight;
				negativeOneScaleCount = 0;
				return;
			}
		}
		// Reset the counter if -1 is not twice in a row
		else {negativeOneScaleCount = 0;}

		// scaling
		topLeft.scale(center, ratio);
		bottomRight.scale(center, ratio);
		width *= ratio;
		length *= ratio;
	}

	/** -rotate-
	 * Rotates the rectangle with respect to the given center point by an angle.
	 * @param center - center point from which the rotation is being done.
	 * @param angleDegrees - the angle (in Degrees) the shape should be rotated by.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		if (angleDegrees == 0){return;}
		topLeft.rotate(center, angleDegrees);
		bottomRight.rotate(center, angleDegrees);
	}

	/** -toString-
	 * This method returns a String representing this shape,
	 * such that one can use this string for saving the shape into a text file.
	 * @return a String representing this shape
	 */
	@Override
	public String toString() {return topLeft + ", " + bottomRight;}




}