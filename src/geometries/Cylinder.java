package geometries;
import primitives.*;

import java.util.List;

/**
 *Cylinder class
 * @author Achinoam & Malka
 * */
public class Cylinder extends  Tube {
    private double height;

    @Override
    public Vector getNormal(Point point)
    {
        //n = normalize(P - O)
        // O is projection of P on cylinder's ray:
        // t = v (P – P0)
        // O = P0 + tv
        Point p0 = axisRay.getP0();
        Vector v = axisRay.getDir();
        //t = v (P – P0)

        if(point.subtract(p0).dotProduct(v)==0)
            {return v;}
        if(point.subtract(p0.add(v.scale(height))).dotProduct(v)==0)
            {return v;}
        return super.getNormal(point);
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
    @Override
    public List<Point> findIntsersections(Ray ray) {
        return null;
    }
}
