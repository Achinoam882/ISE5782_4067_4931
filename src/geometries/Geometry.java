
/**  interface Geometry
 * All the geometries in the scene inherit from it and implement getNormal method
 * */
package geometries;
import primitives.*;

public interface Geometry extends Intersectable
{
   public Vector getNormal(Point p);



}
