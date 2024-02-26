package ex2.ex2;

import ex2.geo.*;
import ex2.gui.GUIShape;
import ex2.gui.GUI_Shape;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * This class represents a collection of GUI_Shape.
 * @author Roy Naor
 * ID - 323917104
 */
public class ShapeCollection implements GUI_Shape_Collection {
	private ArrayList<GUI_Shape> _shapes;
	
	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shape>();
	}

	/**
	 * Returns a GUI_Shape from the collection at a specified index.
	 * @return GUIShape object
	 */
	@Override
	public GUI_Shape get(int i) {
		// Return the GUI_Shape at index i from the collection.
		return _shapes.get(i);
	}

	/**
	 * Returns the number of GUI_Shapes in the collection.
	 * @return the size of the collection
	 */
	@Override
	public int size() {
		// Return the number of GUI_Shapes in the collection.
		return _shapes.size();
	}

	/**
	 * Removes and returns the GUI_Shape at a specified index.
	 * @return the GUI_Shape collection after changes
	 */
	@Override
	public GUI_Shape removeElementAt(int i) {
		// Remove and return the GUI_Shape at index i.
		return this._shapes.remove(i);
	}

	/**
	 * Inserts a GUI_Shape into the collection at the specified index.
	 * @param s the GUI_Shape to add
	 * @param i the index at which the GUI_Shape should be inserted
	 */
	@Override
	public void addAt(GUI_Shape s, int i) {
		// Add a GUI_Shape 's' at the specified index 'i', if 's' and its inner shape are not null.
		if(s != null && s.getShape() != null) {
			this._shapes.add(i, s);
		}
	}

	/**
	 * Adds a GUI_Shape to the end of the collection.
	 * @param s the GUI_Shape to be added to the collection
	 */
	@Override
	public void add(GUI_Shape s) {
		// Add a GUI_Shape 's' to the collection if 's' and its inner shape are not null.
		if(s != null && s.getShape() != null) {
			_shapes.add(s);
		}
	}

	/**
	 * Creates a deep copy of this Shape Collection.
	 * @return a new Shape Collection object
	 */
	@Override
	public ShapeCollection copy() {
		// Create and return a copy of this ShapeCollection.
		ShapeCollection copyCollection = new ShapeCollection();
		for (GUI_Shape shape : this._shapes) {
			copyCollection.add(shape.copy()); // Add a copy of each shape to the new collection.
		}
		return copyCollection;
	}

	/**
	 * Sorts the collection of GUI_Shapes using the provided Comparator.
	 * @param comp the Comparator used to sort the GUI_Shapes
	 */
	@Override
	public void sort(Comparator<GUI_Shape> comp) {
		// Sort the collection of GUI_Shapes using the provided Comparator.
		this._shapes.sort(comp);
	}

	/**
	 * Removes all GUI_Shapes from the collection.
	 */
	@Override
	public void removeAll() {
		// Clear all GUI_Shapes from the collection.
		this._shapes.clear();
	}

	/**
	 * Saves the collection of GUI_Shapes to a text file.
	 * @param file_name the name of the text file
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
	 * Loads GUI_Shapes from a specified file into the collection.
	 * @param file the file path from which the GUI_Shapes will be loaded
	 */
	@Override
	public void load(String file) {
		try {
			// Define the path to the file.
			String path = file;
			// Read the entire content of the file into a string.
			String content = new String(Files.readAllBytes(Paths.get(path)));
			// Split the content into individual shape descriptions based on newlines.
			String[] string_shapes = content.split("\n");
			// Convert each string shape back into a GUI_Shape object and add it to the collection.
			for (String shape : string_shapes) {
				GUI_Shape gs = new GUIShape(shape); // Assumes GUIShape has a constructor that accepts a String.
				this._shapes.add(gs);
			}

		} catch (IOException e) {
			// Print an error message if reading from the file fails.
			System.err.println("Cannot read the file not in the correct format: " + e.getMessage());
		}
	}

	/**
	 * Converts the collection of GUI_Shapes into a string.
	 * @return a string representing all the GUI_Shapes in the collection
	 */
	@Override
	public String toString() {
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += this.get(i) + "\n";
		}
		return ans;
	}
	

}
