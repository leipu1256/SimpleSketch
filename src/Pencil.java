import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * Created by leipu on 2016-06-24.
 */
public class Pencil extends FreeShape{

    protected Pencil(Color c, int x, int y) {
        super(c, x, y);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        Vector<Point> points = pointsSet.getPoints();
        int s = points.size();
        if(s == 1){
            g.drawLine(points.get(0).x, points.get(0).y,points.get(0).x, points.get(0).y);
        }else{
            g.drawPolyline(pointsSet.getXPoints(), pointsSet.getYPoints(), s);
        }
    }

    public void processCursorEvent(MouseEvent e, int t){
        if(t == IShape.CURSOR_DRAGGED){
            pointsSet.addPoint(new Point(e.getX(), e.getY()));
        }
    }


}
