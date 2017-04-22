import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by leipu on 2016-06-18.
 */


public class GUI extends JFrame{
    private static GUI gui = new GUI();

    private JMenuBar jmb;
    private JMenu menu1;// file
    private JMenu menu2;// edit
    private JMenu menu3;// help
    private JMenuItem item1;// open
    private JMenuItem item2;// save
    private JMenuItem item3;// exit
    private JMenuItem item4;// undo
    private JMenuItem item5;// redo
    private JMenuItem item6;// group
    private JMenuItem item7;// ungroup
    private JMenuItem item8;// copy
    private JMenuItem item9;// paste
    private JMenuItem item10;// about

    private JButton foreColor;
    private JButton backColor;
    private JToolBar jtb;
    private ButtonGroup bg;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private ArrayList shapes = new ArrayList();
    private DrawBoard drawBoard;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private GUI(){
        super();

        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        jmb = new JMenuBar();
        menu1 = new JMenu("File");
        item1 = new JMenuItem("Open");
        item2 = new JMenuItem("Save");
        item3 = new JMenuItem("Exit");

        menu2 = new JMenu("Edit");
        item4 = new JMenuItem("Undo");
        item5 = new JMenuItem("Redo");
        item6 = new JMenuItem("Group");
        item7 = new JMenuItem("Ungroup");
        item8 = new JMenuItem("Copy");
        item9 = new JMenuItem("Paste");

        menu3 = new JMenu("Help");
        item10 = new JMenuItem("About");

        drawBoard = new DrawBoard();
        jtb = new JToolBar();


        Initialize();
    }

    private void Initialize(){
        Container container = getContentPane();
        container.add(jtb, BorderLayout.WEST);
        container.add(new JScrollPane(drawBoard),BorderLayout.CENTER);
        this.setMinimumSize(new Dimension(650, 600));
        this.setLocation(200, 100);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Sketch Pad");
        this.setJMenuBar(jmb);
        this.setBackground(Color.gray);

        int menuMask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        jmb.add(menu1);
        menu1.add(item1);
        menu1.add(item2);
        menu1.addSeparator();
        menu1.add(item3);

        item1.addActionListener(e -> openFile());

        item2.addActionListener(e -> saveFile());

        item3.addActionListener(e -> System.exit(0));
        jmb.add(menu2);
        menu2.add(item4);
        menu2.add(item5);
        menu2.add(item6);
        menu2.add(item7);
        menu2.add(item8);
        menu2.add(item9);

        item8.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, menuMask));
        item8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        item9.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, menuMask));
        item9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        jmb.add(menu3);
        menu3.add(item10);

        JPanel toolPanel = new JPanel();
        toolPanel.setBorder(new javax.swing.border.TitledBorder("Drawing Tools"));
        toolPanel.setLayout(new GridLayout(4,2,5,5));
        toolPanel.setMaximumSize(new Dimension(185, 135));
        buttonsActionHandle handle = new buttonsActionHandle();

        button1 = new JButton("Line");
        button1.addActionListener(handle);
        button2 = new JButton("Rect");
        button2.addActionListener(handle);
        button3 = new JButton("Ellipse");
        button3.addActionListener(handle);
        button4 = new JButton("Pencil");
        button4.addActionListener(handle);
        button5 = new JButton("Polygon");
        button5.addActionListener(handle);


        bg = new ButtonGroup();
        bg.add(button1);
        bg.add(button2);
        bg.add(button3);
        bg.add(button4);
        bg.add(button5);


        toolPanel.add(button1);
        toolPanel.add(button2);
        toolPanel.add(button3);
        toolPanel.add(button4);
        toolPanel.add(button5);


        JPanel colorPanel = new JPanel();
        colorPanel.setBorder(new javax.swing.border.TitledBorder(("Color Settings")));
        foreColor = new JButton();
        foreColor.setPreferredSize(new Dimension(50,50));
        foreColor.setBorder(new javax.swing.border.LineBorder(new Color(0,0,0)));
        foreColor.setBackground(Color.BLACK);
        foreColor.addActionListener(
                e -> {
                    Color color = new Color(0, 0, 0);
                    color = JColorChooser.showDialog(GUI.this,"choose color",color);
                    if(color != null)
                        foreColor.setBackground(color);
                    drawBoard.setForeground(foreColor.getBackground());

                }
        );

        backColor = new JButton();
        backColor.setPreferredSize(new Dimension(50,50));
        backColor.setBorder(new javax.swing.border.LineBorder(new Color(0,0,0)));
        backColor.setBackground(Color.WHITE);
        backColor.addActionListener(
                e -> {
                    Color color = new Color(0, 0, 0);
                    color = JColorChooser.showDialog(GUI.this,"choose color",color);
                    if(color != null)
                        backColor.setBackground(color);
                    drawBoard.setBackground(backColor.getBackground());

                     }
        );
        colorPanel.add(foreColor);
        colorPanel.add(backColor);
        colorPanel.setMaximumSize(new Dimension(160, 100));


        jtb.add(toolPanel);
        jtb.add(colorPanel);
        jtb.setOrientation(JToolBar.VERTICAL);
        this.setVisible(true);

    }

    private void openFile(){
        JFileChooser fileChooser = new JFileChooser("/Users/leipu/Pictures");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(this);

        if(result == JFileChooser.CANCEL_OPTION)
            return;
        File fileName = fileChooser.getSelectedFile();

        if(fileName == null || fileName.getName().equals("")){
            JOptionPane.showMessageDialog(fileChooser,"Invalid File Name","Invalid File Name", JOptionPane.ERROR_MESSAGE);
            openFile();
        }
        if(!fileName.canRead()){
            JOptionPane.showMessageDialog(this, "The file can't be read or not exit", "ErrorRend", JOptionPane.ERROR_MESSAGE);
            openFile();
        }
        try{
            FileInputStream fis=new FileInputStream(fileName);
            input = new ObjectInputStream(fis);
            DrawBoard.IShapes = (ArrayList)input.readObject();
            repaint();
        }
        catch (IOException ioException){
            JOptionPane.showMessageDialog(this,"error during read from file",
                    "read Error",JOptionPane.ERROR_MESSAGE );
        }
        catch(ClassNotFoundException classNotFoundException){
            JOptionPane.showMessageDialog(this,"Unable to Create Object",
                    "end of file",JOptionPane.ERROR_MESSAGE );
        }
    }

    private void saveFile(){

        JFileChooser fileChooser = new JFileChooser("/Users/leipu/Pictures");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showSaveDialog(this);
        if(result == JFileChooser.CANCEL_OPTION)
            return;
        File fileName = fileChooser.getSelectedFile();

        fileName.canWrite();
        if (fileName==null||fileName.getName().equals(""))

            JOptionPane.showMessageDialog(fileChooser,"Invalid File Name",
                    "Invalid File Name", JOptionPane.ERROR_MESSAGE);

        else{

            try{
                fileName.delete();
                FileOutputStream fos = new FileOutputStream(fileName);
                output = new ObjectOutputStream(fos);
                output.writeObject(DrawBoard.IShapes);
                output.close();
                fos.close();

            }

            catch(IOException ioe)
            {
                ioe.printStackTrace();
            }
            return;
        }

    }
    private class buttonsActionHandle implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == button1){
                DrawBoard.buttonPressed = 1;
            }
            if(e.getSource() == button2){
                DrawBoard.buttonPressed = 2;
            }
            if(e.getSource() == button3){
                DrawBoard.buttonPressed = 3;
            }
            if(e.getSource() == button4){
                DrawBoard.buttonPressed = 4;
            }
            if(e.getSource() == button5){
                DrawBoard.buttonPressed = 5;
            }


        }
    }


    public static void main(String[] args) throws Exception{
        GUI gui = new GUI();
    }


}
