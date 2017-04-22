/**
 * Created by leipu on 2016-06-23.
 */
public interface IShape {
    public void draw(java.awt.Graphics2D g);
    public void processCursorEvent(java.awt.event.MouseEvent evt, int type);
    public static final int RIGHT_PRESSED = 1;
    public static final int LEFT_RELEASED = 2;
    public static final int CURSOR_DRAGGED = 3;


}
