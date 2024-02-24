package ex2.ex2;

import ex2.gui.GUIShape;
import ex2.gui.GUI_Shape;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * This class represents a collection of GUI_Shape.
 * Ex2: you should implement this class!
 * @author Ariel Ya'acobi
 * ID - 318727187
 */
public class ShapeCollection implements GUI_Shape_Collection {
	private ArrayList<GUI_Shape> _shapes;

	/**
	 * Constructor for ShapeCollection class.
	 * Initializes the internal ArrayList to store GUI_Shape objects.
	 */
	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shape>();
	}

	/**
	 * Retrieves the GUI_Shape object at the specified index.
	 * @param i The index of the GUI_Shape to retrieve.
	 * @return The GUI_Shape object at the specified index.
	 */
	@Override
	public GUI_Shape get(int i) {
		return _shapes.get(i);
	}

	/**
	 * Returns the number of GUI_Shape objects in the collection.
	 * @return The number of GUI_Shape objects in the collection.
	 */
	@Override
	public int size() {
		return _shapes.size();
	}

	/**
	 * Removes the GUI_Shape object at the specified index from the collection.
	 * @param i The index of the GUI_Shape object to remove.
	 * @return The removed GUI_Shape object.
	 */
	@Override
	public GUI_Shape removeElementAt(int i) {
		return this._shapes.remove(i);
	}

	/**
	 * Adds a GUI_Shape object at the specified index in the collection.
	 * @param s The GUI_Shape object to add.
	 * @param i The index at which to add the GUI_Shape object.
	 */
	@Override
	public void addAt(GUI_Shape s, int i) {
		if (s != null && s.getShape() != null) {
			this._shapes.add(i, s);
		}
	}

	/**
	 * Adds a GUI_Shape object to the collection.
	 * @param s The GUI_Shape object to add.
	 */
	@Override
	public void add(GUI_Shape s) {
		if (s != null && s.getShape() != null) {
			this._shapes.add(s);
		}
	}

	/**
	 * Creates a deep copy of the ShapeCollection object.
	 * @return A new ShapeCollection object containing copies of the GUI_Shape objects.
	 */
	@Override
	public ShapeCollection copy() {
		ShapeCollection copyCollection = new ShapeCollection();
		for (int i = 0; i < this._shapes.size(); i++) {
			copyCollection.add(this._shapes.get(i).copy());
		}
		return copyCollection;
	}

	/**
	 * Sorts the GUI_Shape objects in the collection using the specified comparator.
	 * @param comp The comparator to use for sorting.
	 */
	@Override
	public void sort(Comparator<GUI_Shape> comp) {
		this._shapes.sort(comp);
	}

	/**
	 * Removes all GUI_Shape objects from the collection.
	 */
	@Override
	public void removeAll() {
		this._shapes.clear();
	}

	/**
	 * Saves the collection of GUI_Shape objects to a file.
	 * Each GUI_Shape is converted to a string representation before saving.
	 * @param file_name The name of the file to save to.
	 */
	@Override
	public void save(String file_name) {
		// Insert a newline before each "GUIShape" instance in the content, except at the start of the text.
		String path = file_name + ".txt";
		ArrayList<String> lines = new ArrayList<>();
		for (GUI_Shape shape : this._shapes) {
			String new_shape = shape.toString().replace("]", "").replace("[", "");
			lines.add(new_shape);
		}
		try {
			Files.write(Paths.get(path), lines);
			System.out.println("File saved successfully.");
		} catch (Exception e) {
			// Print an error message if writing to the file fails.
			System.err.println("Error writing to file: " + e.getMessage());
		}
	}

	/**
	 * Loads GUI_Shape objects from a file and adds them to the collection.
	 * @param file The name of the file to load from.
	 */
	@Override
	public void load(String file) {
		try {
			String path = file; // Moved inside the try block
			byte[] fileBytes = Files.readAllBytes(Paths.get(path));
			String content = new String(fileBytes, StandardCharsets.UTF_8);
			String[] string_shapes = content.split("\n");

			for (int i = 0; i < string_shapes.length; i++) {
				String shape = string_shapes[i];
				GUI_Shape gs = new GUIShape(shape);
				this._shapes.add(gs);
			}
		} catch (IOException e) {
			System.err.println("Cannot read the file not in the correct format: " + e.getMessage());
		}
	}

	/**
	 * Returns a string representation of the ShapeCollection object.
	 * Each GUI_Shape object is represented as a string in the format "[GUI_Shape.toString()]".
	 * @return A string representation of the ShapeCollection object.
	 */
	@Override
	public String toString() {
		String ans = "";
		for (int i = 0; i < size(); i++) {
			ans += this.get(i) + "\n";
		}
		return ans;
	}
}
