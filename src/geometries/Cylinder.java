package geometries;
import primitives.*;
public class Cylinder extends  Tube {
    private double height;

    @Override
    public Vector getNormal(Point p) {
        return super.getNormal(p);
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                "} " + super.toString();
    }

    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }
}
