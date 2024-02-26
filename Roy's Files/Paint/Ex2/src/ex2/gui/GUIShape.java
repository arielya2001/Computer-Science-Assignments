package ex2.gui;
/**
 * This class implements the GUI_shape.
 * @author I2CS + Roy Naor
 * ID - 323917104
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
	
	public GUIShape(GeoShape g, boolean f, Color c, int t) {
		_g = null;
		if (g!=null) {_g = g.copy();}
		_fill= f;
		_color = c;
		_tag = t;
		_isSelected = false;
	}
	public GUIShape(GUIShape ot) {
		this(ot._g, ot._fill, ot._color, ot._tag);
	}
	public GUIShape(String s) {
		String[] prop = s.split(",");
		if ("Rect_2D".equals(prop[4])) {
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
		}
		if ("Circle_2D".equals(prop[4])) {
			double x = Double.parseDouble(prop[5]);
			double y = Double.parseDouble(prop[6]);
			double radius = Double.parseDouble(prop[7]);
			boolean isFilled = Boolean.parseBoolean(prop[2]);
			int tag = Integer.parseInt(prop[3]);
			Color color = Color.decode(prop[1]);
			Point_2D cen = new Point_2D(x, y);
			Circle_2D c = new Circle_2D(cen, radius);
			_g = c; _fill = isFilled; _color = color; _tag = tag;_isSelected = false;
		}
		if ("Triangle_2D".equals(prop[4])) {
			ArrayList<Point_2D> vertex = new ArrayList<>();
			for (int i = 5; i < prop.length; i += 2) {
				double x = Double.parseDouble(prop[i]);
				double y = Double.parseDouble(prop[i + 1]);
				vertex.add(new Point_2D(x, y));
			}

			Triangle_2D t = new Triangle_2D(vertex.get(0), vertex.get(1), vertex.get(2));

			boolean isFilled = Boolean.parseBoolean(prop[2]);
			int tag = Integer.parseInt(prop[3]);
			Color color = Color.decode(prop[1]);
			_g = t; _fill = isFilled; _color = color; _tag = tag;_isSelected = false;
		}
		if ("Segment_2D".equals(prop[4])) {
			double xA = Double.parseDouble(prop[5]);
			double yA = Double.parseDouble(prop[6]);
			double xB = Double.parseDouble(prop[7]);
			double yB = Double.parseDouble(prop[8]);

			Point_2D A = new Point_2D(xA, yA);
			Point_2D B = new Point_2D(xB, yB);

			boolean isFilled = Boolean.parseBoolean(prop[2]);
			int tag = Integer.parseInt(prop[3]);
			Color color = Color.decode(prop[1]);

			Segment_2D seg = new Segment_2D(A, B);
			_g = seg; _fill = isFilled; _color = color; _tag = tag;_isSelected = false;
		}
		if ("Polygon_2D".equals(prop[4])) {
			Polygon_2D p = new Polygon_2D();
			for (int i = 5; i + 1 < prop.length; i += 2) {
				double x = Double.parseDouble(prop[i]);
				double y = Double.parseDouble(prop[i + 1]);
				p.add(new Point_2D(x, y));
			}

			boolean isFilled = Boolean.parseBoolean(prop[2]);
			int tag = Integer.parseInt(prop[3]);
			Color color = Color.decode(prop[1]);
			_g = p; _fill = isFilled; _color = color; _tag = tag;_isSelected = false;
		}

		}
	@Override
	public GeoShape getShape() {
		return _g;
	}

	@Override
	public void setShape(GeoShape g) {
		_g = g;
	}

	@Override
	public boolean isFilled() {
		return _fill;
	}

	@Override
	public void setFilled(boolean filled) {
		_fill = filled;
	}

	@Override
	public Color getColor() {
		return _color;
	}

	@Override
	public void setColor(Color cl) {
		_color = cl;
	}

	@Override
	public int getTag() {
		return _tag;
	}

	@Override
	public void setTag(int tag) {
		_tag = tag;
		
	}

	@Override
	public GUI_Shape copy() {
		GUI_Shape cp = new GUIShape(this);
		return cp;
	}
	@Override
	public String toString() {
		String ans = ""+this.getClass().getSimpleName()+","+_color.getRGB()+","+_fill+","+_tag+","+this._g.getClass().getSimpleName()+","+_g.toString();
		return ans;
	}
	@Override
	public boolean isSelected() {
		return this._isSelected;
	}
	@Override
	public void setSelected(boolean s) {
		this._isSelected = s;
	}
}
