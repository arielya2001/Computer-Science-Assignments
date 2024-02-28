package ex2.geo;

import java.util.ArrayList;
import java.util.Arrays;

public class Polygon_2D implements GeoShape{
	private ArrayList<Point_2D>	 vertex;

	public Polygon_2D() {this.vertex = new ArrayList<>();}

	public Polygon_2D(Polygon_2D po) {  // create new polygon using the same values of the current
		this.vertex = new ArrayList<>(po.vertex);
	}

	/**
	 * Retrieves all vertices of the polygon.
	 * @return an array of Point_2D objects representing the vertices of the polygon
	 */
	public Point_2D[] getAllPoints() {
		Point_2D[] pointArray = new Point_2D[this.vertex.size()];  // for over all the vertexes
		for (int i = 0; i < this.vertex.size(); i++) {
			pointArray[i] = this.vertex.get(i); // add the vertex to a Points[] array
		}
		return pointArray;
	}

	/**
	 * Adds a new vertex to the polygon if it is not already present.
	 * @param p the point to add as a new vertex
	 */
	public void add(Point_2D p) {
		if (!vertex.contains(p))
			this.vertex.add(p);
	} // use the ArrayList.add()

	/**
	 * Converts the polygon to a string representation.
	 * @return a string representation of the polygon
	 */
	@Override
	public String toString() {return vertex.toString();} // use the ArrayList.toString()

	/**
	 * Checks whether a given point is inside the polygon.
	 * @param ot the point to check
	 * @return true if the point is inside the polygon, false otherwise
	 */
	@Override
	public boolean contains(Point_2D ot) {
		double x = ot.x();
		double y = ot.y();
		boolean isCrossed = false;
		for (int i = 0; i < vertex.size(); i++) {
			// Get the x and y coordinates of the current vertex
			double ax = vertex.get(i).x();
			double ay = vertex.get(i).y();
			// Get the x and y coordinates of the next vertex
			double bx = vertex.get((i + 1) % vertex.size()).x();
			double by = vertex.get((i + 1) % vertex.size()).y();
			// Check if the point coincides with a vertex (corner) of the polygon
			if (x == ax && y == ay) {
				return true; // Point is a corner
			}
			// Check if the y-coordinate of the point is between the y-coordinates of the segment's endpoints
			if ((ay > y) != (by > y)) {
				double slope = (x - ax) * (by - ay) - (bx - ax) * (y - ay);
				if (slope == 0) {
					return true; // Point is on boundary
				}
				if ((slope < 0) != (by < ay)) {
					isCrossed = !isCrossed;
				}
			}
		}
		return isCrossed;
	}

	/**
	 * Calculates the area of the polygon using the Shoelace formula.
	 * @return the area of the polygon
	 */
	@Override
	public double area() {
		double area = 0.0;
		// Calculate the area using the Shoelace formula
		for (int i = 0; i < vertex.size(); i++) {
			// Current vertex
			double x1 = vertex.get(i).x();
			double y1 = vertex.get(i).y();

			// Next vertex
			double x2 = vertex.get((i + 1) % vertex.size()).x();
			double y2 = vertex.get((i + 1) % vertex.size()).y();

			area += (x1 * y2) - (x2 * y1);
		}
		return Math.abs(area / 2.0);
	}

	/**
	 * Calculates the perimeter of the polygon by summing the distances between consecutive vertices.
	 * @return the perimeter of the polygon
	 */
	@Override
	public double perimeter() {
		double totalP = 0;
		for (int i = 0; i < vertex.size() - 1; i++) { // for over al the vertexes and calculate the sum of the sides
			totalP += vertex.get(i + 1).distance(vertex.get(i));
		}

		return totalP;
	}

	/**
	 * Translates all vertices of the polygon by a given vector.
	 * @param vec the vector by which to translate the vertices
	 */
	@Override
	public void translate(Point_2D vec) {
		for (int i = 0; i < vertex.size(); i++) { // for over the vertexes and use the move method from Point_2D
			vertex.get(i).move(vec);
		}
	}

	/**
	 * Creates a deep copy of the polygon.
	 * @return a new Polygon_2D object with the same vertices as the current polygon
	 */
	@Override
	public GeoShape copy() {
		// use the second contractor method in Point_2D to create new object
		// with the same values as the current vertexes
		Polygon_2D copyPolygon = new Polygon_2D();
		for (Point_2D vertex : this.vertex) {
			copyPolygon.add(new Point_2D(vertex));
		}
		return copyPolygon;
	}

	/**
	 * Scales the polygon by a given ratio from a specified center point.
	 * @param center the center point from which to scale
	 * @param ratio the ratio by which to scale the polygon
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		// scaling
		for (int i = 0; i < vertex.size(); i++) {
			vertex.get(i).scale(center, ratio);
		}
	}

	/**
	 * Compares this polygon to another object for equality.
	 * @param ob the object to compare with this polygon
	 * @return true if the specified object is a polygon with the same vertices, false otherwise
	 */
	@Override
	public boolean equals(Object ob) {
		if (this == ob) return true; // the same polygon
		if (!(ob instanceof Polygon_2D)) return false; // the shape is not polygon
		Polygon_2D other = (Polygon_2D) ob; // create polygon because ob is not necessarily polygon
		int size = this.vertex.size();
		if (size != other.vertex.size()) return false; // the number of vertexes not equal

		int index = other.vertex.indexOf(this.vertex.get(0));
		if (index == -1) return false; // No matching start, not equal

		// Check normal order
		for (int i = 0; i < size; i++) {
			if (!this.vertex.get(i).equals(other.vertex.get((index + i) % size))) {
				// If normal order fails, check reverse order
				for (int j = 0; j < size; j++) {
					if (!this.vertex.get(j).equals(other.vertex.get((index - j + size) % size))) {
						return false; // Both orders fail, not equal
					}
				}
				return true; // Reverse order succeeds, they are equal
			}
		}
		return true; // Normal order succeeds, they are equal
	}

	/**
	 * Rotates all vertices of the polygon around a given center by a specified angle.
	 * @param center the center point around which to rotate
	 * @param angleDegrees the angle by which to rotate the vertices, in degrees
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		// use the rotate method from Point_2d to rotate all the vertexes
		for (int i = 0; i < vertex.size(); i++) {
			vertex.get(i).rotate(center, angleDegrees);
		}
	}

}
