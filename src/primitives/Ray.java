/**
* Ray class represents a vector with a location point
 * it contains a Vector and a Point
 *  * @author Achinoam & Malka
 */
package primitives;

import geometries.Intersectable.GeoPoint;

import java.util.List;
import java.util.Objects;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Ray {
   final Point p0;
    final Vector dir;
    private static final double DELTA = 0.1;


    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    public Ray(Point p0, Vector dir, Vector normal) {
        this.dir = dir;
        double nv = alignZero(normal.dotProduct(dir));
        if (!isZero(nv)){
            Vector moveVector = normal.scale(nv > 0 ? DELTA : -DELTA);
            this.p0 = p0.add(moveVector);
        }else{
            this.p0 = p0;
        }

        //Vector delta = normal.scale(normal.dotProduct(dir) > 0 ? DELTA :  -DELTA);
        //Point point = p0.add(delta);
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
     * @param points
     * @return The closest point to the began of the ray
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }
    /**
     *
     * @param geoPoints
     * @return The closest point to the began of the ray
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPoints) {

        if (geoPoints == null) //In case of an empty list
            return null;
        GeoPoint closePoint = geoPoints.get(0);	//Save the first point in the list
        for (GeoPoint p : geoPoints) {
            if (closePoint.point.distance(p0) > p.point.distance(p0))	//In case the distance of closes point is bigger than the p point
                closePoint = p;
        }
        return closePoint;
    }

}

