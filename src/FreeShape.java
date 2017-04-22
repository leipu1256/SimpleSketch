import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by leipu on 2016-06-23.
 */
public abstract class FreeShape implements IShape{
    protected Color color;
    protected PointsSet pointsSet;

    protected FreeShape(Color c, int x, int y){
        pointsSet = new PointsSet();
        color = c;
        pointsSet.addPoint(new Point(x, y));
    }

    public void processCursorEvent(MouseEvent e, int t){
        if(t != IShape.CURSOR_DRAGGED)
            return;
        pointsSet.addPoint(new Point(e.getX(), e.getY()));
    }
}
