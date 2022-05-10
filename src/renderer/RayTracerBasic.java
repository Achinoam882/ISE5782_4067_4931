package renderer;
import java.util.List;

import lighting.LightSource;
import primitives.*;

import static primitives.Util.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import javax.swing.*;

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
    private static final double DELTA = 0.1;

    /**
     * For shading test between point and light source
     *
     * @param l     - vector from light
     * @param n     - normal of body
     * @param gp    - point in geometry body
     * @return
     *         <li>true - if unshaded
     *         <li>false - if shaded
     */
    private boolean unshaded(GeoPoint gp, Vector l, Vector n, LightSource light,double nv)
    {
        Vector lightDirection =l.scale(-1);
        Vector epsVector=n.scale(nv<0?DELTA:-1*DELTA);
        Point point =gp.point.add(epsVector);
        Ray lightRay=new Ray(point,lightDirection);
        List<GeoPoint> intersections=scene.geometries.findGeoIntersections((lightRay));
        if( intersections==null)
            return true;
        double lightDistance = light.getDistance(gp.point);//calculates the Distance between light and point
        for (GeoPoint geoPoint : intersections) {
            //checks if the point is behind light
            if (geoPoint.point.distance(gp.point) <= lightDistance )
                return false;//the point should be shaded
        }
        return true;//no shade

    }

    /**
     * Implementation for the abstract method traceRay
     */
    @Override
    public  Color traceRay(Ray ray) {
        List<GeoPoint> intersectionsPoints = scene.geometries.findGeoIntersections(ray);
        if (intersectionsPoints != null) {
            GeoPoint closestPoint = ray.findClosestGeoPoint(intersectionsPoints);
            return calcColor(closestPoint,ray);
        }
        else
           return scene.background;


    }
    /**
     * Calculate the color of a certain point
     *
     * @param point
     * @return The color of the point (calculated with local effects)
     */
        public Color calcColor(GeoPoint point, Ray ray) {
            return scene.ambientLight.getIntensity().add(point.geometry.getEmission()).add(calcLocalEffects(point, ray));
        }
    /**
     * Calculate the effects of lights
     *
     * @param intersection
     * @param ray
     * @return The color resulted by local effecrs calculation
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return Color.BLACK;
        int nShininess = intersection.geometry.getMaterial().nShininess;

        Double3 kd = intersection.geometry.getMaterial().kD;
        Double3 ks = intersection.geometry.getMaterial().kS;
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) {// checks if nl == nv
                if (unshaded(intersection, l, n,lightSource,nv)) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }


            }

        }
        return color;
    }
    /**
     * Calculates diffusive light
     * @param kd
     * @param l
     * @param n
     * @param lightIntensity
     * @return The color of diffusive effects
     */
    private Color calcDiffusive(Double3 kd, Vector l, Vector n, Color lightIntensity) {
        double ln = alignZero(l.dotProduct(n));
        if (ln < 0)
            ln = ln * -1;
        return lightIntensity.scale(kd.scale(ln));
    }

    /**
     * Calculate specular light
     * @param ks
     * @param l
     * @param n
     * @param v
     * @param nShininess
     * @param lightIntensity
     * @return The color of specular reflection
     */
    private Color calcSpecular(Double3 ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        var r=l.add(n.scale(l.dotProduct(n)*-2));
        //Vector r = l.subtract(n.scale(l.dotProduct(n) * 2));
        double vr = alignZero(v.scale(-1).dotProduct(r));
        if (vr < 0)
            vr = 0;
        vr = Math.pow(vr, nShininess);
        return lightIntensity.scale(ks.scale(vr));
    }

}

