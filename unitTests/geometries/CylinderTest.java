package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for geometries.Cylinder class
 * @author Achinoam & Malka
 */
class CylinderTest {

    /**
     * Test method for {@link geometries.Cylinder#getNormal(Point)} }
     */
    @Test
    void testGetNormal()
    {
        Cylinder cy=new Cylinder(new Ray(new Point(0,0,0),new Vector(0,0,1)),4,4);
        // ============ Equivalence Partitions Tests ==============
        //   There are three tests here

        //TC01: Test for the points on the cylinder's side
        assertEquals( new Vector(1,0,0), cy.getNormal(new Point(1, 0, 1)),"Bad normal to Cylinder");

        //TC02: Test for the points that are on the top of the cylinder . The normal is the axis's vector
        assertEquals(new Vector(0,0,1), cy.getNormal(new Point(1, 0, 4)),"Bad normal to Cylinder");

        //TC03: Test for the points that are on the bottom of the cylinder . The normal is the axis's vector
        assertEquals( new Vector(0,0,1), cy.getNormal(new Point(1, 0, 0)),"Bad normal to Cylinder");

        // =============== Boundary Values Tests ==================
        //TC04: Test for the points in the seam between the top base and the cylinder side-----
        assertEquals( new Vector(0,0,1), cy.getNormal(new Point(4, 0, 4)),"Bad normal to Cylinder");
        //TC05: Test for the points in the seam between the bottom base and the cylinder side
        assertEquals( new Vector(0,0,1), cy.getNormal(new Point(4, 0, 0)),"Bad normal to Cylinder");
    }


}