package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * DirectionalLight
 */
public class DirectionalLight extends Light implements LightSource{
    private Vector direction;

    /**
     *
     * @param intensity
     * @param dir
     */
    public DirectionalLight(Color intensity, Vector dir) {
        super(intensity);
        direction = dir.normalize();
    }

    @Override
    public Color getIntensity(Point p){
        return getIntensity();
    }

    @Override
    public Vector getL(Point p){
        return direction;
    }

    @Override
    public double getDistance(Point point)
    {
        return Double.POSITIVE_INFINITY;
    }


}