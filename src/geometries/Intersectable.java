package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.Objects;

/**
 * common interface for all graphic 3D objects
 * that intersect with a specific Ray {@link primitives.Ray}
 */
public abstract class Intersectable {
    public Box box;

    /**
     * find all intersection points {@link Point}
     * that intersect the Shape from a specific Ray {@link Ray}
     *
     * @param ray Ray pointing towards the graphic object
     * @return immutable list of intersection points
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * represent point and the geometry body
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        /**
         * Ctor - build the GeoPoint
         *
         * @param geometry - the geometry Body that have a point
         * @param point    - the point on the geometry body
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (geometry == null)
                return false;
            if (!(o instanceof GeoPoint)) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return this.geometry.equals(geoPoint.geometry) && this.point.equals(geoPoint.point);
        }

        @Override
        public int hashCode() {
            return Objects.hash(geometry, point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }
    /**
     * Function for finding all the intersection points of the Ray in the Geometry
     *
     * @param ray - The ray that crosses the body
     * @return List of The intersect Point , null if there is no intersection point
     */
    public List<GeoPoint> findGeoIntersections(Ray ray)
    {

        return findGeoIntersectionsHelper(ray);
    }
    /**
     * Function Return all the intersection Points of the Ray in the Geometry in
     * specific distance ( that not bigger than max)
     *
     * @param ray - The ray that crosses the body
     * @return list of the intersection points
     */
    protected abstract   List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
    /**
     * Abstract method for creating box for each geometry
     */
    public abstract void setBox();

}
