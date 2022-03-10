/**
* Ray class represents a vector with a location point
 * it contains a Vector and a Point
 *  * @author Achinoam & Malka
 */
package primitives;

import java.util.Objects;

public class Ray {
   final Point p0;
    final Vector dir;

    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return Objects.equals(getP0(), ray.getP0()) && Objects.equals(getDir(), ray.getDir());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getP0(), getDir());
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
}

