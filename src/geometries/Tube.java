package geometries;
import primitives.*;

import java.util.List;

/**
 *
 *  * @author Achinoam & Malka
 */
public class Tube extends Geometry{
   final protected Ray axisRay;
    final protected double radius;

    public Ray getAxisRay() {
        return axisRay;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    @Override
   /** public Vector getNormal(Point point)
    {
        // TODO Auto-generated method stub
        //n = normalize(P - O)
        // O is projection of P on cylinder's ray:
        // t = v (P – P0)
        // O = P0 + tv
        Point p0 = axisRay.getP0();
        Vector v = axisRay.getDir();
        //t = v (P – P0)
        double t = point.subtract(p0).dotProduct(v);
        // O = P0 + tv
        Point o=null;
        if (!Util.isZero(t))// if it's close to 0, we'll get ZERO vector exception
            o = p0.add(v.scale(t));
        Vector n = point.subtract(o).normalize();
        return n;
    }*/

    public Vector getNormal(Point p){
        double t = (axisRay.getDir()).dotProduct(p.subtract(axisRay.getP0()));
        if (t != 0) //in case the point is not across the ray point
        {
            Point center = axisRay.getP0().add(axisRay.getDir().scale(t));
            return p.subtract(center).normalize();
        }
        else // in case the point is across the ray point
            return p.subtract(axisRay.getP0()).normalize();
    }


    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return null;
    }

}
