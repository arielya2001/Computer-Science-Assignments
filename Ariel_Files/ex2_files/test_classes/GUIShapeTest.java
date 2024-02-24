package ex2.test_classes;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.awt.*;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.ValueSource;
import ex2.geo.*;
import ex2.gui.GUIShape;

import static org.junit.jupiter.api.Assertions.*;

class GUIShapeTest {

    //Stream methods that provides values for each test:
    static Stream<GeoShape> shapeProvider() {
        Rect_2D rectangle = new Rect_2D(new Point_2D(1.0, 2.0), new Point_2D(4.0, 6.0));
        Circle_2D circle = new Circle_2D(new Point_2D(3.0, 4.0), 6.0);
        Triangle_2D triangle = new Triangle_2D(new Point_2D(0.0, 0.0), new Point_2D(3.0, 0.0), new Point_2D(0.0, 3.0));

        return Stream.of(circle, rectangle, triangle);
    }
    static Stream<Color> colorProvider() {
        return Stream.of(
                Color.BLACK,
                new Color(255, 0, 0),
                new Color(0, 0, 255),
                new Color(255, 255, 0)
        );
    }
/////////////////////////////////////// Test functions: ///////////////////////////////////////////////////////////////

    /**
     * Verifies if the shape set by setShape matches the shape retrieved by getShape.
     * @param newShape the new shape to be set and tested
     */
    @ParameterizedTest
    @MethodSource("shapeProvider")
    void testSetShape(GeoShape newShape) {

        boolean isFilled = true;
        Color color = Color.GREEN;
        int tag = 4;
        GUIShape guiShape = new GUIShape(null, false, Color.BLUE, 2);


        guiShape.setShape(newShape);
        GeoShape updatedShape = guiShape.getShape();


        assertEquals(newShape, updatedShape, "The shape set by setShape should match the shape retrieved by getShape.");
    }
    /**
     * Verifies if the filled status matches the expected value.
     * @param expectedFilledStatus the expected filled status to be tested
     */
    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void testIsFilled(boolean expectedFilledStatus) {

        GUIShape shape = new GUIShape(null, expectedFilledStatus, Color.BLACK, 0);


        assertEquals(expectedFilledStatus, shape.isFilled(), "The filled status should match the expected value.");
    }
    /**
     * Verifies if the shape reflects the new filled status after setFilled is called.
     * @param newFilledStatus the new filled status to be set and tested
     */
    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void testSetFilled(boolean newFilledStatus) {

        GUIShape shape = new GUIShape(null, !newFilledStatus, Color.BLACK, 0);


        shape.setFilled(newFilledStatus);


        assertEquals(newFilledStatus, shape.isFilled(), "The shape should reflect the new filled status after setFilled is called.");
    }
    /**
     * Verifies if the color returned by getColor method matches the expected color.
     * @param expectedColor the expected color to be tested
     */
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

    /**
     * Verifies if the color is set to the new color as expected by setColor method.
     * @param newColor the new color to be set and tested
     */
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
    /**
     * Verifies if the tag returned by getTag method matches the expected tag value.
     * @param expectedTag the expected tag value to be tested
     */
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
    /**
     * Verifies if the tag is set to the new tag value as expected by setTag method.
     * @param newTag the new tag value to be set and tested
     */
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

    /**
     * Verifies if the copied GUIShape has the same color, tag, and fill status as the original GUIShape.
     * @param geoShape the geometric shape to be used for creating the original GUIShape
     */
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

    /**
     * Verifies if the string representation of GUIShape matches the expected format.
     * @param geoShape the geometric shape to be used for creating the GUIShape
     */
    @ParameterizedTest
    @MethodSource("shapeProvider")
    void testToString(GeoShape geoShape) {
        // Arrange
        GUIShape shape = new GUIShape(geoShape, true, colorProvider().findFirst().orElse(null), 1);
        String expected = "GUIShape," + shape.getColor().getRGB() + ",true,1," + shape.getShape().getClass().getSimpleName() + "," + shape.getShape().toString();

        // Act & Assert
        assertEquals(expected, shape.toString(), "The toString output should match the expected format.");
    }
    /**
     * Verifies if the selected status reflects the value set by setSelected method.
     * @param shape the geometric shape to be used for creating the GUIShape
     */
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
    /**
     * Verifies if the selected status is updated after calling setSelected method.
     * @param shape the geometric shape to be used for creating the GUIShape
     */
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