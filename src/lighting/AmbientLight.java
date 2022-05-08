package lighting;
import primitives.Color;
import primitives.Double3;

/**
 * Ambient light for all objects in the scene
 */
public class AmbientLight extends Light {

    /**
     * A Ctor who gets the color and power of light
     *
     * @param Ia - Fill light intensity according to RGB
     * @param Ka - Coefficient of attenuation of filler light
     */
    public AmbientLight(Color Ia , Double3 Ka){
        super(Ia.scale(Ka));
    }

    /**
     * default constructor setting ambientLight to Black
     */
    public AmbientLight(){
        super(Color.BLACK);
    }


}

