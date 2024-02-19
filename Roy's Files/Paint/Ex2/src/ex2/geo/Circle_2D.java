package ex2.geo;

/**
 * This class represents a 2D circle in the plane.
 * @author Roy Naor
 * ID - 323917104
 */
public class Circle_2D implements GeoShape{
	private Point_2D _center;
	private double _radius;

	private final double originalRadius;
	private final Point_2D originalCenter;
	private int negativeOneScaleCount = 0;

	public Circle_2D(Point_2D center, double radius) {
		this._center = new Point_2D(center);
		this._radius = radius;
		this.originalCenter = new Point_2D(center);
		this.originalRadius = radius;
	}
	public Circle_2D(Circle_2D c) {
		this(c.getCenter(), c.getRadius());
	}
	public double getRadius() {return this._radius;}
	public Point_2D getCenter(){ return _center;}

	@Override
	public String toString() {return _center.toString()+", "+_radius;}

	@Override
	public boolean contains(Point_2D ot) {
		//if the point is in the borders of the circle
		if (_center.distance(ot) - _radius > 0)
			return false;
		return true;
	}

	@Override
	public double area() {return Math.PI * Math.pow(_radius, 2);} // calculate the using the area formula

	@Override
	public double perimeter() {return 2 * Math.PI * _radius;} // calculate the using the perimeter formula

	@Override
	public void translate(Point_2D vec) {_center.move(vec);} // use the move method from Point_2d to move the center

	@Override
	public GeoShape copy() {return new Circle_2D(this);} // use the second contractor method to create new object
	// with the same values as the current


	@Override
	public void scale(Point_2D center, double ratio) {
		if (ratio == 1) {return;} // exit the method

		if (ratio == -1) {
			negativeOneScaleCount++;
			if (negativeOneScaleCount == 2) { // ratio is -1 twice in a row
				// Restore to original scale and reset counter
				this._radius = originalRadius;
				this._center.set_x(originalCenter.x());
				this._center.set_y(originalCenter.y());
				negativeOneScaleCount = 0;
				return;
			}
		}
		// Reset the counter if -1 is not twice in a row
		else {negativeOneScaleCount = 0;}

		// scaling
		_center.scale(center, ratio);
		_radius *= ratio;
	}

	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this._center.rotate(center,angleDegrees);
	}
	// use the rotate method from Point_2d to rotate the center

}
