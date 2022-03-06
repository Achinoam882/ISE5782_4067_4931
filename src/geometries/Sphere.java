package geometries;
import primitives.*;
public class Sphere implements Geometry
{
  final private Point center;
   final private double  radius;

    @Override
    public Vector getNormal(Point p) {
        return null;
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
