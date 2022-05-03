package lighting;
import primitives.Color;
import primitives.Double3;

/**
 * Ambient light for all objects in the scene
 */
public class AmbientLight {
    private final Color intensity;  // light intensity as a Color

    /**
     * primary constructor
     * @param Ia basic illumination
     * @param Ka attenuation factor
     */
    public AmbientLight(Color Ia , Double3 Ka){
        intensity = Ia.scale(Ka);
    }

    /**
     * default constructor setting ambientLight to Black
     */
    public AmbientLight(){
        intensity= Color.BLACK;
    }

    /**
     * getter for intensity
     * @return the actual intensity
     */
    public Color getIntensity() {
        return intensity;
    }
}

