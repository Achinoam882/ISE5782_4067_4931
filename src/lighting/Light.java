package lighting;
import primitives.Color;
import primitives.Color;

/**
 * The class represents all light sources at an abstract level
 *
 * @author Achinoam & Malka
 */
public  abstract class Light {
    private Color intensity;
    /**
     * A Ctor who gets the color and power (intensity) of light
     *
     * @param intensity - light intensity according to RGB
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }
    /**
     * get the ambient light source
     *
     * @return the intensity - ambient light source
     */
    public Color getIntensity() {
        return intensity;
    }
}
