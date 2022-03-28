package geometries;
import primitives.Point;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable
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
    public List<Point> findIntsersections(Ray ray){
        List<Point> points = null;
        if(_intersectables != null) {
            for (Intersectable body: _intersectables) {
                List<Point> result = body.findIntsersections(ray);
                if(result != null){
                    if(points == null)
                        points = new LinkedList<Point>(result);
                    else
                        points.addAll(result);
                }
            }
        }
        return points;
    }
}
