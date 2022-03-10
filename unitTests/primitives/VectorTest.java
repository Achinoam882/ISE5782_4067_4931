package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Unit tests for primitives.Vector class
 * @author Achinoam & Malka
 */

class VectorTest
{
    /**
     *
     */
    @Test
    public void testConstructors() {

        // =============== Boundary Values Tests ==================

        try {
            // TC01: No exception in case the vector(double, double, double) we build is (0,0,0)
            Vector v1 = new Vector(0, 0, 0);
            fail("Vector(double, double, double) for Vector zero does not throw an exception");

            // TC02: No exception in case the vector(Double3) we build is (ZERO)
            v1 = new Vector(Double3.ZERO);
            fail("Vector(Point3D) for Vector zero does not throw an exception");
        } catch (Exception e) {
        }
    }


    @Test
    void testLength()
    {
        // ============ Equivalence Partitions Tests ==============
        // TC01: length wrong calculation
        assertTrue(isZero(new Vector(0, 3, 4).length() - 5), "length() wrong value");
    }

    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: squared length wrong calculation
        Vector v1 = new Vector(1, 2, 3);
        assertTrue(isZero(v1.lengthSquared() - 14),"lengthSquared() wrong value");
    }

    @Test
    void testDotProduct()
    {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Orthogonal vectors dot product doesn't equal zero
        assertTrue(isZero(v1.dotProduct(v3)),"dotProduct() for orthogonal vectors is not zero");

        // TC02: Wrong vectors dot product calculations
        assertTrue(isZero(v1.dotProduct(v2) + 28),"dotProduct() wrong value");
    }

    @Test
    void testCrossProduct()
    {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001,"crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)),"crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)),"crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),"crossProduct() for parallel vectors does not");
    }
    /**
     *
     */
    @Test
    void testNormalize()
    {
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();

        // ============ Equivalence Partitions Tests ==============
        // TC01: normalized() doesn't creates a new vector (but it should)
        assertFalse( u == v, "normalized() function does not create a new vector");

        // TC02:  normalize() doesn't make it a unit vector
        assertTrue(isZero(u.length() - 1), "normalize() result is not a unit vector");
    }

    @Test
    void testAdd()
    {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Wrong vectors addition
        assertEquals(new Vector(4,7,16) , new Vector(3,5,7).add(new Vector(1, 2, 9)), "Vectors addition doesn't work");
    }

    @Test
    void testScale()
    {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Wrong vectors scale multiplication
        assertEquals(new Vector(3, 6, 9), new Vector(1, 2, 3).scale(3), "scale() wrong scale multiplication");
    }



}