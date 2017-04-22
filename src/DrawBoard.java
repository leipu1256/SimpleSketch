import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * Created by leipu on 2016-06-28.
 */
public class DrawBoard extends JPanel implements MouseListener, MouseMotionListener{
    public static int buttonPressed = 0;
    public static IShape currentShape = null;
    public static ArrayList IShapes = null;

    public DrawBoard(){
        IShapes = new ArrayList();
        addMouseListener(this);
        addMouseMotionListener(this);

        setDoubleBuffered(true);
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        this.setPreferredSize(new Dimension(700,600));
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setForeground(Color.BLACK);
        this.setBackground(Color.WHITE);
        setVisible(true);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        int i = IShapes.size();
        Graphics2D g2D = (Graphics2D) g;
        for(int j = 0; j < i; j++){
            ((IShape)IShapes.get(j)).draw(g2D);

        }
    }

    public void mousePressed(MouseEvent event){
        if(DrawBoard.buttonPressed == 0)
            return;
        else if(event.getButton() == MouseEvent.BUTTON1){
            switch (buttonPressed){
                case 1:
                    currentShape = new Line(getForeground(), event.getX(), event.getY());
                    break;
                case 2:
                    currentShape = new Rect(getForeground(), event.getX(), event.getY());
                    break;
                case 3:
                    currentShape = new Ellipse(getForeground(), event.getX(), event.getY());
                    break;
                case 4:
                    currentShape = new Pencil(getForeground(), event.getX(), event.getY());
                    break;
                case 5:
                    currentShape = new Polygon(getForeground(), event.getX(), event.getY());
                default:
                    break;
            }
            IShapes.add(currentShape);
            repaint();
        }
        else if(event.getButton() == MouseEvent.BUTTON3 && currentShape != null){
            currentShape.processCursorEvent(event, IShape.RIGHT_PRESSED);
            repaint();
        }
    }

    public void mouseDragged(MouseEvent event){
        if(currentShape == null){
            return;
        }
        else{
            currentShape.processCursorEvent(event, IShape.CURSOR_DRAGGED);
            repaint();
        }
    }

    public void mouseReleased(MouseEvent event){
        if(event.getButton() == MouseEvent.BUTTON1 && currentShape !=null){
            currentShape.processCursorEvent(event, IShape.LEFT_RELEASED);
            currentShape = null;
            repaint();
        }
    }

    public void mouseClicked(MouseEvent event){}
    public void mouseEntered(MouseEvent event){}
    public void mouseExited(MouseEvent event){}
    public void mouseMoved(MouseEvent event){}


}
