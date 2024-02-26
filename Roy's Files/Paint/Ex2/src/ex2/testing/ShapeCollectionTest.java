package ex2.testing;

import ex2.ex2.Ex2_Const;
import ex2.ex2.ShapeCollection;
import ex2.geo.Circle_2D;
import ex2.geo.Point_2D;
import ex2.geo.ShapeComp;
import ex2.gui.GUIShape;
import ex2.gui.GUI_Shape;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ShapeCollectionTest {

    Point_2D p1 = new Point_2D(1.0, 2.0);
    Point_2D p2 = new Point_2D(2.0, 2.0);
    double radius = 5.0, radius2 = 7.0;
    Color color = Color.black;
    int tag = 1;
    private final String testFileName = "testShapeCollectionSave.txt";
    private final Path testFilePathLoad = Paths.get("testShapeCollectionLoad.txt");
    private Path testFilePath;
    public static final ShapeComp CompByArea = new ShapeComp(Ex2_Const.Sort_By_Area);

    // Provide your test parameters
    static Stream<ShapeComp> sortTestParameters() {
        return Stream.of(
                ShapeComp.CompByArea,
                ShapeComp.CompByPerimeter,
                ShapeComp.CompByTag,
                ShapeComp.CompByToString,
                ShapeComp.CompByAntiArea,
                ShapeComp.CompByAntiPerimeter,
                ShapeComp.CompByAntiTag,
                ShapeComp.CompByAntiToString
        );
    }

    @Test
    void get() {
        int i = 0;
        ShapeCollection shapes = new ShapeCollection();
        Circle_2D c1 = new Circle_2D(p2, radius);
        GUI_Shape gs = new GUIShape(c1, false, color, tag);
        shapes.add(gs);
        assertTrue(shapes.get(i).getShape() instanceof Circle_2D, "the shapes should match.");
    }

    @Test
    void size() {
        ShapeCollection shapes = new ShapeCollection();
        Circle_2D c1 = new Circle_2D(p2, radius);
        GUI_Shape gs = new GUIShape(c1, false, color, tag);
        shapes.add(gs);
        assertEquals(1, shapes.size(), "Size should match.");
    }

    @Test
    void sizeAfterRemoving() {
        ShapeCollection shapes = new ShapeCollection();
        Circle_2D c1 = new Circle_2D(p2, radius);
        Circle_2D c2 = new Circle_2D(p1, radius);
        GUI_Shape gs = new GUIShape(c1, false, color, tag);
        GUI_Shape gs2 = new GUIShape(c2, false, color, tag);
        shapes.add(gs);
        shapes.add(gs2);
        shapes.removeElementAt(0);
        assertEquals(1, shapes.size(), "Size should match.");
    }

    @Test
    void removeElementAt() {
        ShapeCollection shapes = new ShapeCollection();
        Circle_2D c1 = new Circle_2D(p2, radius);
        Circle_2D c2 = new Circle_2D(p1, radius);
        Circle_2D c3 = new Circle_2D(p1, radius);
        GUI_Shape gs = new GUIShape(c1, false, color, tag);
        GUI_Shape gs2 = new GUIShape(c2, false, color, tag);
        GUI_Shape gs3 = new GUIShape(c3, false, color, tag);
        shapes.add(gs);
        shapes.add(gs2);
        shapes.add(gs3);
        shapes.removeElementAt(1);
        assertEquals(gs3, shapes.get(1), "the shape in the index after the removed shape should" +
                "be index - 1.");
    }

    @Test
    void addAt() {
        ShapeCollection shapes = new ShapeCollection();
        Circle_2D c1 = new Circle_2D(p2, radius);
        Circle_2D c2 = new Circle_2D(p1, radius);
        Circle_2D c3 = new Circle_2D(p1, radius);
        GUI_Shape gs1 = new GUIShape(c1, false, color, tag);
        GUI_Shape gs2 = new GUIShape(c2, false, color, tag);
        GUI_Shape gs3 = new GUIShape(c3, false, color, tag);
        shapes.add(gs1); // [gs1]
        shapes.add(gs2); // [gs1, gs2]
        shapes.addAt(gs3, 1); // Expected: [gs1, gs3, gs2]

        assertEquals(gs3, shapes.get(1), "gs3 should be at index 1 after insertion.");
        assertEquals(gs2, shapes.get(2), "gs2 should be shifted to index + 1.");
        assertEquals(3, shapes.size(), "Collection should contain the correct amount of\" +\n" +
                "                \" elements after adding.");
    }

    @Test
    void add() {
        ShapeCollection shapes = new ShapeCollection();
        Circle_2D c1 = new Circle_2D(p2, radius);
        GUI_Shape gs1 = new GUIShape(c1, false, color, tag);
        shapes.add(gs1); // [gs1]

        assertEquals(1, shapes.size(), "Collection should contain the correct amount of" +
                " elements after adding.");
    }

    @Test
    void copy() {
        ShapeCollection original = new ShapeCollection();
        Circle_2D c1 = new Circle_2D(p2, radius);
        Circle_2D c2 = new Circle_2D(p1, radius);
        GUI_Shape gs1 = new GUIShape(c1, false, color, tag);
        GUI_Shape gs2 = new GUIShape(c2, false, color, tag);
        original.add(gs1); // [gs1]
        original.add(gs2); // [gs1, gs2]

        ShapeCollection copy = original.copy();
        // Check if the copy has the same size as the original
        assertEquals(original.size(), copy.size(), "Copy should have the same size as the original collection.");

        // Verify that the objects are not the same (deep copy)
        for (int i = 0; i < original.size(); i++) {
            assertNotSame(original.get(i), copy.get(i), "Elements at index " + i +
                    " should not be the same in the original and the copy.");
        }
    }

    @ParameterizedTest
    @MethodSource("sortTestParameters")
    void sort(ShapeComp comp) {
        ShapeCollection shapes = new ShapeCollection();
        Point_2D p1 = new Point_2D(0, 0);
        Point_2D p2 = new Point_2D(1, 1);
        double radius1 = 3.0, radius2 = 5.0;
        Color color = Color.black;
        int tag = 1;

        // Create circles of different sizes
        Circle_2D smallCircle = new Circle_2D(p1, radius1);
        Circle_2D largeCircle = new Circle_2D(p2, radius2);

        // Create GUI shapes for the circles
        GUI_Shape smallShape = new GUIShape(smallCircle, false, color, tag);
        GUI_Shape largeShape = new GUIShape(largeCircle, false, color, tag);

        // Add shapes in reverse order
        shapes.add(largeShape);
        shapes.add(smallShape);

        shapes.sort(comp);
        assertTrue(comp.compare(shapes.get(0), shapes.get(1)) <= 0, "Shapes should be sorted " +
                "according to the provided comparator.");
    }

    @Test
    void removeAll() {
        ShapeCollection shapes = new ShapeCollection();
        Circle_2D c1 = new Circle_2D(p2, radius);
        Circle_2D c2 = new Circle_2D(p1, radius2);
        GUI_Shape gs1 = new GUIShape(c1, false, color, tag);
        GUI_Shape gs2 = new GUIShape(c2, false, color, tag);
        shapes.add(gs1); // [gs1]
        shapes.add(gs2); // [gs1, gs2]

        assertNotEquals(0, shapes.size(), "Collection should not be empty before removeAll is called.");

        shapes.removeAll();

        // verify the collection is empty
        assertEquals(0, shapes.size(), "Collection should be empty after removeAll is called.");
    }


    @Test
    void save() throws IOException {
        ShapeCollection shapes = new ShapeCollection();
        shapes.add(new GUIShape(new Circle_2D(new Point_2D(2.0, 2.0), 5.0), false, Color.black, 1));

        String testFileName = "testSaveShapes";
        shapes.save(testFileName);

        String expected = "GUIShape,-16777216,false,1,Circle_2D,2.0,2.0, 5.0";
        String content = Files.readString(Paths.get(testFileName + ".txt")).trim();

        assertEquals(expected, content, "Saved file content mismatch.");
    }


    @Test
    void load() throws IOException {
        ShapeCollection shapes = new ShapeCollection();

        String testContent = "GUIShape,-16777216,false,1,Circle_2D,2.0,2.0,5.0\n" +
                "GUIShape,-16777216,false,1,Circle_2D,1.0,2.0,7.0";
        Files.writeString(testFilePathLoad, testContent);

        // Load the shapes from the test file
        shapes.load(testFilePathLoad.toString());

        // Verify that the shapes were loaded correctly
        assertEquals(2, shapes.size(), "The collection should contain the shapes after loading.");
    }

        @Test
    void testToString() {
        ShapeCollection shapes = new ShapeCollection();
        Circle_2D c1 = new Circle_2D(p2, radius);
        Circle_2D c2 = new Circle_2D(p1, radius2);
        GUI_Shape gs1 = new GUIShape(c1, false, color, tag);
        GUI_Shape gs2 = new GUIShape(c2, false, color, tag);

        shapes.add(gs1);
        shapes.add(gs2);

        String expected = gs1 + "\n" + gs2 + "\n";
        String actual = shapes.toString();

        assertEquals(expected, actual, "The toString method should return a newline-separated list of shapes.");
    }
}