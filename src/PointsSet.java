import java.awt.*;
import java.util.Vector;
/**
 * Created by leipu on 2016-06-24.
 */
public class PointsSet {
    private Vector<Point> points;

    public PointsSet(){
        points = new Vector<Point>();
    }
    public PointsSet(Point[] p){
        points = new Vector<Point>();
        for(int i = 0; i < p.length; i++){
            points.add(p[i]);
        }
    }
    public void addPoint(Point p){
        points.add(p);
    }
    public  Vector<Point> getPoints(){
        return points;
    }

    public int[] getXPoints(){
        int[] xPoints = new int[points.size()];
        for(int i = 0; i < points.size(); i++){
            xPoints[i] = points.get(i).x;
        }
        return xPoints;
    }

    public int[] getYPoints(){
        int[] yPoints = new int[points.size()];
        for (int i = 0 ; i < points.size(); i++){
            yPoints[i] = points.get(i).y;
        }
        return yPoints;
    }

    public void remove(int index){
        points.remove(index);
    }

    public int size(){
        return points.size();
    }
}
