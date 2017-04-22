import java.awt.*;
import java.awt.event.MouseEvent;


/**
 * Created by leipu on 2016-06-29.
 */
public class Polygon implements IShape {

    private Color color;
    private PointsSet pointsSet;
    private boolean finalized;
    private int currX, currY;


    public Polygon(Color c, int x, int y) {
        color = c;
        currX = x;
        currY = y;
        pointsSet = new PointsSet();
        pointsSet.addPoint(new Point(x, y));
    }


    @Override
    public void processCursorEvent(MouseEvent evt, int t) {
        currX = evt.getX();
        currY = evt.getY();
        if (t == IShape.RIGHT_PRESSED) {
            pointsSet.addPoint(new Point(currX, currY));
        } else if (t == IShape.LEFT_RELEASED) {
            finalized = true;
        }
    }



    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);

        if (finalized) {

            if (pointsSet.size() == 1) {
                Point p = pointsSet.getPoints().get(0);
                g.drawLine(p.x, p.y, p.x, p.y);
            } else {
                g.drawPolygon(pointsSet.getXPoints(), pointsSet.getYPoints(), pointsSet.size());
            }
        } else {
            pointsSet.addPoint(new Point(currX, currY));
            g.drawPolyline(pointsSet.getXPoints(), pointsSet.getYPoints(), pointsSet.size());
            pointsSet.remove(pointsSet.size() - 1);
        }

//    }
//public void draw(Graphics2D g2D){
//
//    g2D.setColor(color);
//
//    Vector<Point> point = pointsSet.getPoints();
//
//    int size = pointsSet.size();
//
//
//    if(true){
////        if(size>=2){
////
////            if(Math.abs(point[0][size-1]- point[0][0]) <= 1 &&
////                    Math.abs(point[1][size-1]- point[1][0]) <= 1)
////            {
////                g2D.drawPolygon(point[0], point[1], size);
////                DrawBoard.currentShape = null;
////                return;
////
////            }
////
////            if(Math.abs(point[0][size-1]-point[0][size-2]) <= 1 &&
////                    Math.abs(point[1][size-1]-point[1][size-2]) <= 1){
////
////                g2D.drawPolygon(point[0], point[1], size);
////                DrawBoard.currentShape = null;
////                return;
////            }
////        }
//
//        g2D.drawLine(currX, currY, endX, endY);
//        g2D.drawPolyline(pointsSet.getXPoints(), pointsSet.getYPoints(), size);
//    }
//
//}


    }
}
