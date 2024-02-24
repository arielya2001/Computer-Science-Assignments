package ex2.test_classes;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.awt.*;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.ValueSource;
import ex2.geo.*;
import org.junit.jupiter.api.Test;
import ex2.gui.GUIShape;

import static org.junit.jupiter.api.Assertions.*;

class GUIShapeTest {
    static Stream<GeoShape> shapeProvider() {
        Rect_2D rectangle = new Rect_2D(new Point_2D(1.0, 2.0), new Point_2D(4.0, 6.0));
        Circle_2D circle = new Circle_2D(new Point_2D(3.0, 4.0), 6.0);
        Triangle_2D triangle = new Triangle_2D(new Point_2D(0.0, 0.0), new Point_2D(3.0, 0.0), new Point_2D(0.0, 3.0));

        return Stream.of(circle, rectangle, triangle);
    }
    static Stream<Color> colorProvider() {
        return Stream.of(
                Color.BLACK,
                new Color(255, 0, 0),  // Change RGB values for Red
                new Color(0, 0, 255),  // Change RGB values for Blue
                new Color(255, 255, 0)  // Change RGB values for Yellow
        );
    }

    @ParameterizedTest
    @MethodSource("shapeProvider")
    void testGetShape(GeoShape inputShape) {
        // Arrange
        boolean isFilled = false; // Set to false
        Color color = Color.BLUE; // Use blue color
        int tag = 3; // Set tag to 3
        GUIShape guiShape = new GUIShape(inputShape, isFilled, color, tag);

        // Act
        GeoShape actualShape = guiShape.getShape();

        // Assert
        assertEquals(inputShape.getClass(), actualShape.getClass(),
                "The actual shape class should match the expected shape class.");
    }

    @ParameterizedTest
    @MethodSource("shapeProvider")
    void testSetShape(GeoShape newShape) {
        // Arrange
        boolean isFilled = true; // Set to true
        Color color = Color.GREEN; // Use green color
        int tag = 4; // Set tag to 4
        GUIShape guiShape = new GUIShape(null, false, Color.BLUE, 2); // Initialize GUIShape with no shape

        // Act
        guiShape.setShape(newShape); // Set the new shape
        GeoShape updatedShape = guiShape.getShape(); // Retrieve the updated shape

        // Assert
        assertEquals(newShape, updatedShape, "The shape set by setShape should match the shape retrieved by getShape.");
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true}) // Reverse order of booleans
    void testIsFilled(boolean expectedFilledStatus) {
        // Arrange
        GUIShape shape = new GUIShape(null, expectedFilledStatus, Color.BLACK, 0);

        // Act & Assert
        assertEquals(expectedFilledStatus, shape.isFilled(), "The filled status should match the expected value.");
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true}) // Reverse order of booleans
    void testSetFilled(boolean newFilledStatus) {
        // Arrange
        GUIShape shape = new GUIShape(null, !newFilledStatus, Color.BLACK, 0); // Set to the opposite of newFilledStatus initially

        // Act
        shape.setFilled(newFilledStatus); // Change to new status

        // Assert
        assertEquals(newFilledStatus, shape.isFilled(), "The shape should reflect the new filled status after setFilled is called.");
    }

    @ParameterizedTest
    @MethodSource("colorProvider")
    void testGetColor(Color expectedColor) {
        // Arrange
        GUIShape shape = new GUIShape(null, false, expectedColor, 0);

        // Act
        Color actualColor = shape.getColor();

        // Assert
        assertEquals(expectedColor, actualColor, "The color should match the expected color.");
    }

    @ParameterizedTest
    @MethodSource("colorProvider")
    void testSetColor(Color newColor) {
        // Arrange
        GUIShape shape = new GUIShape(null, false, Color.BLACK, 0);

        // Act
        shape.setColor(newColor);
        Color actualColor = shape.getColor();

        // Assert
        assertEquals(newColor, actualColor, "The color should be set to the new color.");
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, -3, 20})
    void testGetTag(int expectedTag) {
        // Arrange
        GUIShape shape = new GUIShape(null, false, Color.BLACK, expectedTag);

        // Act
        int actualTag = shape.getTag();

        // Assert
        assertEquals(expectedTag, actualTag, "The tag should match the expected tag value.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, -5, 0})
    void testSetTag(int newTag) {
        // Arrange
        GUIShape shape = new GUIShape(null, false, Color.BLACK, 0);

        // Act
        shape.setTag(newTag);
        int actualTag = shape.getTag();

        // Assert
        assertEquals(newTag, actualTag, "The tag should be set to the new tag value.");
    }

    @ParameterizedTest
    @MethodSource("shapeProvider")
    void testCopy(GeoShape geoShape) {
        // Arrange
        GUIShape original = new GUIShape(geoShape, true, colorProvider().findFirst().orElse(null), 1);
        GUIShape copied = (GUIShape) original.copy();

        // Act & Assert
        assertEquals(original.getColor(), copied.getColor(), "Copied GUIShape should have the same color.");
        assertEquals(original.getTag(), copied.getTag(), "Copied GUIShape should have the same tag.");
        assertEquals(original.isFilled(), copied.isFilled(), "Copied GUIShape should have the same fill status.");
        assertNotSame(original, copied, "Copied GUIShape should not be the same instance as the original.");
    }

    @ParameterizedTest
    @MethodSource("shapeProvider")
    void testToString(GeoShape geoShape) {
        // Arrange
        GUIShape shape = new GUIShape(geoShape, true, colorProvider().findFirst().orElse(null), 1);
        String expected = "GUIShape," + shape.getColor().getRGB() + ",true,1," + shape.getShape().getClass().getSimpleName() + "," + shape.getShape().toString();

        // Act & Assert
        assertEquals(expected, shape.toString(), "The toString output should match the expected format.");
    }

    @ParameterizedTest
    @MethodSource("shapeProvider")
    void testIsSelected(GeoShape shape) {
        // Arrange
        GUIShape guiShape = new GUIShape(shape, false, Color.BLACK, 0);

        // Act
        boolean initialSelectedStatus = guiShape.isSelected();
        guiShape.setSelected(!initialSelectedStatus);
        boolean updatedSelectedStatus = guiShape.isSelected();

        // Assert
        assertEquals(!initialSelectedStatus, updatedSelectedStatus, "The selected status should reflect the value set by setSelected.");
    }

    @ParameterizedTest
    @MethodSource("shapeProvider")
    void testSetSelected(GeoShape shape) {
        // Arrange
        GUIShape guiShape = new GUIShape(shape, false, Color.BLACK, 0);
        boolean newSelectedStatus = !guiShape.isSelected();

        // Act
        guiShape.setSelected(newSelectedStatus);

        // Assert
        assertEquals(newSelectedStatus, guiShape.isSelected(), "The selected status should be updated after calling setSelected.");
    }
}