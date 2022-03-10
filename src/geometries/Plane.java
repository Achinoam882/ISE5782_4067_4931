/**
 * Plane class
 * Is represented by a point and normal
 * @author Achinoam & Malka
 */
package geometries;
import primitives.*;

public class Plane implements Geometry {
    final private Point _q0;
    final  private Vector _normal;

   /** public Plane(Point p1, Point p2, Point p3) {
       _q0 = p1;
        Vector U = p2.subtract(p1);
        Vector V = p3.subtract(p1);
        Vector N = U.crossProduct(V);
        N.normalize();
        //right hand rule
        _normal = N;*/
        /**
         * A ctor that gets 3 parameters(Point type).
         * @param p1 point p1
         * @param p2 point p2
         * @param p3 point p3
         */
    public Plane(Point p1, Point p2, Point p3){

            if (p1.equals(p2) || p2.equals(p3) || p3.equals(p1))
                throw new IllegalArgumentException("Two of the points are the same point");

            Vector v1 = p2.subtract(p1);
            Vector v2 = p3.subtract(p1);

            try{
                Vector cross = v1.crossProduct(v2);

               _q0 = p2;
                _normal = cross.normalize();
            }
            catch (Exception e){
                throw new IllegalArgumentException("The points are on the same line");
            }
        }



    public Plane(Point p0, Vector normal) {
        _q0 = p0;
        _normal = normal.normalize();
    }

    public Point get_q0() {
        return _q0;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "_q0=" + _q0 +
                ", _normal=" + _normal +
                '}';
    }

    //public Vector get_normal() {
        //return _normal;
    //}

    @Override
    public Vector getNormal(Point p)
    {
        return _normal;
    }
    public Vector getNormal() {return _normal;}
}
