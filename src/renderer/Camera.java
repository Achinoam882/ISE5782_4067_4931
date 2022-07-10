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
    private double focalDistance = 0.0;
    private double apertureRadius = 1.0;
    private static final int LEVEL = 4;
    private int amountRowPixels;
    private int amountColumnPixels;

    /**
     * Location of the camera lens
     *
     * @return the p0 a location of the camera lens
     */
    public Camera setPixels(int amountRowPixels, int amountColumnPixels) {
        this.amountRowPixels = amountRowPixels;
        this.amountColumnPixels = amountColumnPixels;
        return this;
    }
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
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }
    public double getwidth() {
        return width;
    }
    public double getheight() {
        return height;
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
    public Ray buildRay(Point p){
        return new Ray(p0, p.subtract(p0));
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
     * Calculates list of rays through each of the pixels on the view plane
     *
     * @param nX number of pixels in X axis
     * @param nY number of pixels in Y axis
     * @param j  the column index (With i define a specific pixel)
     * @param i  the row index (With j define a specific pixel)
     * @return list of rays object which is the ray through a specific index
     */
    public List<Ray> constructRays(int nX, int nY, int j, int i) {
        if (amountColumnPixels <= 0 || amountRowPixels <= 0) {
            return List.of(constructRay(nX, nY, j, i));
        }
        Point Pc = p0.add(vTo.scale(distance));
        List<Ray> rays = new LinkedList<>();
//ratio
        double Ry = height / nY;
        double Rx = width / nX;
        double Yi = -(i - (nY - 1) / 2d) * Ry;
        double Xj = (j - (nX - 1) / 2d) * Rx;
//Pixel[i,j]center:
        Point Pij = Pc;
        if ((Yi)!=0) {
            Pij = Pij.add(vUp.scale(Yi));
        }
        if ((Xj)!=0) {
            Pij = Pij.add(vRight.scale(Xj));
        }
        Ry = Ry / amountColumnPixels;
        Rx = Rx / amountRowPixels;
        for (int k = 0; k < amountRowPixels; k++) {
            for (int l = 0; l < amountColumnPixels; l++) {

                Point point = Pij;
                double Yii = -(k -
                        (amountColumnPixels - 1) / 2d) *
                        Ry;
                double Xjj = -(l -
                        (amountRowPixels - 1) / 2d) * Rx;
                if ((Yii)!=0) {
                    point = point.add(vUp.scale(Yii
                    ));
                }
                if ((Xjj)!=0) {
                    point = point.add(vRight.scale(
                            Xjj));
                }
                rays.add(new Ray(p0, point.subtract(p0)));
            }
        }
        return rays;
    }
    /**
     * Image writer setter
     *
     * @param imgWriter the image writer to set
     * @return camera itself
     */
    public Camera setImageWriter(ImageWriter imgWriter) {
        this.imageWriter = imgWriter;
        return this;
    }

    /**
     * Ray tracer setter
     *
     * @param tracer to use
     * @return camera itself
     */
    public Camera setRayTracer(RayTracerBase tracer) {
        this.tracer = tracer;
        return this;
    }







    /**
     * This function renders image's pixel color map from the scene included with
     * the Renderer object
     */
    public Camera renderImage() {
        if (imageWriter == null)
            throw new MissingResourceException(RESOURCE_ERROR, RENDER_CLASS, IMAGE_WRITER_COMPONENT);

        if (tracer == null)
            throw new MissingResourceException(RESOURCE_ERROR, RENDER_CLASS, RAY_TRACER_COMPONENT);
        //rendering the image
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                        castRay(nX, nY, j, i);



            }
        }
        return this;
    }
    /**
     * This function renders image's pixel color map from the scene included with
     * the Renderer object - with multi-threading
     */
    public void renderImageWithTreads() {
        // In case that not all of the fields are filled
        if (imageWriter == null || tracer == null)
            throw new MissingResourceException("Missing", "resource", "exception");

        // The nested loop finds and creates a ray for each pixels, finds its color and
        // writes it to the image pixles
        int nY = this.imageWriter.getNy();
        int nX = this.imageWriter.getNx();

        double printInterval = 0.01;
        int threadsCount = 3;
        Pixel.initialize(nY, nX, printInterval);
        while (threadsCount-- > 0) {
            new Thread(() -> {
                for (Pixel pixel = new Pixel(); pixel.nextPixel(); Pixel.pixelDone())
                    castRay(nX, nY, pixel.col, pixel.row);
            }).start();
        }
        Pixel.waitToFinish();
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
    /**
     * set Aperture Radius to affect on depth of field
     * (builder design pattern)
     *
     * @param apertureRadius
     * @return this camera object
     */
    public Camera setApertureRadius(double apertureRadius) {
        this.apertureRadius = apertureRadius;
        return this;
    }


    /**
     * set distance of Focal plane from View Plan
     * (builder design pattern)
     *
     * @param focalDistance
     * @return this camera object
     */
    public Camera setFpDistance(double focalDistance) {
        this.focalDistance = focalDistance;
        return this;
    }

    /**
     * get distance of Focal plan from View Plan
     *
     * @return distance from view plan
     */
    public double getFpDistance() {
        return focalDistance;
    }

    /**
     * get radius of Aperture Radius
     *
     * @return radius
     */
    public double getApertureRadius() {
        return apertureRadius;
    }

    public Point calcPij(double width,double height,int nX, int nY, int j, int i,Point pCenter) {
        double Ry = height / nY;
        double Rx = width / nX;
        double yi = -(double)(i - ((nY - 1) /(double)2)) * Ry;
        double xj = (double)(j - (nX - 1) /(double)2) * Rx;
        Point pIJ = pCenter;
        if (xj != 0) pIJ = pIJ.add(getvRight().scale(xj));
        if (yi != 0) pIJ = pIJ.add(getvUp().scale(yi));
        return pIJ;
    }
    public Point getPCenter() { return p0.add(vTo.scale(distance));}
    /**
     * Cast ray from camera in order to color a pixel
     * @param nX resolution on X axis (number of pixels in row)
     * @param nY resolution on Y axis (number of pixels in column)
     * @param j pixel's column number (pixel index in row)
            * @param i pixel's row number (pixel index in column)
     * */
    private void castRay(int nX, int nY, int i, int j) {
        List<Ray> rays = constructRays(nX, nY, i, j);
        Color pixelColor = tracer.traceRays(rays);

        imageWriter.writePixel(i, j, pixelColor);
    }
}

