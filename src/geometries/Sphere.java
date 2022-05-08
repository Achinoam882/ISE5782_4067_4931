package geometries;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.*;

/**
 *
 *  * @author Achinoam & Malka
 */
public class Sphere extends Geometry {
    final private Point center;
    final private double radius;

    @Override
    public Vector getNormal(Point point) {
        Vector p = point.subtract(center);
        return p.normalize();
    }

    public Sphere(double radius, Point center) {
        this.radius = radius;
        this.center = center;
    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }


    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        double r = this.radius;

        // Special case: if point p0 == center, that mean that all we need to calculate
        // is the radios mult scalar with the direction, and add p0
        if (center.equals(ray.getP0())) {
            var p1 = ray.getPoint(r);
            return List.of(new GeoPoint(this, p1));

        }

        Vector u = center.subtract(ray.getP0());
        double tm = u.dotProduct(ray.getDir());
        double d = Math.sqrt(alignZero(u.lengthSquared() - tm * tm));

        if (d >= r) //also In case the cut is tangent to the object still return null - d = r
            return null;

        double th = Math.sqrt(r * r - d * d);
        double t1 = tm + th;
        double t2 = tm - th;
        Point p1,p2;
        if (alignZero(t1) > 0 || alignZero(t2) > 0)
        {

            List<GeoPoint> myList = new LinkedList<GeoPoint>();

            if (alignZero(t1) > 0) {
                 p1 = ray.getPoint(t1);
               myList.add(new GeoPoint(this, p1));

            }
            if (alignZero(t2) > 0) {
                 p2 = ray.getPoint(t2);
                 myList.add(new GeoPoint(this, p2));
            }
            return myList;
        } else { //In case there are no intersections points
            return null;
        }
        /* return List.add(new GeoPoint(this, p));*/

    }
}