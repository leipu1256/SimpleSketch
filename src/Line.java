import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by leipu on 2016-06-24.
 */
public class Line extends RectBoundedShape {


    protected Line(Color c, int x, int y) {
        super(c, x, y);
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawLine(startX, startY, endX, endY);
    }

    @Override
    public void processCursorEvent(MouseEvent e, int t) {
        if (t != IShape.CURSOR_DRAGGED)
            return;
        int x = e.getX();
        int y = e.getY();
        if (e.isShiftDown()) {
            if (x - startX == 0) {
                endX = startX;
                endY = y;
            } else {
                float slope = Math.abs((float) (y - startY) / (x - startX));
                if (slope < 0.577) {
                    endX = x;
                    endY = startY;
                } else if (slope < 1.155) {
                    regulateShape(x, y);
                } else {
                    endX = startX;
                    endY = y;
                }
            }
        } else {
            endX = x;
            endY = y;
        }
    }
}
