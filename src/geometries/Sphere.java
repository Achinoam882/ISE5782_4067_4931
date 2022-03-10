package geometries;
import primitives.*;
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
}
