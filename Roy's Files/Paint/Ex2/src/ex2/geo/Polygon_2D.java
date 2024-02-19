package ex2.geo;

import java.util.ArrayList;

public class Polygon_2D implements GeoShape{
	private ArrayList<Point_2D>	 vertex;
	private ArrayList<Point_2D> originalVertex;
	private double negativeOneScaleCount;

	public Polygon_2D() {this.vertex = new ArrayList<>(); this.originalVertex = new ArrayList<>();}

	public Polygon_2D(Polygon_2D po) {  // create new polygon using the same values of the current
		this.vertex = new ArrayList<>(po.vertex);
		this.originalVertex = new ArrayList<>(po.vertex);
	}

	public Point_2D[] getAllPoints() {
		Point_2D[] pointArray = new Point_2D[this.vertex.size()];  // for over all the vertexes
		for (int i = 0; i < this.vertex.size(); i++) {
			pointArray[i] = this.vertex.get(i); // add the vertex to a Points[] array
		}
		return pointArray;
	}

	public void add(Point_2D p) {this.vertex.add(p); this.originalVertex.add(p);} // use the ArrayList.add()

	@Override
	public String toString() {return vertex.toString();} // use the ArrayList.toString()

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

	@Override
	public double perimeter() {
		double totalP = 0;
		for (int i = 0; i < vertex.size() - 1; i++) { // for over al the vertexes and calculate the sum of the sides
			totalP += vertex.get(i + 1).distance(vertex.get(i));
		}

		return totalP;
	}

	@Override
	public void translate(Point_2D vec) {
		for (int i = 0; i < vertex.size(); i++) { // for over the vertexes and use the move method from Point_2D
			vertex.get(i).move(vec);
		}
	}

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

	@Override
	public void scale(Point_2D center, double ratio) {
		if (ratio == 1) {return;} // exit the method

		if (ratio == -1) {
			negativeOneScaleCount++;
			if (negativeOneScaleCount == 2) {
				// Restore to original scale and reset counter
				this.vertex.clear();
				for (Point_2D p : this.originalVertex) {
					this.vertex.add(new Point_2D(p));
				}
				negativeOneScaleCount = 0;
				return;
			}
		}
		// Reset the counter if -1 is not twice in a row
		else {negativeOneScaleCount = 0;}

		// scaling
		for (int i = 0; i < vertex.size(); i++) {
			vertex.get(i).scale(center, ratio);
		}
	}

	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		// use the rotate method from Point_2d to rotate all the vertexes
		for (int i = 0; i < vertex.size(); i++) {
			vertex.get(i).rotate(center, angleDegrees);
		}
	}

}
