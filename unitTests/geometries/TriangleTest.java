package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for geometries.Triangle class
 * @author Achinoam & Malka
 */
class TriangleTest
{
    /**
     * Test method for {@link geometries.Triangle#getNormal(Point)} }
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Triangle triangle = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3); // Normalizing the vector components
        assertTrue(new Vector(sqrt3, sqrt3, sqrt3).equals(triangle.getNormal(new Point(0, 0, 1)))
                ||  new Vector(-1 * sqrt3, -1 * sqrt3, -1 * sqrt3).equals(triangle.getNormal(new Point(0, 0, 1))),"Bad normal to triangle");
    }
}