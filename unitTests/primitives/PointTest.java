package primitives;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Unit tests for primitives.Point class
 *  * @author Achinoam & Malka
 */
class PointTest {

    /**
     * Test method for add function.
     */
    @Test
    void testAdd()
    {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Point addition wrong calculation
        assertEquals(new Point(0,0,0), new Point(1, 2, 3).add(new Vector(-1, -2, -3)), "Point + Vector does not work correctly");
    }

    @Test
    void testSubtract()
    {
        assertEquals(new Vector(1,1,1),new Point(2,3,4).subtract(new Point(1,2,3)),"Points subtraction does not work correctly");
    }

    /**
     * Test method for distanceSquared function.
     */
    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Point squared distance wrong calculation
        assertTrue(isZero(new Point(3, 5, 6).distanceSquared(new Point(1, 2, 3)) - 22), "Squared distance wrong calculation");
    }

    /**
     * Test method for distance function.
     */
    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Point distance wrong calculation
        assertTrue(isZero(new Point(4, 6, 3).distance(new Point(1, 2, 3)) - 5), "Squared distance wrong calculation");
    }
}