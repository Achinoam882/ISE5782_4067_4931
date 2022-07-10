package renderer;
import primitives.*;
import java.util.List;
import java.util.LinkedList;

/**
 * abstract Class for create BlackBoard of points
 *
 */
public abstract class BlackBoard {

    /**
     * constructor list of points to create circle black board
     * by get size (x/y), 3Dpoint and direction vectors, and radius of circle
     *
     * calc by GRID of x/y and ignore points outside the given radius
     *
     * the given center Point always return in the list.
     *
     *  because circle is smaller than rectangle, you get less points than x*y in approximately 27%
     *
     *  xy number of points in x and y. NEED TO BE over than 1 (1 return only the given center point)
     *  center point to create points around
     *  vUp vector
     *  vRight vector
     *  radius of circle black board (radius 0.0 return only the center point)
     *  list of all points
     */
    public static List<Point> constructCircleBlackBorad(int xy, Point center, Vector vUp, Vector vRight, double radius){

        if (xy < 1)
            throw new IllegalArgumentException("xy must be over than 0");

        if ((xy == 1) || (radius == 0.0))
            return List.of(center);


        List<Point> pointList = new LinkedList<>();
        pointList.add(center);

        double rectSize = 2*radius;
        double radius2 = radius*radius;

        double rxy = Util.alignZero(rectSize/xy);

        for (int i = 0; i < xy; i++) {
            for (int j = 0; j < xy; j++) {
                double yI = Util.alignZero((-(i-(xy-1)/2.0))*rxy);
                double xJ = Util.alignZero((j-(xy-1)/2.0)*rxy);

                Point pIJ = center;

                if (xJ != 0) pIJ = pIJ.add(vRight.scale(xJ));
                if (yI != 0) pIJ = pIJ.add(vUp.scale(yI));

                double dist = Util.alignZero(pIJ.distanceSquared(center));

                if ((dist  <= radius2 ) && (dist > 0))
                    pointList.add(pIJ);
            }
        }

        return pointList;
    }

}

