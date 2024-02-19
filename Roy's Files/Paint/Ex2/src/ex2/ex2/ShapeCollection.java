package ex2.ex2;

import ex2.geo.*;
import ex2.gui.GUIShape;
import ex2.gui.GUI_Shape;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	@Override
	public GUI_Shape get(int i) {
		// Return the GUI_Shape at index i from the collection.
		return _shapes.get(i);
	}

	@Override
	public int size() {
		// Return the number of GUI_Shapes in the collection.
		return _shapes.size();
	}

	@Override
	public GUI_Shape removeElementAt(int i) {
		// Remove and return the GUI_Shape at index i.
		return this._shapes.remove(i);
	}

	@Override
	public void addAt(GUI_Shape s, int i) {
		// Add a GUI_Shape 's' at the specified index 'i', if 's' and its inner shape are not null.
		if(s != null && s.getShape() != null) {
			this._shapes.add(i, s);
		}
	}

	@Override
	public void add(GUI_Shape s) {
		// Add a GUI_Shape 's' to the collection if 's' and its inner shape are not null.
		if(s != null && s.getShape() != null) {
			_shapes.add(s);
		}
	}

	@Override
	public ShapeCollection copy() {
		// Create and return a copy of this ShapeCollection.
		ShapeCollection copyCollection = new ShapeCollection();
		for (GUI_Shape shape : this._shapes) {
			copyCollection.add(shape.copy()); // Add a copy of each shape to the new collection.
		}
		return copyCollection;
	}

	@Override
	public void sort(Comparator<GUI_Shape> comp) {
		// Sort the collection of GUI_Shapes using the provided Comparator.
		this._shapes.sort(comp);
	}

	@Override
	public void removeAll() {
		// Clear all GUI_Shapes from the collection.
		this._shapes.clear();
	}

	@Override
	public void save(String file_name) {
		// Convert the shapes collection to a string and remove the square brackets.
		String content = this._shapes.toString().replace("[", "").replace("]", "");
		// Insert a newline before each "GUIShape" instance in the content, except at the start of the text.
		String modifiedContent = content.replaceAll("(?<!^)(GUIShape)", "\n$1");
		// Append ".txt" to the provided filename to create the full path.
		String path = file_name + ".txt";
		try {
			// Write the modified content to a file at the specified path.
			Files.writeString(Paths.get(path), modifiedContent.trim());
			System.out.println("File saved successfully.");
		} catch (Exception e) {
			// Print an error message if writing to the file fails.
			System.err.println("Error writing to file: " + e.getMessage());
		}
	}

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


	@Override
	public String toString() {
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += this.get(i) + "\n";
		}
		return ans;
	}
	

}
