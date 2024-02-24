package ex2.gui;
/**
 * This class implements the GUI_shape.
 * Ex2: you should implement this class!
 * Note! - I have changed only the "GUIShape(String s)" function.
 * @author I2CS and Ariel Ya'acobi
 */
import ex2.geo.*;
import java.awt.*;
import java.util.ArrayList;


public class GUIShape implements GUI_Shape{
	private GeoShape _g = null;
	private boolean _fill;
	private Color _color;
	private int _tag;
	private boolean _isSelected;

	/**
	 * Constructs a new GUI shape with the specified geometric shape, fill status, color, and tag.
	 * @param g The geometric shape associated with the GUI shape.
	 * @param f The fill status of the GUI shape.
	 * @param c The color of the GUI shape.
	 * @param t The tag of the GUI shape.
	 */
	public GUIShape(GeoShape g, boolean f, Color c, int t) {
		_g = null;
		if (g != null) {
			_g = g.copy();
		}
		_fill = f;
		_color = c;
		_tag = t;
		_isSelected = false;
	}

	/**
	 * Constructs a new GUI shape by copying another GUI shape.
	 * @param ot The GUI shape to be copied.
	 */
	public GUIShape(GUIShape ot) {
		this(ot._g, ot._fill, ot._color, ot._tag);
	}

	/**
	 * GUIShape Constructor
	 * Parses a string representation of a geometric shape and initializes the corresponding GeoShape object.
	 * @param s the string representation of the geometric shape
	 */
	public GUIShape(String s) {
		String[] prop = s.split(",");
		String shapeType = prop[4];

		switch (shapeType) {
			case "Circle_2D":
				_g = new Circle_2D(new Point_2D(Double.parseDouble(prop[5]), Double.parseDouble(prop[6])), Double.parseDouble(prop[7]));
				break;

			case "Polygon_2D":
				Polygon_2D p = new Polygon_2D();
				for (int i = 5; i + 1 < prop.length; i += 2) {
					p.add(new Point_2D(Double.parseDouble(prop[i]), Double.parseDouble(prop[i + 1])));
				}
				_g = p;
				break;

			case "Rect_2D":
				double xP1 = Double.parseDouble(prop[5]);
				double yP1 = Double.parseDouble(prop[6]);
				double xP3 = Double.parseDouble(prop[7]);
				double yP3 = Double.parseDouble(prop[8]);
				double xP2 = Double.parseDouble(prop[9]);
				double yP2 = Double.parseDouble(prop[10]);
				double xP4 = Double.parseDouble(prop[11]);
				double yP4 = Double.parseDouble(prop[12]);
				boolean isFilled = Boolean.parseBoolean(prop[2]);
				int tag = Integer.parseInt(prop[3]);
				Color color = Color.decode(prop[1]);
				Point_2D p1 = new Point_2D(xP1, yP1);
				Point_2D p2 = new Point_2D(xP2, yP2);
				Point_2D p3 = new Point_2D(xP3, yP3);
				Point_2D p4 = new Point_2D(xP4, yP4);
				Rect_2D r = new Rect_2D(p1, p2, p3, p4);
				_g = r; _fill = isFilled; _color = color; _tag = tag;_isSelected = false;
				break;

			case "Segment_2D":
				_g = new Segment_2D(new Point_2D(Double.parseDouble(prop[5]), Double.parseDouble(prop[6])), new Point_2D(Double.parseDouble(prop[7]), Double.parseDouble(prop[8])));
				break;

			case "Triangle_2D":
				ArrayList<Point_2D> pointList = new ArrayList<>();
				for (int i = 5; i < prop.length; i += 2) {
					pointList.add(new Point_2D(Double.parseDouble(prop[i]), Double.parseDouble(prop[i + 1])));
				}
				_g = new Triangle_2D(pointList.get(0), pointList.get(1), pointList.get(2));
				break;

			default:
				_g = null; // Handle unrecognized shape types
				break;
		}

		if (_g != null) {
			_fill = Boolean.parseBoolean(prop[2]);
			_tag = Integer.parseInt(prop[3]);
			_color = Color.decode(prop[1]);
			_isSelected = false;
		}
	}


	/**
	 * Retrieves the geometric shape associated with this GUI shape.
	 * @return The GeoShape object representing the geometric shape.
	 */
	@Override
	public GeoShape getShape() {
		return _g;
	}

	/**
	 * Sets the geometric shape associated with this GUI shape.
	 * @param g The GeoShape object to be set.
	 */
	@Override
	public void setShape(GeoShape g) {
		_g = g;
	}

	/**
	 * Checks if the GUI shape is filled with color.
	 * @return True if the GUI shape is filled, false otherwise.
	 */
	@Override
	public boolean isFilled() {
		return _fill;
	}

	/**
	 * Sets the fill status of the GUI shape.
	 * @param filled True to fill the shape with color, false otherwise.
	 */
	@Override
	public void setFilled(boolean filled) {
		_fill = filled;
	}

	/**
	 * Retrieves the color of the GUI shape.
	 * @return The color of the GUI shape.
	 */
	@Override
	public Color getColor() {
		return _color;
	}

	/**
	 * Sets the color of the GUI shape.
	 * @param cl The color to be set.
	 */
	@Override
	public void setColor(Color cl) {
		_color = cl;
	}

	/**
	 * Retrieves the tag associated with the GUI shape.
	 * @return The tag of the GUI shape.
	 */
	@Override
	public int getTag() {
		return _tag;
	}

	/**
	 * Sets the tag associated with the GUI shape.
	 * @param tag The tag to be set.
	 */
	@Override
	public void setTag(int tag) {
		_tag = tag;
	}

	/**
	 * Creates a deep copy of the GUI shape.
	 * @return A new GUI shape object with identical properties and state.
	 */
	@Override
	public GUI_Shape copy() {
		return new GUIShape(this);
	}

	/**
	 * Generates a string representation of the GUI shape.
	 * @return A string containing the class name, color, fill status, tag, shape type, and shape details.
	 */
	@Override
	public String toString() {
		return String.format("%s,%d,%b,%d,%s,%s", this.getClass().getSimpleName(), _color.getRGB(), _fill, _tag, _g.getClass().getSimpleName(), _g.toString());
	}

	/**
	 * Checks if the GUI shape is currently selected.
	 * @return True if the GUI shape is selected, false otherwise.
	 */
	@Override
	public boolean isSelected() {
		return _isSelected;
	}

	/**
	 * Sets the selection status of the GUI shape.
	 * @param s True to select the GUI shape, false to deselect.
	 */
	@Override
	public void setSelected(boolean s) {
		_isSelected = s;
	}
}
