package ex2.geo;

/**
 * This class represents a 2D circle in the plane.
 * @author Roy Naor
 * ID - 323917104
 */
public class Circle_2D implements GeoShape{
	private Point_2D _center;
	private double _radius;

	public Circle_2D(Point_2D center, double radius) {
		this._center = new Point_2D(center);
		this._radius = radius;
	}

	/**
	 * Copy constructor to create a new circle identical to the given one.
	 * @param c the circle to copy
	 */
	public Circle_2D(Circle_2D c) {
		this(c.getCenter(), c.getRadius());
	}

	/**
	 * Getter for the radius of the circle.
	 * @return the radius of the circle
	 */
	public double getRadius() {return this._radius;}

	/**
	 * Getter for the center of the circle.
	 * @return the center point of the circle
	 */
	public Point_2D getCenter(){ return _center;}

	/**
	 * Convert the circle to a string representation.
	 * @return string of the circle
	 */
	@Override
	public String toString() {return _center.toString()+", "+_radius;}

	/**
	 * Check if a given point is inside this circle.
	 * @param ot the point to check
	 * @return true if the point is inside the circle, false otherwise
	 */
	@Override
	public boolean contains(Point_2D ot) {
		//if the point is in the borders of the circle
		if (_center.distance(ot) - _radius > 0)
			return false;
		return true;
	}

	/**
	 * Calculate the area of the circle using the area formula.
	 * @return the area of the circle
	 */
	@Override
	public double area() {return Math.PI * Math.pow(_radius, 2);} // calculate the using the area formula

	/**
	 * Calculate the perimeter of the circle using the perimeter formula.
	 * @return the perimeter of the circle
	 */
	@Override
	public double perimeter() {return 2 * Math.PI * _radius;} // calculate the using the perimeter formula

	/**
	 * Translate the center of the circle by a given vector.
	 * @param vec the vector to move the center by
	 */
	@Override
	public void translate(Point_2D vec) {_center.move(vec);} // use the move method from Point_2d to move the center

	/**
	 * Create a copy of this circle.
	 * @return a new circle with the same center and radius
	 */
	@Override
	public GeoShape copy() {return new Circle_2D(this);} // use the second contractor method to create new object
	// with the same values as the current

	/**
	 * Scale the circle by a given ratio from a specified center.
	 * @param center the center point from which to scale
	 * @param ratio the ratio by which to scale the radius
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		_center.scale(center, ratio);
		_radius *= ratio;
	}

	/**
	 * Check if another object is equal to this circle.
	 * @param ob the object to compare with this circle
	 * @return true if the given object is a circle with the same center and radius, false otherwise
	 */
	@Override
	public boolean equals(Object ob) {
		if (ob.getClass() != this.getClass())
			return false;
		Point_2D ob_center = ((Circle_2D) ob).getCenter();
		double ob_radius = ((Circle_2D) ob).getRadius();
        return this._center.equals(ob_center) && this._radius == ob_radius;
    }

	/**
	 * Rotate the center of the circle around a given point by a specified angle in degrees.
	 * @param center the center point around which to rotate
	 * @param angleDegrees the angle to rotate the center by, in degrees
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this._center.rotate(center,angleDegrees);
	}
	// use the rotate method from Point_2d to rotate the center
}

