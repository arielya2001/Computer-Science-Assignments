package ex2.geo;

/**
 * This class represents a 2D Triangle in the plane.
 * @author Roy Naor
 * ID - 323917104
 */
public class Triangle_2D implements GeoShape{
	public Point_2D top, rightBottom, leftBottom;
	private final Point_2D originalTop;
	private final Point_2D originalRightBottom;
	private final Point_2D originalLeftBottom;
	public int negativeOneScaleCount = 0;
	public double height;

	public Triangle_2D(Point_2D top, Point_2D rightBottom, Point_2D leftBottom) {
		this.top = top;
		this.leftBottom = leftBottom;
		this.rightBottom = rightBottom;
		this.originalTop = new Point_2D(top.x(), top.y());
		this.originalRightBottom = new Point_2D(rightBottom.x(), rightBottom.y());
		this.originalLeftBottom = new Point_2D(leftBottom.x(), leftBottom.y());

		calculateHeight();
	}

	/** -calculateHeight-
	 * calculate the height using the top and right Points
	 */
	public void calculateHeight(){this.height = Math.abs((top.y() - rightBottom.y()));}

	/** -Triangle_2D-
	 * Creates a new object of Triangle_2D that is a copy of the specified Triangle_2D object.
	 * creates a new object with the same properties.
	 * @param t1 - the Triangle_2D object to copy
	 */
	public Triangle_2D(Triangle_2D t1) {this(t1.top, t1.rightBottom, t1.leftBottom);}

	/** -getAllPoints-
	 * @return a new Points array with the Top, right and left points of the triangle
	 */
	public Point_2D[] getAllPoints() {return new Point_2D[] {top, rightBottom, leftBottom};}

	/** -contains-
	 * checking if a Point2D is in the triangles borders using "The barycentric coordinates" formula
	 * @param ot - a query 2D point
	 * @return boolean True if the Point2D is in and False if not
	 */
	@Override
	public boolean contains(Point_2D ot) {
		double areaTotal, area1, area2, area3;
		areaTotal = this.area();

		area1 = Math.abs((ot.x() - leftBottom.x()) * (rightBottom.y() - leftBottom.y()) -
				(ot.y() - leftBottom.y()) * (rightBottom.x() - leftBottom.x())) / areaTotal;
		area2 = Math.abs((top.x() - ot.x()) * (rightBottom.y() - ot.y()) -
				(top.y() - ot.y()) * (rightBottom.x() - ot.x())) / areaTotal;
		area3 = 1 - area1 - area2;

		return area1 >= 0 && area1 <= 1 && area2 >= 0 && area2 <= 1 && area3 >= 0 && area3 <= 1;
	}

	/** -area-
	 * calculates the area of the triangle using the coordinates of its vertices.
	 * @return the value of the mul (the area of the triangle)
	 */
	@Override
	public double area() {return Math.abs((top.x() - leftBottom.x()) *
			(rightBottom.y() - leftBottom.y()) - (top.y() - leftBottom.y()) * (rightBottom.x() - leftBottom.x()));}

	/** -perimeter-
	 *  sum the 3 vectors created from the 3 Points
	 * @return the value of the perimeter
	 */
	@Override
	public double perimeter() {
		double distance1 = top.distance(rightBottom);
		double distance2 = top.distance(leftBottom);
		double distance3 = leftBottom.distance(rightBottom);
		return (int)(distance1 + distance2 + distance3);
	}

	/** -translate-
	 * Move the triangle by the vector 0,0-->vec
	 * @param vec - a vector from the 0,0
	 */
	@Override
	public void translate(Point_2D vec) {
		this.top.move(vec);
		this.leftBottom.move(vec);
		this.rightBottom.move(vec);
	}

	/** -copy-
	 * Computes a new (deep) copy of this triangle
	 * @return a new triangle object with the same properties
	 */
	@Override
	public GeoShape copy() {return new Triangle_2D(this);}

	/** -scale-
	 * Rescales the triangle with respect to the given center point
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
				this.top = originalTop;
				this.rightBottom = originalRightBottom;
				this.leftBottom = originalLeftBottom;
				negativeOneScaleCount = 0;
				return;
			}
		}
		// Reset the counter if -1 is not twice in a row
		else {negativeOneScaleCount = 0;}

		// scaling
		top.scale(center, ratio);
		rightBottom.scale(center, ratio);
		leftBottom.scale(center, ratio);
		calculateHeight();
	}

	/** -rotate-
	 * Rotates the triangle with respect to the given center point by an angle.
	 * @param center - center point from which the rotation is being done.
	 * @param angleDegrees - the angle (in Degrees) the shape should be rotated by.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this.top.rotate(center, angleDegrees);
		this.rightBottom.rotate(center, angleDegrees);
		this.leftBottom.rotate(center, angleDegrees);
	}

	/** -toString-
	 * This method returns a String representing this shape,
	 * such that one can use this string for saving the shape into a text file.
	 * @return a String representing this shape
	 */
	@Override
	public String toString() {return top + ", " + rightBottom + ", " + leftBottom;}
}
