/**
 * class Vector contains a Point3D  and it represents the distance and direction of
 * that point from the point (0,0,0)
 * @author Achinoam and Malka
 */
package primitives;

public class Vector extends Point {
     Vector(Double3 xyz) {
        super(xyz);
        if(_xyz.equals(Double3.ZERO)){
            throw new IllegalArgumentException("Vector (0,0,0 not valid");
        }

    }
    /**
     * constructor for Vector class
     *
     * @param x value for X Coordinate of   Point
     * @param y value for Y Coordinate of  Point
     * @param z value for Z Coordinate of  Point
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if(_xyz.equals(Double3.ZERO)){
            throw new IllegalArgumentException("Vector (0,0,0 not valid");
        }
    }

//    @Override
//    public boolean equals(Object o) {
//        return super.equals(o);
//    }
//
//    @Override
//    public int hashCode() {
//        return super.hashCode();
//    }

    @Override
    public String toString() {
        return "Vector " + _xyz ;
    }

    /**
     * @return length using Pythagoras
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * @return euclidean length squared of the vector
     */
    public double lengthSquared() {
        double u1 = _xyz._d1;
        double u2 = _xyz._d2;
        double u3 = _xyz._d3;

        return u1 * u1 + u2 * u2 + u3 * u3;
    }

    /**
     *
     * dot product between two vectors (scalar product)
     *
     * @param vector the right vector of U.V
     * @return scalar value of dot product
     * @link https://chepusht.mathcs.wilkes.edu/DotVsCrossProducts.pdf
     */
    public double dotProduct(Vector vector) {
        double u1 = _xyz._d1;
        double u2 = _xyz._d2;
        double u3 = _xyz._d3;

        double v1 = vector._xyz._d1;
        double v2 = vector._xyz._d2;
        double v3 = vector._xyz._d3;

        return (u1 * v1 + u2 * v2 + u3 * v3);
    }

    /**
     *
     * @param vector the right vector of UxV
     * @return vector according to right hand rulr
     * @link https://chepusht.mathcs.wilkes.edu/DotVsCrossProducts.pdf
     */
    public Vector crossProduct(Vector vector) {
        double u1 = _xyz._d1;
        double u2 = _xyz._d2;
        double u3 = _xyz._d3;

        double v1 = vector._xyz._d1;
        double v2 = vector._xyz._d2;
        double v3 = vector._xyz._d3;

        return new Vector((u2*v3-v2*u3),-(u1*v3-v1*u3),(u1*v2-v1*u2));

    }

    /**
     *
     * @return vector with length = 1
     */
    public Vector normalize() {
        double len = length();

        return new Vector(_xyz.reduce(len));
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
    public Vector add(Double3 xyz)
    {
            return  new Vector(_xyz.add(xyz));
    }
    public Vector scale(double scalingFacor)
    {
        return new Vector(
                new Double3(
                        (scalingFacor * _xyz._d1),
                        (scalingFacor * _xyz._d2),
                       (scalingFacor * _xyz._d3)));
    }

}

