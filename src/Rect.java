import java.awt.*;

/**
 * Created by leipu on 2016-06-24.
 */
public class Rect extends RectBoundedShape {

    protected Rect(Color c, int x, int y) {

        super(c, x, y);

    }

    @Override
    public void draw(Graphics2D g) {
        g.drawRect(startX, startY, endX - startX, endY - startY);
    }


}
