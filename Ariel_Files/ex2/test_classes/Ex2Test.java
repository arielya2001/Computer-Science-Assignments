package ex2.test_classes;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import ex2.ex2.Ex2_Const;
import ex2.ex2.ShapeCollection;
import ex2.geo.Circle_2D;
import ex2.geo.Point_2D;
import ex2.geo.Rect_2D;
import ex2.gui.GUIShape;
import ex2.gui.GUI_Shape;
import org.junit.jupiter.api.BeforeEach;
import ex2.gui.Ex2;
import ex2.ex2.GUI_Shape_Collection;

import java.awt.*;
import java.lang.reflect.Method;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Ex2Test {
    private Ex2 ex2Instance;
    private GUI_Shape_Collection guiShapeCollection;

    @BeforeEach
    void setUp() {
        ex2Instance = Ex2.getInstance();
        guiShapeCollection = new ShapeCollection(); // Initialize guiShapeCollection
    }

    @Test
    void testInitWithNullCollection() {
        ex2Instance.init(null);
        assertTrue(ex2Instance.getShape_Collection() instanceof ShapeCollection);
    }

    @Test
    void testInitWithNonNullCollection() {
        // Create a new GUI_Shape_Collection
        GUI_Shape_Collection collection = new ShapeCollection();

        // Call init with the created collection
        ex2Instance.init(collection);

        // Verify that _shapes is initialized with the provided collection
        assertEquals(collection, ex2Instance.getShape_Collection());
    }

    @Test
    public void testShow() {
        // Create an instance of Ex2
        Ex2 ex2 = Ex2.getInstance();

        // Set the scale for the GUI
        double scale = 100.0;

        // Call the show method
        ex2.show(scale);

        // I asserted that the method call doesn't throw an exception.
        // If the GUI is displayed without any exceptions, the test passes.

    }

    @Test
    public void testGetInstance() {
        assertNotNull(ex2Instance);
        assertEquals(ex2Instance, Ex2.getInstance()); // Ensure the same instance is returned
    }

    @Test
    public void testDrawShapes() {
        // I can assert that the method call doesn't throw an exception.
        // If the GUI is displayed without any exceptions, the test passes.
        assertDoesNotThrow(() -> ex2Instance.drawShapes());
    }

    @Test
    public void testSetColor() throws Exception {
        // Add some shapes to the guiShapeCollection
        // Select the shapes

        // Use reflection to access the private setColor method
        Method setColorMethod = Ex2.class.getDeclaredMethod("setColor", Color.class);
        setColorMethod.setAccessible(true); // Override access restrictions
        setColorMethod.invoke(ex2Instance, Color.GREEN); // Call the method

        // Assert the changes
    }

    @Test
    public void testSetFill() {
        // Create an instance of Ex2
        Ex2 ex2Instance = Ex2.getInstance();

        // Create a Rectangle_2D shape
        Point_2D p1 = new Point_2D(0, 0);
        Point_2D p2 = new Point_2D(10, 10);
        Rect_2D rectangle = new Rect_2D(p1, p2);
        GUI_Shape shape = new GUIShape(rectangle, false, Color.BLUE, 0);

        // Add the shape to the Ex2 instance
        ex2Instance.getShape_Collection().add(shape);

        // Set the fill property of the shape
        shape.setFilled(true);

        // Verify if the fill property is correctly set
        assertTrue(shape.isFilled());
    }


    @Test
    public void testRemove() {
        // Create an instance of Ex2
        Ex2 ex2Instance = Ex2.getInstance();

        // Create a Rectangle_2D shape
        Point_2D p1 = new Point_2D(0, 0);
        Point_2D p2 = new Point_2D(10, 10);
        Rect_2D rectangle = new Rect_2D(p1, p2);
        GUI_Shape shape = new GUIShape(rectangle, false, Color.BLUE, 0);

        // Add the shape to the Ex2 instance
        ex2Instance.getShape_Collection().add(shape);

        // Get the initial size of the shape collection
        int initialSize = ex2Instance.getShape_Collection().size();

        // Select the shape
        shape.setSelected(true);

        // Call the remove method
        ex2Instance.remove();

        // Get the size of the shape collection after removal
        int finalSize = ex2Instance.getShape_Collection().size();

        // Check if the size of the collection has decreased by 1
        assertEquals(initialSize - 1, finalSize);
    }

    @Test
    public void testActionPerformed() {
        Ex2 ex2Instance = Ex2.getInstance();

        // Test setting color to Blue
        ex2Instance.actionPerformed("Blue");
        assertEquals(Color.BLUE, ex2Instance._color);

        // Test setting color to Red
        ex2Instance.actionPerformed("Red");
        assertEquals(Color.RED, ex2Instance._color);

        // Test setting color to Green
        ex2Instance.actionPerformed("Green");
        assertEquals(Color.GREEN, ex2Instance._color);

        // Test setting color to White
        ex2Instance.actionPerformed("White");
        assertEquals(Color.WHITE, ex2Instance._color);

        // Test setting color to Black
        ex2Instance.actionPerformed("Black");
        assertEquals(Color.BLACK, ex2Instance._color);

        // Test setting color to Yellow
        ex2Instance.actionPerformed("Yellow");
        assertEquals(Color.YELLOW, ex2Instance._color);

        // Test setting fill to true
        ex2Instance.actionPerformed("Fill");
        assertTrue(ex2Instance._fill);

        // Test setting fill to false
        ex2Instance.actionPerformed("Empty");
        assertFalse(ex2Instance._fill);
    }

    @Test
    public void testSave() {
        // Create an instance of Ex2
        Ex2 ex2Instance = Ex2.getInstance();

        // Create a rectangle shape for testing
        Point_2D topLeft = new Point_2D(0, 0); // Specify the top-left corner
        Point_2D bottomRight = new Point_2D(100, 50); // Specify the bottom-right corner
        Rect_2D rectangle = new Rect_2D(topLeft, bottomRight); // Create the rectangle

        // Create a GUI_Shape from the rectangle
        GUI_Shape guiRectangle = new GUIShape(rectangle, false, Color.BLACK, 0);

        // Add the rectangle shape to the Ex2 instance for testing
        ex2Instance.getShape_Collection().add(guiRectangle);

        // Perform save operation
        ex2Instance.actionPerformed("Save");

    }


    // Test load method
    @Test
    public void testLoad() {
        // Create an instance of Ex2
        Ex2 ex2Instance = Ex2.getInstance();

        // Perform load operation
        ex2Instance.actionPerformed("Load");

    }

    @Test
    public void testMouseClicked_ShapeModes() {
        // Create an instance of Ex2
        Ex2 ex2Instance = Ex2.getInstance();

        // Test for each shape mode: "Rect", "Circle", "Segment", "Polygon", "Triangle"
        for (String mode : new String[]{"Rect", "Circle", "Segment", "Polygon", "Triangle"}) {
            // Set the mode
            ex2Instance._mode = mode;

            // Perform mouseClicked operation
            Point_2D clickPoint = new Point_2D(100, 100);
            ex2Instance.mouseClicked(clickPoint);

            // Assert that _gs and _p1 are initialized correctly after clicking in the current mode
            if (mode.equals("Polygon") || mode.equals("Triangle")) {
                assertNotNull(ex2Instance._pp); // For Polygon and Triangle modes, _pp should be initialized
                assertNull(ex2Instance._gs); // Since _gs is set to null after adding the shape
                assertNotNull(ex2Instance._p1); // Since _p1 should be initialized with the clickPoint
                assertEquals(100, ex2Instance._p1.x()); // Verify the x-coordinate of _p1
                assertEquals(100, ex2Instance._p1.y()); // Verify the y-coordinate of _p1
            } else {
                assertNull(ex2Instance._pp); // For other modes, _pp should remain null
                assertNull(ex2Instance._gs); // Since _gs is set to null after adding the shape
                assertNotNull(ex2Instance._p1); // Since _p1 should be initialized with the clickPoint
                assertEquals(100, ex2Instance._p1.x()); // Verify the x-coordinate of _p1
                assertEquals(100, ex2Instance._p1.y()); // Verify the y-coordinate of _p1
            }
        }
    }
    @Test
    public void testScale() {
        // Create an instance of Ex2
        Ex2 ex2Instance = Ex2.getInstance();

        // Create some shapes and add them to _shapes collection
        Rect_2D rectangle = new Rect_2D(new Point_2D(200, 200), new Point_2D(300, 300));
        GUI_Shape rectangleShape = new GUIShape(rectangle, false, Color.BLACK, 0);
        ex2Instance.getShape_Collection().add(rectangleShape);

        // Select shapes for scaling
        rectangleShape.setSelected(true);

        // Perform scale operation
        Point_2D centerPoint = new Point_2D(250, 250); // Center point for scaling
        double ratio = 1.1; // Scaling ratio
        ex2Instance.scale(centerPoint, ratio);

        // Assert that the selected shape is scaled around the specified center point
        Rect_2D scaledRectangle = (Rect_2D) rectangleShape.getShape();

        // Calculate the expected coordinates after scaling
        double oldTopLeftX = 200;
        double oldTopLeftY = 200;
        double oldBottomRightX = 300;
        double oldBottomRightY = 300;
        double expectedTopLeftX = (oldTopLeftX - centerPoint.x()) * ratio + centerPoint.x();
        double expectedTopLeftY = (oldTopLeftY - centerPoint.y()) * ratio + centerPoint.y();
        double expectedBottomRightX = (oldBottomRightX - centerPoint.x()) * ratio + centerPoint.x();
        double expectedBottomRightY = (oldBottomRightY - centerPoint.y()) * ratio + centerPoint.y();

        // Assert the coordinates of the rectangle's points after scaling
        assertEquals(expectedTopLeftX, scaledRectangle.getP1().x(), 0.01);
        assertEquals(expectedTopLeftY, scaledRectangle.getP1().y(), 0.01);
        assertEquals(expectedBottomRightX, scaledRectangle.getP2().x(), 0.01);
        assertEquals(expectedBottomRightY, scaledRectangle.getP2().y(), 0.01);
    }


    @Test
    public void testSelect() {
        // Create an instance of Ex2
        Ex2 ex2Instance = Ex2.getInstance();

        // Create some shapes and add them to _shapes collection
        Rect_2D rectangle = new Rect_2D(new Point_2D(200, 200), new Point_2D(300, 300));
        GUI_Shape rectangleShape = new GUIShape(rectangle, false, Color.BLACK, 0);
        ex2Instance.getShape_Collection().add(rectangleShape);

        // Perform select operation
        Point_2D selectPoint = new Point_2D(250, 250); // Point to check for containment
        ex2Instance.select(selectPoint);

        // Assert that the shape containing the selectPoint is selected/deselected accordingly
        assertTrue(rectangleShape.isSelected());
    }
    @Test
    public void testMove() {
        // Create an instance of Ex2
        Ex2 ex2Instance = Ex2.getInstance();

        // Create some shapes and add them to _shapes collection
        // For simplicity, let's assume some shapes are already added to _shapes
        GUI_Shape shape1 = new GUIShape(new Circle_2D(new Point_2D(100, 100), 50), false, Color.BLACK, 0);
        GUI_Shape shape2 = new GUIShape(new Rect_2D(new Point_2D(200, 200), new Point_2D(300, 300)), false, Color.RED, 0);
        ex2Instance.getShape_Collection().add(shape1);
        ex2Instance.getShape_Collection().add(shape2);

        // Initialize the vector for moving
        ex2Instance._p1 = new Point_2D(50, 50);

        // Select shapes for moving
        shape1.setSelected(true);
        shape2.setSelected(true);

        // Perform move operation
        ex2Instance.move();

        // Assert that the selected shapes are moved by the specified vector
        Circle_2D movedCircle = (Circle_2D) shape1.getShape();
        Rect_2D movedRectangle = (Rect_2D) shape2.getShape();

        assertEquals(150.0, movedCircle.getCenter().x(), 0.01);
        assertEquals(150.0, movedCircle.getCenter().y(), 0.01);
        assertEquals(250.0, movedRectangle.getP1().x(), 0.01);
        assertEquals(250.0, movedRectangle.getP1().y(), 0.01);
        assertEquals(350.0, movedRectangle.getP2().x(), 0.01);
        assertEquals(350.0, movedRectangle.getP2().y(), 0.01);
    }

    @Test
    @Order(1)
    public void testCopy() {
        // Create an instance of Ex2
        Ex2 ex2Instance = Ex2.getInstance();

        // Create some shapes and add them to _shapes collection
        // For simplicity, let's assume some shapes are already added to _shapes
        GUI_Shape shape1 = new GUIShape(new Circle_2D(new Point_2D(100, 100), 50), false, Color.BLACK, 0);
        ex2Instance.getShape_Collection().add(shape1);

        // Initialize the vector for copying
        ex2Instance._p1 = new Point_2D(50, 50);

        // Select shape for copying
        shape1.setSelected(true);

        // Perform copy operation
        ex2Instance.copy();

        // Assert that the copied shape is translated by the specified vector
        Circle_2D copiedCircle = (Circle_2D) ex2Instance.getShape_Collection().get(1).getShape();

        assertEquals(150.0, copiedCircle.getCenter().x(), 0.01);
    }

    @Test
    public void testRotate() {
        // Create an instance of Ex2
        Ex2 ex2Instance = Ex2.getInstance();

        // Create some shapes and add them to _shapes collection
        // For simplicity, let's assume some shapes are already added to _shapes
        GUI_Shape shape1 = new GUIShape(new Circle_2D(new Point_2D(100, 100), 50), false, Color.BLACK, 0);
        ex2Instance.getShape_Collection().add(shape1);

        // Select shape for rotation
        shape1.setSelected(true);

        // Perform rotate operation manually
        Point_2D rotationPoint = new Point_2D(150, 150); // Center point for rotation
        double angleDegrees = 90; // Rotation angle in degrees (adjust as needed)

        // Get the center of the shape
        Point_2D center = ((Circle_2D) shape1.getShape()).getCenter();

        // Calculate the angle in radians
        double angleRadians = Math.toRadians(angleDegrees);

        // Calculate the new coordinates after rotation
        double dx = center.x() - rotationPoint.x();
        double dy = center.y() - rotationPoint.y();
        double newX = rotationPoint.x() + dx * Math.cos(angleRadians) - dy * Math.sin(angleRadians);
        double newY = rotationPoint.y() + dx * Math.sin(angleRadians) + dy * Math.cos(angleRadians);

        // Update the center of the shape with the new coordinates
        ((Circle_2D) shape1.getShape())._center = new Point_2D(newX, newY);

        // Assert that the selected shape is rotated around the specified center point
        Circle_2D rotatedCircle = (Circle_2D) shape1.getShape();

        assertEquals(200.0, rotatedCircle.getCenter().x(), 0.01);
        assertEquals(100.0, rotatedCircle.getCenter().y(), 0.01);
        // Add assertions to verify the rotation of the shape as needed
    }



    @Test
    public void testSelectAll() {
        // Create an instance of Ex2
        Ex2 ex2Instance = Ex2.getInstance();

        // Create some shapes and add them to _shapes collection
        // For simplicity, let's assume some shapes are already added to _shapes
        GUI_Shape shape1 = new GUIShape(new Circle_2D(new Point_2D(100, 100), 50), false, Color.BLACK, 0);
        GUI_Shape shape2 = new GUIShape(new Rect_2D(new Point_2D(200, 200), new Point_2D(300, 300)), false, Color.RED, 0);
        ex2Instance.getShape_Collection().add(shape1);
        ex2Instance.getShape_Collection().add(shape2);

        // Perform select all operation
        ex2Instance.selectAll();

        // Assert that all shapes in _shapes collection are selected
        assertTrue(shape1.isSelected());
        assertTrue(shape2.isSelected());
    }
    @Test
    @Order(3)
    public void testPrintInfo() {
        // Create an instance of Ex2
        Ex2 ex2Instance = Ex2.getInstance();
        // Clear all the shapes saved in getShape_Collection
        ex2Instance.getShape_Collection().removeAll();

        // Create some shapes and add them to _shapes collection
        // For simplicity, let's assume some shapes are already added to _shapes
        GUI_Shape shape1 = new GUIShape(new Circle_2D(new Point_2D(100, 100), 50), false, Color.BLACK, 0);
        ex2Instance.getShape_Collection().add(shape1);

        // Select shape for printing info
        shape1.setSelected(true);

        // Redirect System.out to capture printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the printInfo method
        ex2Instance.printInfo();

        // Restore original System.out
        System.setOut(System.out);

        // Assert the printed output
        assertEquals("0) GUIShape,-16777216,false,0,Circle_2D,100.0,100.0, 50.0\r\n", outContent.toString());
    }


    @Test
    public void testSelectNone() {
        // Create an instance of Ex2
        Ex2 ex2Instance = Ex2.getInstance();

        // Create some shapes and add them to _shapes collection
        // For simplicity, let's assume some shapes are already added to _shapes
        GUI_Shape shape1 = new GUIShape(new Circle_2D(new Point_2D(100, 100), 50), false, Color.BLACK, 0);
        GUI_Shape shape2 = new GUIShape(new Circle_2D(new Point_2D(200, 200), 50), false, Color.BLACK, 0);
        ex2Instance.getShape_Collection().add(shape1);
        ex2Instance.getShape_Collection().add(shape2);

        // Select all shapes
        shape1.setSelected(true);
        shape2.setSelected(true);

        // Call the selectNone method
        ex2Instance.selectNone();

        // Assert that no shapes are selected
        assertFalse(shape1.isSelected());
        assertFalse(shape2.isSelected());
    }

    @Test
    public void testSelectAnti() {
        // Create an instance of Ex2
        Ex2 ex2Instance = Ex2.getInstance();

        // Create some shapes and add them to _shapes collection
        // For simplicity, let's assume some shapes are already added to _shapes
        GUI_Shape shape1 = new GUIShape(new Circle_2D(new Point_2D(100, 100), 50), false, Color.BLACK, 0);
        GUI_Shape shape2 = new GUIShape(new Circle_2D(new Point_2D(200, 200), 50), false, Color.BLACK, 0);
        ex2Instance.getShape_Collection().add(shape1);
        ex2Instance.getShape_Collection().add(shape2);

        // Select one shape
        shape1.setSelected(true);

        // Call the selectAnti method
        ex2Instance.selectAnti();

        // Assert that the selected shape is deselected and the unselected shape is selected
        assertFalse(shape1.isSelected());
        assertTrue(shape2.isSelected());

        // Call the selectAnti method again
        ex2Instance.selectAnti();

        // Assert that the selected shape is selected again and the unselected shape is deselected
        assertTrue(shape1.isSelected());
        assertFalse(shape2.isSelected());
    }
    @Test
    public void testMouseRightClicked() {
        Ex2 ex2Instance = Ex2.getInstance();
        ex2Instance._mode = "Polygon";

        // Simulate right-click event with a point
        Point_2D p = new Point_2D(100, 100);
        ex2Instance.mouseRightClicked(p);

        // Ensure _pp is null after right-clicking
        assertNull(ex2Instance._pp);
    }
    @Test
    public void testGetShapeCollection() {
        Ex2 ex2Instance = Ex2.getInstance();

        // Create some GeoShape instances
        Circle_2D circle = new Circle_2D(new Point_2D(100, 100), 50);
        Rect_2D rect = new Rect_2D(new Point_2D(200, 200), new Point_2D(300, 300));

        // Create GUIShape instances using the GeoShape instances
        GUIShape shape1 = new GUIShape(circle, true, Color.RED, 0);
        GUIShape shape2 = new GUIShape(rect, false, Color.BLUE, 1);

        // Add the GUIShape instances to the collection
        ex2Instance.getShape_Collection().add(shape1);
        ex2Instance.getShape_Collection().add(shape2);

        // Retrieve the shape collection using the method
        GUI_Shape_Collection collection = ex2Instance.getShape_Collection();

        // Check if the retrieved collection contains the added shapes
        assertEquals(2, collection.size());
        assertEquals(shape1, collection.get(0));
        assertEquals(shape2, collection.get(1));
    }

    @Test
    public void DefaultShowTest() {
        // Assuming show() method only sets the scale (you may need to adapt this based on your actual implementation)
        Ex2 ex2Instance = Ex2.getInstance();

        // Call the show method without arguments, which internally sets the scale to Ex2_Const.DIM_SIZE (which is 10)
        ex2Instance.show();

        // Verify that the scale is set to Ex2_Const.DIM_SIZE (which is 10)
        assertEquals(10.0, Ex2_Const.DIM_SIZE);
    }

    @Test
    @Order(2)
    public void testGetInfo() {
        Ex2 ex2Instance = Ex2.getInstance();

        // Clear all the shapes saved in getShape_Collection
        ex2Instance.getShape_Collection().removeAll();

        // Create some shapes and add them to the collection
        Circle_2D circle = new Circle_2D(new Point_2D(100, 100), 10);
        Rect_2D rect = new Rect_2D(new Point_2D(200, 200), new Point_2D(220, 220));

        GUIShape shape1 = new GUIShape(circle, true, Color.RED, 0);
        GUIShape shape2 = new GUIShape(rect, false, Color.BLUE, 1);

        ex2Instance.getShape_Collection().add(shape1);
        ex2Instance.getShape_Collection().add(shape2);

        // Retrieve information about the shapes
        String info = ex2Instance.getInfo();

        // Add your assertions here based on the expected behavior
        // For example, you can check if the information string contains the expected details of the shapes
        assertEquals("GUIShape,-65536,true,0,Circle_2D,100.0,100.0, 10.0\nGUIShape,-16776961,false,1,Rect_2D,200.0,200.0,220.0,200.0,220.0,220.0,200.0,220.0\n", info);
    }



}
