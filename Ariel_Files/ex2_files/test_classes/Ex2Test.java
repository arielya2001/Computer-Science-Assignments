package ex2.test_classes;

import ex2.ex2.GUI_Shape_Collection;
import ex2.ex2.ShapeCollection;
import ex2.geo.Circle_2D;
import ex2.geo.Point_2D;
import ex2.geo.Rect_2D;
import ex2.geo.Triangle_2D;
import ex2.gui.Ex2;
import ex2.gui.GUIShape;
import org.junit.jupiter.api.Test;

import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

class Ex2Test {

    /**
     * Tests the getShapeCollection method of Ex2.
     * Verifies if the shape collection is correctly updated with new shapes.
     */
    @Test
    void getShapeCollection() {
        // Obtain an instance of Ex2
        Ex2 ex2Instance = Ex2.getInstance();

        // Initialize a new ShapeCollection for Ex2
        ex2Instance.init(new ShapeCollection());

        // Define the parameters for the new Circle_2D and Rect_2D instances
        Circle_2D circle = new Circle_2D(new Point_2D(200, 200), 25);

        // Changed parameters for Rect_2D: diagonal points (300, 300) and (400, 400)
        Rect_2D rect = new Rect_2D(new Point_2D(300, 300), new Point_2D(400, 400));

        // Create GUIShape instances using the new Circle_2D and Rect_2D instances
        GUIShape shape1 = new GUIShape(circle, true, Color.BLUE, 5);

        // Changed color for shape2 to Color.RED and tag to 6
        GUIShape shape2 = new GUIShape(rect, false, Color.RED, 6);

        // Add the new GUIShape instances to the collection
        ex2Instance.getShape_Collection().add(shape1);
        ex2Instance.getShape_Collection().add(shape2);

        // Get the shape collection from Ex2
        GUI_Shape_Collection collection = ex2Instance.getShape_Collection();

        // Check if the retrieved collection contains the added shapes with updated details
        assertEquals(2, collection.size(), "The number of shapes in the collection should be 2.");
        assertTrue(collection.get(0) instanceof GUIShape && collection.get(0).getShape() instanceof Circle_2D,
                "The first shape in the collection should be a circle with updated properties.");
        assertTrue(collection.get(1) instanceof GUIShape && collection.get(1).getShape() instanceof Rect_2D,
                "The second shape in the collection should be a rectangle with updated properties.");
    }

    /**
     * Tests the init method of Ex2.
     * Verifies if the shape collection is initialized correctly.
     */
    @Test
    void init() {
        // Obtain an instance of Ex2
        Ex2 ex2 = Ex2.getInstance();

        // Initializing ex2 with null input
        ex2.init(null);

        // Verify that the shape collection is initialized and empty for null input
        assertNotNull(ex2.getShape_Collection(), "Shape collection should be initialized.");
        assertEquals(0, ex2.getShape_Collection().size(), "Shape collection should be empty for null input.");

        // Initialize ex2 with a new ShapeCollection
        ex2.init(new ShapeCollection());

        // Verify that the shape collection is still empty after initialization with a new ShapeCollection
        assertEquals(0, ex2.getShape_Collection().size(), "Shape collection should be empty for new ShapeCollection.");
    }

    /**
     * Tests the getInfo method of Ex2.
     * Verifies if the information string generated matches the expected format.
     */
    @Test
    void getInfo() {
        // Obtain an instance of Ex2
        Ex2 ex2Instance = Ex2.getInstance();

        // Remove all shapes from the shape collection
        ex2Instance.getShape_Collection().removeAll();

        // Create a circle and a triangle and add them to the collection
        Circle_2D circle = new Circle_2D(new Point_2D(150, 150), 20); // Circle with center (150, 150) and radius 20
        Triangle_2D triangle = new Triangle_2D(new Point_2D(400, 400), new Point_2D(250, 300), new Point_2D(300, 250)); // Triangle with vertices (400,400), (250,300), and (300,250)

        // Create GUIShape instances for the circle and the triangle
        GUIShape shape1 = new GUIShape(circle, true, Color.YELLOW, 0); // Circle with color YELLOW and tag 0
        GUIShape shape2 = new GUIShape(triangle, false, Color.RED, 1); // Triangle with color RED and tag 1

        // Add the shapes to the collection
        ex2Instance.getShape_Collection().add(shape1);
        ex2Instance.getShape_Collection().add(shape2);

        // Get the information string
        String info = ex2Instance.getInfo();

        // Expected string representation of the shapes in the collection
        String expectedInfo = "GUIShape,-256,true,0,Circle_2D,150.0,150.0, 20.0\n" +
                "GUIShape,-65536,false,1,Triangle_2D,400.0,400.0, 250.0,300.0, 300.0,250.0\n";

        // Verify that the generated information string matches the expected format
        assertEquals(expectedInfo, info);
    }

}
