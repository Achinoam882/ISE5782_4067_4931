package renderer;
import primitives.*;
import java.util.LinkedList;
/**
 * Shoot rays from the center of projection through the view plane pixels for
 * "see" objects in the this 3D world
 *
 * @author Malka & Achi
 *
 */
import java.util.List;

import static primitives.Util.alignZero;

public class Camera {
    private Point p0;
    private Vector vUp;
    private Vector vTo;
    private Vector vRight;
    private double width;
    private double height;
    private double distance;

    /**
     * Location of the camera lens
     *
     * @return the p0 a location of the camera lens
     */
    public Point getP0() {
        return p0;
    }

    /**
     * The Vector starting at P0 and pointing upwards
     *
     * @return the vUp
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     * The Vector starting at P0 and pointing forward
     *
     * @return the vTo
     */
    public Vector getvTo() {
        return vTo;
    }

    /**
     * The Vector starting at P0 and pointing to the right
     *
     * @return the vRight
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     * A camera constructor that receives two vectors in the direction of the
     * camera(up,to) and point3d for the camera lens
     *
     * @param p0  - location of the camera lens
     * @param vTo - starting at P0 and pointing forward
     * @param vUp -starting at P0 and pointing upwards
     */
    public Camera(Point p0, Vector vTo, Vector vUp) {
        if (!Util.isZero(vUp.dotProduct(vTo)))
            throw new IllegalArgumentException("Vectors are not vertical");
        this.p0 = p0;
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
        this.vRight = vTo.crossProduct(vUp).normalize();
    }

    /**
     * setter for size of view plane
     *
     * @param width  - a width of plane view
     * @param height - a height of plane view
     * @return the camera himself
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * setter for distance from camera to view plane
     *
     * @param distance - a distance from camera to view plane
     * @return the camera himself
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }
    /**
     * Calculates a ray through each of the pixels on the view plane
     * @param nX number of pixels in X axis
     * @param nY number of pixels in Y axis
     * @param j the column index (With i define a specific pixel)
     * @param i the row index (With j define a specific pixel)
     * @return new Ray object which is the ray through a specific index
     */
    public Ray constructRay(int nX, int nY, int j, int i)
    {
        // Image center:
        Point pC = p0.add(vTo.scale(this.distance));

        // Ratio:
        double Ry = height / nY;
        double Rx = width / nX;

        // Pixel[i,j] center
        double yi = alignZero(-(i - (nY - 1) / 2.0) * Ry);
        double xj = alignZero((j - (nX - 1) / 2.0) * Rx);

        Point pIJ = pC;

        // To avoid a zero vector exception
        if (xj != 0)
            pIJ = pIJ.add(vRight.scale(xj));
        if (yi != 0)
            pIJ = pIJ.add(vUp.scale(yi));

        Vector vIJ = pIJ.subtract(this.p0);

        return new Ray(p0, vIJ);

    }

}