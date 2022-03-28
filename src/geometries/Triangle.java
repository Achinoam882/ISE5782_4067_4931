/**
 * Triangle class
 * represents two-dimensional Triangle in 3D Cartesian coordinate system
 * @author Achinoam & Malka
 *
 */
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;

public class Triangle extends Polygon{
    public Triangle(Point p1, Point p2, Point p3)
    {
        super(p1,p2,p3);
    }

    @Override
    public String toString() {
        return "Triangle{} " + super.toString();
    }

    @Override
    public Vector getNormal(Point p){
        return super.getNormal(p);
    }

    @Override
    public List<Point> findIntsersections(Ray ray) {
        List<Point> resultPoint = plane.findIntsersections(ray);

        if (resultPoint == null) // In case there is no intersection with the plane return null
            return null;

        Vector v1 = vertices.get(0).subtract(ray.getP0());
        Vector v2 = vertices.get(1).subtract(ray.getP0());
        Vector v3 = vertices.get(2).subtract(ray.getP0());
        Vector n1 = (v1.crossProduct(v2)).normalize();
        Vector n2 = (v2.crossProduct(v3)).normalize();
        Vector n3 = (v3.crossProduct(v1)).normalize();
        double t1 = alignZero(n1.dotProduct(ray.getDir()));
        double t2 = alignZero(n2.dotProduct(ray.getDir()));
        double t3 = alignZero(n3.dotProduct(ray.getDir()));

        if (t1 == 0 || t2 == 0 || t3 == 0) // In case one or more of the scalars equals zero
            return null; // that mean the point is not inside the triangle

        if ((t1 > 0 && t2 > 0 && t3 > 0) || (t1 < 0 && t2 < 0 && t3 < 0)) { // In case the all scalars are in the same sign, the point is in the triangle
            LinkedList<Point> result = new LinkedList<Point>();
            result.add(resultPoint.get(0));
            return result;
            //} else if (t1 < 0 && t2 < 0 && t3 < 0) { // In case the all scalars are in the same sign, the point is in the triangle
            //    LinkedList<Point> result = new LinkedList<Point>();
            //    result.add(resultPoint.get(0));
            //    return result;
        } else
            return null;	//If the scalars are in a different sign
    }
}
