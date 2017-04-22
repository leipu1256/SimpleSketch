import java.awt.*;

/**
 * Created by leipu on 2016-07-05.
 */
public class Bounds {
    int left, right, top, bottom;

    void UpdateBounds(int x1, int y1, int x2, int y2)
    {
        left = (x1 < x2) ? x1 : x2;
        right = (x1 > x2) ? x1 : x2;
        top = (y1 < y2) ? y1 : y2;
        bottom = (y1 > y2) ? y1 : y2;
    }

    void UpdateBounds(Point pt1, Point pt2)
    {
        left = (pt1.x < pt2.x) ? pt1.x : pt2.x;
        right = (pt1.x > pt2.x) ? pt1.x : pt2.x;
        top = (pt1.y < pt2.y) ? pt1.y : pt2.y;
        bottom = (pt1.y > pt2.y) ? pt1.y : pt2.y;
    }

    int Width()
    {
        return Math.abs(left - right);
    }

    int Height()
    {
        return Math.abs(top - bottom);
    }

    boolean ContainsPoint(int x, int y)
    {
        if ((y < top) || (y > bottom))
            return false;
        if ((x < left) || (x > right))
            return false;
        return true;
    }
}
