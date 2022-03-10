package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for geometries.Plane class
 * @author Achinoam & Malka
 */
class PlaneTest {

    /**
     * Test method for ctor who get 3 point.
     */
    @Test
    public void testPlane() {

        // =============== Boundary Values Tests ==================

        // TC01: First and second points are the same
        try {
            Plane plane = new Plane(new Point(1, 2, 3), new Point(1, 2, 3), new Point(3, 6, 9));
            fail("ERROR: Two points are the same point, exception was not thrown");
        } catch (Exception e) {
        }

        // TC02: The three points are on the same line
        try {
            Plane plane = new Plane(new Point(1, 2, 3), new Point(2, 4, 6), new Point(3, 6, 9));
            fail("ERROR: The points are on the same line, exception was not thrown");
        } catch (Exception e) {
        }
    }

    /**
     * Test method for getNormal func who get a point as parameter.
     */


    /**
     * Test method for {@link geometries.Plane#getNormal(Point)} }
     */
        @Test
        void getNormal()
    {

            // ============ Equivalence Partitions Tests ==============

            // TC01: There is a simple single test here
            Plane pl = new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1));
            double sqrt3 = Math.sqrt(1d / 3); // Normalizing the vector components

            // Two opposite sides of the vector must be checked:
            assertTrue(new Vector(sqrt3, sqrt3, sqrt3).equals(pl.getNormal(new Point(0, 0, 1)))
                            || new Vector(-1 * sqrt3, -1 * sqrt3, -1 * sqrt3).equals(pl.getNormal(new Point(0, 0, 1)))
                    ,"Bad normal to plane");
        }
    }

