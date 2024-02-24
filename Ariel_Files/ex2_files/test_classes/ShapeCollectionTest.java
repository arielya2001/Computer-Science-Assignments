package ex2.test_classes;

import ex2.ex2.Ex2_Const;
import ex2.ex2.ShapeCollection;
import ex2.geo.Circle_2D;
import ex2.geo.Point_2D;
import ex2.geo.Rect_2D;
import ex2.geo.ShapeComp;
import ex2.gui.GUIShape;
import ex2.gui.GUI_Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static ex2.geo.ShapeComp.CompByArea;
import static org.junit.jupiter.api.Assertions.*;

class ShapeCollectionTest {
    private ShapeCollection shapeCollection;

    @BeforeEach
    void setUp() {
        shapeCollection = new ShapeCollection();
    }


    @Test
    void testAdd() {
        // Adding shapes
        shapeCollection.add(new GUIShape(new Circle_2D(new Point_2D(1, 1), 5), true, Color.RED, 1));
        shapeCollection.add(new GUIShape(new Rect_2D(new Point_2D(2, 2), new Point_2D(5, 5)), false, Color.BLUE, 2));

        // Asserting size
        assertEquals(2, shapeCollection.size(), "Size should be 2 after adding two shapes.");
    }

    @Test
    void testSize() {
        // Adding shapes
        shapeCollection.add(new GUIShape(new Circle_2D(new Point_2D(1, 1), 5), true, Color.RED, 1));
        shapeCollection.add(new GUIShape(new Rect_2D(new Point_2D(2, 2), new Point_2D(5, 5)), false, Color.BLUE, 2));

        // Asserting size
        assertEquals(2, shapeCollection.size(), "Size should be 2 after adding two shapes.");
    }


    @Test
    void testRemoveElementAt() {
        // Adding shapes
        shapeCollection.add(new GUIShape(new Circle_2D(new Point_2D(1, 1), 5), true, Color.RED, 1));
        shapeCollection.add(new GUIShape(new Rect_2D(new Point_2D(2, 2), new Point_2D(5, 5)), false, Color.BLUE, 2));

        // Removing shape at index 0
        shapeCollection.removeElementAt(0);

        // Asserting size
        assertEquals(1, shapeCollection.size(), "Size should be 1 after removing one shape.");
    }

    @Test
    void testCopy() {
        // Adding shapes
        shapeCollection.add(new GUIShape(new Circle_2D(new Point_2D(1, 1), 5), true, Color.RED, 1));
        shapeCollection.add(new GUIShape(new Rect_2D(new Point_2D(2, 2), new Point_2D(5, 5)), false, Color.BLUE, 2));

        // Copying the collection
        ShapeCollection copiedCollection = shapeCollection.copy();

        // Asserting size of copied collection
        assertEquals(shapeCollection.size(), copiedCollection.size(), "Size of copied collection should be the same as original.");
    }
    @Test
    void testGet() {
        GUIShape shape = new GUIShape(new Circle_2D(new Point_2D(1, 1), 5), true, Color.RED, 1);
        shapeCollection.add(shape);

        assertEquals(shape, shapeCollection.get(0), "Should retrieve the correct shape.");
    }

    @Test
    void testAddAt() {
        GUIShape shape = new GUIShape(new Circle_2D(new Point_2D(1, 1), 5), true, Color.RED, 1);
        shapeCollection.addAt(shape, 0);

        assertEquals(shape, shapeCollection.get(0), "Should add the shape at the specified index.");
    }

    @Test
    void testSort() {
        // Create shapes
        Point_2D p1 = new Point_2D(1, 1);
        Point_2D p2 = new Point_2D(2, 2);
        double width = 5;
        double height = 10;
        Color color = Color.RED;
        int tag = 1;

        Rect_2D rect1 = new Rect_2D(p1, new Point_2D(p1.x() + width, p1.y() + height));
        Rect_2D rect2 = new Rect_2D(p2, new Point_2D(p2.x() + width * 2, p2.y() + height * 2));

        GUI_Shape gs1 = new GUIShape(rect1, false, color, tag);
        GUI_Shape gs2 = new GUIShape(rect2, false, color, tag);

        // Add shapes to the collection
        shapeCollection.add(gs1); // [gs1]
        shapeCollection.add(gs2); // [gs1, gs2]

        // Create expected sorted collection
        ShapeCollection sortedShapes = new ShapeCollection();
        sortedShapes.add(gs2);
        sortedShapes.add(gs1);

        // Sort the collection
        shapeCollection.sort(CompByArea);

        // Assert shapes are sorted correctly
        boolean isCorrectOrder = shapeCollection.get(0).getShape().area() <= shapeCollection.get(1).getShape().area();
        assertTrue(isCorrectOrder, "Shapes should be sorted in ascending order by area.");
    }

    @Test
    void testRemoveAll() {
        shapeCollection.add(new GUIShape(new Circle_2D(new Point_2D(1, 1), 5), true, Color.RED, 1));
        shapeCollection.add(new GUIShape(new Circle_2D(new Point_2D(2, 2), 10), false, Color.BLUE, 2));

        shapeCollection.removeAll();

        assertEquals(0, shapeCollection.size(), "Should remove all shapes from the collection.");
    }

    @Test
    void testSave() {
        // Add shapes to the collection
        shapeCollection.add(new GUIShape(new Circle_2D(new Point_2D(1, 1), 5), true, Color.RED, 1));
        shapeCollection.add(new GUIShape(new Circle_2D(new Point_2D(2, 2), 10), false, Color.BLUE, 2));

        // Save the collection
        shapeCollection.save("testShapes");

        // Assert that the file was saved successfully (you can add additional assertions if needed)
        assertTrue(Files.exists(Paths.get("testShapes.txt")), "File should be saved successfully.");
    }

    @Test
    void testLoad() {
        // Load the previously saved collection
        ShapeCollection loadedCollection = new ShapeCollection();
        loadedCollection.load("testShapes.txt");

        // Assert that the loaded collection has the same number of shapes as the original collection
        assertEquals(shapeCollection.size(), loadedCollection.size(), "Should load the same number of shapes.");
        // Add additional assertions if needed to verify the loaded collection
    }


    @Test
    void testToString() {
        shapeCollection.add(new GUIShape(new Circle_2D(new Point_2D(1, 1), 5), true, Color.RED, 1));
        shapeCollection.add(new GUIShape(new Circle_2D(new Point_2D(2, 2), 10), false, Color.BLUE, 2));

        String expected = "GUIShape,-65536,true,1,Circle_2D,1.0,1.0, 5.0\n" +
                "GUIShape,-16776961,false,2,Circle_2D,2.0,2.0, 10.0\n";

        assertEquals(expected, shapeCollection.toString(), "Should return the correct string representation.");
    }

}