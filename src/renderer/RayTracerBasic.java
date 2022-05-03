package renderer;
import java.util.List;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import static primitives.Util.*;
import scene.Scene;

/**
 * basic ray trace heir from the rayTraceBase class the class is to calculate
 * the closest point to the ray from all the intersections and calculate the
 * color in this point
 *
 * @author David and Matan
 */
public class RayTracerBasic extends RayTracerBase {
    /**
     * Ctor - get scene and set it
     *
     * @param scene - body that build from geometries bodies and color and
     *              ambientLight(strong of the color)
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    public  Color traceRay(Ray ray) {
        List<Point> intersections = scene.geometries.findIntsersections(ray);
        if (intersections != null) {
            Point closestPoint = ray.findClosestPoint(intersections);
            return calcColor(closestPoint);
        }
        else
           return scene.background;

    }
    public Color calcColor(Point point) {
        return scene.ambientLight.getIntensity();
}
    }