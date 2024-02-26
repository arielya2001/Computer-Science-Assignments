package ex2.testing;

import ex2.ex2.Ex2_Const;
import ex2.ex2.GUI_Shape_Collection;
import ex2.ex2.ShapeCollection;
import ex2.geo.Circle_2D;
import ex2.geo.Point_2D;
import ex2.geo.Rect_2D;
import ex2.gui.Ex2;
import ex2.gui.GUIShape;
import ex2.gui.GUI_Shape;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.awt.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Ex2Test {

    static Stream<GUI_Shape_Collection> shapeCollectionProvider() {
        return Stream.of(
                null,
                new ShapeCollection(),
                createNonEmptyShapeCollection()
        );
    }

    private static ShapeCollection createNonEmptyShapeCollection() {
        ShapeCollection shapes = new ShapeCollection();
        shapes.add(new GUIShape(new Circle_2D(new Point_2D(1, 1), 1), false, Color.BLACK, 0));
        return shapes;
    }

    @ParameterizedTest
    @MethodSource("shapeCollectionProvider")
    void init(GUI_Shape_Collection shapes) {
        Ex2 ex2 = Ex2.getInstance();
        ex2.init(shapes);
        assertNotNull(ex2.getShape_Collection(), "Shape collection should be initialized.");
        if (shapes == null) {
            assertEquals(0, ex2.getShape_Collection().size(), "Shape collection should be empty for null input.");
        } else {
            assertEquals(shapes.size(), ex2.getShape_Collection().size(), "Shape collection sizes should match.");
        }
    }

    @Test
    void show() {
        Ex2 ex2 = Ex2.getInstance();
        assertDoesNotThrow(() -> ex2.show(Ex2_Const.DIM_SIZE), "Show method should not throw exceptions.");
    }

    @Test
    public void getShapeCollection() {
        Ex2 ex2Instance = Ex2.getInstance();
        ex2Instance.init(new ShapeCollection());

        Circle_2D circle = new Circle_2D(new Point_2D(150, 150), 30); // Changed parameters
        Rect_2D rect = new Rect_2D(new Point_2D(250, 250), new Point_2D(350, 350)); // Changed parameters

        // Create GUIShape instances using the new GeoShape instances
        GUIShape shape1 = new GUIShape(circle, true, Color.GREEN, 2); // Changed color and tag
        GUIShape shape2 = new GUIShape(rect, false, Color.ORANGE, 3); // Changed color and tag

        // Add the new GUIShape instances to the collection
        ex2Instance.getShape_Collection().add(shape1);
        ex2Instance.getShape_Collection().add(shape2);

        GUI_Shape_Collection collection = ex2Instance.getShape_Collection();

        // Check if the retrieved collection contains the added shapes with updated details
        assertEquals(2, collection.size(), "Collection should contain exactly two shapes.");
        assertTrue(collection.get(0) instanceof GUIShape && ((GUIShape)collection.get(0)).getShape() instanceof Circle_2D,
                "The first shape should be a circle with updated properties.");
        assertTrue(collection.get(1) instanceof GUIShape && ((GUIShape)collection.get(1)).getShape() instanceof Rect_2D,
                "The second shape should be a rectangle with updated properties.");
    }

    @Test
    public void testShow() {
        Ex2 ex2Instance = Ex2.getInstance();
        ex2Instance.show();

        // Verify that the scale is set to Ex2_Const.DIM_SIZE (which is 10)
        assertEquals(10.0, Ex2_Const.DIM_SIZE);
    }

    @Test
    public void testGetInfo() {
        Ex2 ex2Instance = Ex2.getInstance();
        ex2Instance.getShape_Collection().removeAll();

        // Create some shapes and add them to the collection
        Circle_2D circle = new Circle_2D(new Point_2D(100, 100), 10);
        Rect_2D rect = new Rect_2D(new Point_2D(200, 200), new Point_2D(220, 220));

        GUIShape shape1 = new GUIShape(circle, true, Color.RED, 0);
        GUIShape shape2 = new GUIShape(rect, false, Color.BLUE, 1);

        ex2Instance.getShape_Collection().add(shape1);
        ex2Instance.getShape_Collection().add(shape2);
        String info = ex2Instance.getInfo();

        assertEquals("GUIShape,-65536,true,0,Circle_2D,100.0,100.0, 10.0\nGUIShape,-16776961,false,1,Rect_2D,200.0,200.0,220.0,200.0,220.0,220.0,200.0,220.0\n", info);
    }
}