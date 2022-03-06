/**
 * Plane class
 * Is represented by a point and normal
 * @author Achinoam & Malka
 */
package geometries;
import primitives.*;

public class Plane implements Geometry {
    final private Point _q0;
    final  private Vector _normal;

    public Plane(Point p1, Point p2, Point p3) {
        _q0 = p1;
        _normal = null;
    }

    public Plane(Point p0, Vector normal) {
        _q0 = p0;
        _normal = normal.normalize();
    }

    public Point get_q0() {
        return _q0;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "_q0=" + _q0 +
                ", _normal=" + _normal +
                '}';
    }

    public Vector get_normal() {
        return _normal;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
    public Vector getNormal() {
        return null;
    }
}
