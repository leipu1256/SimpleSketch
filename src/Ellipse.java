import java.awt.*;

/**
 * Created by leipu on 2016-06-24.
 */
public class Ellipse extends RectBoundedShape {


    protected Ellipse(Color c, int x, int y) {
        super(c, x, y);
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawOval(startX, startY, endX - startX, endY - startY);
    }
}
