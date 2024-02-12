package ex2.ex2;

import ex2.geo.*;
import ex2.gui.GUIShape;
import ex2.gui.GUI_Shape;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * This class represents a collection of GUI_Shape.
 * Ex2: you should implement this class!
 * @author Roy Naor
 * ID - 323917104
 */
public class ShapeCollection implements GUI_Shape_Collection {
	private ArrayList<GUI_Shape> _shapes;
	
	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shape>();
	}

	/** -get-
	 * This method return a reference to the i'th element in the collection.
	 * @param i - the index of the element
	 * @return a reference (NOT a copy) for the i'th element in the collection.
	 */
	@Override
	public GUI_Shape get(int i) {
		return _shapes.get(i);
	}

	/** -size-
	 * @return the size of the collection (if empty return 0).
	 */
	@Override
	public int size() {
		return _shapes.size();
	}

	/** -remove-
	 * This method remove (and return) the gui_shape at the i'th location.
	 * After removing the size of this collection decreases (by 1)
	 * and the order (of the elements) remains the same - just without the removed element.
	 * @param i - the index of the element to be removed.
	 * @return the gui_shape which was removed
	 */
	@Override
	public GUI_Shape removeElementAt(int i) {return this._shapes.remove(i);}

	/** -addAt-
	 * This method adds the gui_element s to this collection in the i'th position.
	 * Note: the method adds s "as is" (NOT a new copy of s).
	 * @param s - the gui_shape
	 * @param i - the location (index) in which s should be added
	 */
	@Override
	public void addAt(GUI_Shape s, int i) {
		if(s!=null && s.getShape()!=null) {
			this._shapes.add(i, s);
		}
	}

	/** -add-
	 * This method adds the gui_element s to this collection in the last position.
	 * Note: the method adds s "as is" (NOT a new copy of s).
	 * @param s - the gui_shape
	 */
	@Override
	public void add(GUI_Shape s) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s);
		}
	}

	/** -copy-
	 * This method constructs a deep copy of this collection.
	 * Note: the two collections are equal - yet they have no shared memory.
	 * @return a new deep copy of the collection
	 */
	public ShapeCollection copy() {
		ShapeCollection copyCollection = new ShapeCollection();
		for (GUI_Shape shape : this._shapes) {
			copyCollection.add(shape.copy());
		}
		return copyCollection;
	}

	@Override
	public void sort(Comparator<GUI_Shape> comp) {
		this._shapes.sort(comp);
	}

	/** -removeAll-
	 * This method remove all the gui_shapes at once.
	 * After removing the size of this collection is 0
	 */
	@Override
	public void removeAll() {this._shapes.clear();}

	@Override
	public void save(String file_name) {
		String content = this._shapes.toString().replace("[", "").replace("]", "");
		String modifiedContent = content.replaceAll("(?<!^)(GUIShape)", "\n$1");
		String path = file_name + ".txt";
		try {
			Files.writeString(Paths.get(path), modifiedContent.trim());
			System.out.println("File saved successfully.");
		} catch (Exception e) {
			System.err.println("Error writing to file: " + e.getMessage());
		}
	}

	@Override
	public void load(String file) {
		try {
			String path = file;
			String content = new String(Files.readAllBytes(Paths.get(path)));
			String[] string_shapes = content.split("\n"); // split into the shapes
			System.out.println(Arrays.toString(string_shapes));
			for (String shape : string_shapes) {
				String[] prop = shape.split(","); // split into the properties of every shape
				System.out.println(Arrays.toString(prop));
				if ("Rect_2D".equals(prop[4])) {
					continue;
				}
				if ("Circle_2D".equals(prop[4])) {
					double x = Double.parseDouble(prop[5]);
					double y = Double.parseDouble(prop[6]);
					double radius = Double.parseDouble(prop[7]);
					boolean isFilled = Boolean.parseBoolean(prop[2]);
					int tag = Integer.parseInt(prop[3]);
					Point_2D cen = new Point_2D(x, y);
					Circle_2D c = new Circle_2D(cen, radius);
					GUI_Shape gs = new GUIShape(c, isFilled, Color.decode(prop[1]), tag);
					this._shapes.add(gs);
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

					GUI_Shape gs = new GUIShape(t, isFilled, color, tag);
					this._shapes.add(gs);
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

					Segment_2D t = new Segment_2D(A, B);

					GUI_Shape gs = new GUIShape(t, isFilled, color, tag);
					this._shapes.add(gs);
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

					GUI_Shape gs = new GUIShape(p, isFilled, color, tag);
					this._shapes.add(gs);
				}
				System.out.println(Arrays.toString(prop));
			}
			//GUI_Shape gs = new GUIShape()

		} catch (IOException e) {
			System.err.println("Cannot read the file: " + e.getMessage());
		}
	}

	/** -toString-
	 * This method returns a String representing this ArrayList,
	 * such that one can use this string for saving the shape into a text file.
	 * @return a String representing this ArrayList
	 */
	@Override
	public String toString() {
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += this.get(i);
		}
		return ans;
	}
	

}
