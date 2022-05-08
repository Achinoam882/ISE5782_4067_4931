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
public List<GeoPoint> findGeoIntersectionsHelper(Ray ray){
    if (_intersectables.isEmpty()) // In case the collection is empty
        return null;

    List<GeoPoint> points = null, result;
    for (Intersectable body: _intersectables) {
        result = body.findGeoIntersectionsHelper(ray);
        if(result != null){
            if(points == null)
                points = new LinkedList<GeoPoint>(result);
            else
                points.addAll(result);
        }
    }
    return points;
}
}
