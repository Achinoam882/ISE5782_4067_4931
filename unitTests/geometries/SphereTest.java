package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for geometries.Sphere class
 * @author Achinoam & Malka
 */
class SphereTest {

    /**
     * Test method for {@link geometries.Sphere#getNormal(Point)} }
     */
    @Test
    public void testGetNormal() {

        // ============ Equivalence Partitions Tests ==============

        // TC01: Wrong normal calculation
        Sphere sph = new Sphere(5,new Point(1,1,1)); // The equation for the sphere is: (x-1)^2 + (y-1)^2 + (z-1)^2 = 25

        // The vector is: (4,5,1) - (1,1,1) = (3,4,0) => After normalization: (3/5, 4/5, 0)
        assertEquals(new Vector(3d/5, 4d/5, 0), sph.getNormal(new Point(4, 5, 1)), "Bad normal to Sphere");
    }


}