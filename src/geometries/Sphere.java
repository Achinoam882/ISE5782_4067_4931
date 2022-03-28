package geometries;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.*;

/**
 *
 *  * @author Achinoam & Malka
 */
public class Sphere implements Geometry
{
  final private Point center;
   final private double  radius;

    @Override
    public Vector getNormal(Point point)
    {
        Vector p = point.subtract(center);
        return p.normalize();
    }
    public Sphere(double radius, Point center)
    {
      this.radius=radius;
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
    public List<Point> findIntsersections(Ray ray) {
        double r = this.radius;

        // Special case: if point p0 == center, that mean that all we need to calculate
        // is the radios mult scalar with the direction, and add p0
        if (center.equals(ray.getP0())) {
            Point p1 = ray.getPoint(r);
            LinkedList<Point> result = new LinkedList<Point>();
            result.add(p1);
            return result;
        }

        Vector u = center.subtract(ray.getP0());
        double tm = u.dotProduct(ray.getDir());
        double d = Math.sqrt(alignZero(u.lengthSquared() - tm * tm));

        if (d >= r) //also In case the cut is tangent to the object still return null - d = r
            return null;

        double th = Math.sqrt(r * r - d * d);
        double t1 = tm + th;
        double t2 = tm - th;

        if(alignZero(t1) > 0 || alignZero(t2) > 0){
            LinkedList<Point> result = new LinkedList<Point>();
            if(alignZero(t1) > 0){
                Point p1 = ray.getPoint(t1);
                result.add(p1);
            }
            if(alignZero(t2) > 0){
                Point p2 = ray.getPoint(t2);
                result.add(p2);
            }
            return result;
        }
        else { //In case there are no intersections points
            return null;
        }
    }

}
