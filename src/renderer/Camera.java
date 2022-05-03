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
import java.util.MissingResourceException;

import static primitives.Util.alignZero;

public class Camera {
    private static final String RESOURCE_ERROR = "Renderer resource not set";
    private static final String RENDER_CLASS = "Camera";
    private static final String IMAGE_WRITER_COMPONENT = "Image writer";
    private static final String RAY_TRACER_COMPONENT = "Ray tracer";
    private Point p0;
    private Vector vUp;
    private Vector vTo;
    private Vector vRight;
    private double width;
    private double height;
    private double distance;
    private ImageWriter imageWriter;
    private RayTracerBase tracer;

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
     *
     * @param nX number of pixels in X axis
     * @param nY number of pixels in Y axis
     * @param j  the column index (With i define a specific pixel)
     * @param i  the row index (With j define a specific pixel)
     * @return new Ray object which is the ray through a specific index
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
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

    /**
     * Image writer setter
     *
     * @param imgWriter the image writer to set
     * @return camera itself - for chaining
     */
    public Camera setImageWriter(ImageWriter imgWriter) {
        this.imageWriter = imgWriter;
        return this;
    }

    /**
     * Ray tracer setter
     *
     * @param tracer to use
     * @return camera itself - for chaining
     */
    public Camera setRayTracer(RayTracerBase tracer) {
        this.tracer = tracer;
        return this;
    }
    /**
     * Cast ray from camera in order to color a pixel
     * @param nX resolution on X axis (number of pixels in row)
     * @param nY resolution on Y axis (number of pixels in column)
     * @param col pixel's column number (pixel index in row)
     * @param row pixel's row number (pixel index in column)
     */
    private Color castRay(int nX, int nY, int col, int row) {

        Ray ray = constructRay(nX, nY, col, row);
        Color pixelColor = tracer.traceRay(ray);
        return pixelColor;

    }

    /**
     * This function renders image's pixel color map from the scene included with
     * the Renderer object
     */
    public void renderImage() {
        if (imageWriter == null)
            throw new MissingResourceException(RESOURCE_ERROR, RENDER_CLASS, IMAGE_WRITER_COMPONENT);

        if (tracer == null)
            throw new MissingResourceException(RESOURCE_ERROR, RENDER_CLASS, RAY_TRACER_COMPONENT);
        //rendering the image
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                Color pixelColor=castRay(nX, nY, j, i);
                imageWriter.writePixel(j, i, pixelColor);


            }
        }
    }
            /**
             * Create a grid [over the picture] in the pixel color map. given the grid's
             * step and color.
             *
             * @param step  grid's step
             * @param color grid's color
             */
            public void printGrid ( int step, Color color){
                if (imageWriter == null)
                    throw new MissingResourceException(RESOURCE_ERROR, RENDER_CLASS, IMAGE_WRITER_COMPONENT);

                int nX = imageWriter.getNx();
                int nY = imageWriter.getNy();

                for (int i = 0; i < nY; ++i)
                    for (int j = 0; j < nX; ++j)
                        if (j % step == 0 || i % step == 0)
                            imageWriter.writePixel(j, i, color);
            }


            /**
             * Produce a rendered image file
             */
            public void writeToImage () {
                if (imageWriter == null)
                    throw new MissingResourceException(RESOURCE_ERROR, RENDER_CLASS, IMAGE_WRITER_COMPONENT);

                imageWriter.writeToImage();
            }
        }

