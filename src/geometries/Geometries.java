package geometries;
import primitives.Point;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable
{

    private List<Intersectable>_intersectables ;
    /**
     * default constructor
     */    public Geometries() {
        _intersectables =  new LinkedList<>();
    }
    /**
     * constructor of Geometries
     * @param geometries+- array of {@link Intersectable} objects
     */
    public Geometries(Intersectable...geometries) {
        _intersectables = List.of(geometries);        /*Collections.addAll(_intersectables, geometries);*/

    }
    public void add(Intersectable... geometries){
        _intersectables.addAll(List.of(geometries));
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {

        if (box != null && !box.IsRayHitBox(ray))
            return null;

        if (_intersectables.isEmpty())		// In case the collection is empty
            return null;

        List<GeoPoint> result = null, points;

        for (Intersectable geom : _intersectables)	// The loop checks intersections for each shape
        {
            points = geom.findGeoIntersectionsHelper(ray);

            if (points != null) 				// In case there are intersections
            {
                if (result == null)  		    // If we only now start to add shape intersections - assigns the points to result
                {
                    result = new LinkedList<GeoPoint>(points);
                }
                else
                    result.addAll(points);
            }
        }
        return result;
    }



    /**
     * Function that create box for each geometry
     */
    public void setGeometriesBoxes() {
        for(Intersectable geo : _intersectables) {
            geo.setBox();
        }
    }

    /**
     * Create big box that will contain all of the geometries
     */
    @Override
    public void setBox() {

        setGeometriesBoxes(); //Create box for each geometry

        Intersectable intersecI = _intersectables.get(0);
        double maxX = intersecI.box.maxX;
        double maxY = intersecI.box.maxY;
        double maxZ = intersecI.box.maxZ;
        double minX = maxX;
        double minY = maxY;
        double minZ = maxZ;

        for(Intersectable geo : _intersectables) {	//For each geometry find the max and min of is box,
            //and create the geometries box

            if (maxX < geo.box.maxX)
                maxX = geo.box.maxX;

            if (maxY < geo.box.maxY)
                maxY = geo.box.maxY;

            if (maxZ < geo.box.maxZ)
                maxZ = geo.box.maxZ;

            if (minX > geo.box.minX)
                minX = geo.box.minX;

            if (minY > geo.box.minY)
                minY = geo.box.minY;

            if (minZ > geo.box.minZ)
                minZ = geo.box.minZ;
        }
        box = new Box(maxX, maxY, maxZ, minX, minY, minZ);
    }
}
