/**
* Ray class represents a vector with a location point
 * it contains a Vector and a Point
 *  * @author Achinoam & Malka
 */
package primitives;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static primitives.Util.isZero;

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
    public Point getPoint(double t) {
        if (isZero(t)){
            return p0;
        }
        return p0.add(dir.scale(t));

    }
    /**
     *
     * @param listPoint
     * @return The closest point to the began of the ray
     */
    public Point findClosestPoint(List<Point> listPoint) {

        if (listPoint == null) //In case of an empty list
            return null;
        Point closePoint = listPoint.get(0);	//Save the first point in the list
        for (Point p : listPoint) {
            if (closePoint.distance(p0) > p.distance(p0))	//In case the distance of closes point is bigger than the p point
                closePoint = p;
        }
        return closePoint;
    }

}

