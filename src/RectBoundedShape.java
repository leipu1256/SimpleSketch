import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by leipu on 2016-06-24.
 */
public abstract class RectBoundedShape implements IShape{
    protected Color color;

    protected int startX, startY, endX, endY;
    protected RectBoundedShape(Color c, int x, int y){
        color = c;
        startX = endX = x;
        startY = endY = y;
    }



    public void processCursorEvent(MouseEvent e, int t) {
        if(t != IShape.CURSOR_DRAGGED)
            return;
        int x = e.getX();
        int y = e.getY();
        if(e.isShiftDown()){
            regulateShape(x,y);
        }
        else{
            endX = x;
            endY = y;
        }
    }

    protected void regulateShape(int x, int y){
        int w = x - startX;
        int h = y - startY;
        int s = Math.min(Math.abs(w),Math.abs(h));
        if(s == 0){
            endX = startX;
            endY = startY;
        }
        else{
            endX = startX + s * (w / Math.abs(w));
            endY = startY + s * (h / Math.abs(h));
        }
    }

}
