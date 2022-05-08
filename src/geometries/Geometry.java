
/**  interface Geometry
 * All the geometries in the scene inherit from it and implement getNormal method
 * */
package geometries;
import primitives.*;

public abstract class Geometry extends Intersectable
{
   private  Material material=new Material();
   public abstract  Vector getNormal(Point p);
   protected Color emission=new Color(java.awt.Color.BLACK);
   /**
    * give the color of the body
    *
    * @return the color of the body
    */
   public Color getEmission() {
      return emission;
   }
   /**
    * ------ set the emission ------
    *
    * @param emission - get the new color to set
    * @return the Geometry itself
    */
   public Geometry setEmission(Color emission) {
      this.emission = emission;
      return this;
   }
   /**
    * --------------- getter -------------------
    *
    * @return the material class
    */
   public Material getMaterial() {
      return material;
   }
   /**
    * ------ set the material ------
    *
    * @param material - set material to geometry
    * @return itself geometry
    */
   public Geometry setMaterial(Material material) {
      this.material = material;
      return this;
   }
}
