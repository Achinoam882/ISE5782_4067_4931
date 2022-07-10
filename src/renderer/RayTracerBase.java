package renderer;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

/**
 * An abstract base class responsible for tracking the ray
 *
 * @author Achinoam & Malka
 */
public abstract class RayTracerBase {
    protected Scene scene;
    /**
     * Ctor - get scene and set it
     *
     * @param scene - body that build from geometries bodies and color and
     *              ambientLight(strong of the color)
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }
    /**
     * returns color of pixel in current tracing ray
     *
     * @param rays - ray on tracing
     * @return the color of pixel in current tracing ray
     */
   /* public abstract Color traceRay(Ray ray);*/
    public abstract Color traceRays(List<Ray> rays);

    public abstract Color traceRay(Ray ray);
}
