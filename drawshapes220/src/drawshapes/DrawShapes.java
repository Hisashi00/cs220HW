package drawshapes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class DrawShapes extends JFrame
{
    public enum ShapeType {
        SQUARE,
        CIRCLE,
        RECTANGLE
    }
    
    private DrawShapesPanel shapePanel;
    private Scene scene;
    private ShapeType shapeType = ShapeType.SQUARE;
    private Color color = Color.RED;
    private Point startDrag;
    private Stack<Scene> undoStack = new Stack<>();
    private int distance = 30;


    public DrawShapes(int width, int height)
    {
        setTitle("Draw Shapes!");
        scene=new Scene();
        undoStack.push(scene.copy());
        
        // create our canvas, add to this frame's content pane
        shapePanel = new DrawShapesPanel(width,height,scene);
        this.getContentPane().add(shapePanel, BorderLayout.CENTER);
        this.setResizable(false);
        this.pack();
        this.setLocation(100,100);
        
        // Add key and mouse listeners to our canvas
        initializeMouseListener();
        initializeKeyListener();
        
        // initialize the menu options
        initializeMenu();

        // Handle closing the window.
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void push(){
        undoStack.push(scene.copy());
    }
    
    private void initializeMouseListener()
    {
        MouseAdapter a = new MouseAdapter() {
            
            public void mouseClicked(MouseEvent e)
            {
                System.out.printf("Mouse cliked at (%d, %d)\n", e.getX(), e.getY());
                
                if (e.getButton()==MouseEvent.BUTTON1) { 
                    if (shapeType == ShapeType.SQUARE) {
                        push();
                        scene.addShape(new Square(color, 
                                e.getX(), 
                                e.getY(),
                                100));
                    } else if (shapeType == ShapeType.CIRCLE){
                        push();
                        scene.addShape(new Circle(color,
                                e.getPoint(),
                                100));
                    } else if (shapeType == ShapeType.RECTANGLE) {
                        push();
                        scene.addShape(new Rectangle(
                                e.getPoint(),
                                100, 
                                200,
                                color));
                    }
                    
                } else if (e.getButton()==MouseEvent.BUTTON2) {
                    // apparently this is middle click
                } else if (e.getButton()==MouseEvent.BUTTON3){
                    // right right-click
                    Point p = e.getPoint();
                    System.out.printf("Right click is (%d, %d)\n", p.x, p.y);
                    List<IShape> selected = scene.select(p);
                    if (selected.size() > 0){
                        for (IShape s : selected){
                            s.setSelected(true);
                        }
                    } else {
                        for (IShape s : scene){
                            s.setSelected(false);
                        }
                    }
                    System.out.printf("Select %d shapes\n", selected.size());
                }
                repaint();
            }
            
            /* (non-Javadoc)
             * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
             */
            public void mousePressed(MouseEvent e)
            {
                System.out.printf("mouse pressed at (%d, %d)\n", e.getX(), e.getY());
                scene.startDrag(e.getPoint());
                
            }

            /* (non-Javadoc)
             * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
             */
            public void mouseReleased(MouseEvent e)
            {
                System.out.printf("mouse released at (%d, %d)\n", e.getX(), e.getY());
                scene.stopDrag();
                repaint();
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                System.out.printf("mouse drag! (%d, %d)\n", e.getX(), e.getY());
                scene.updateSelectRect(e.getPoint());
                repaint();
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                // TODO use this to grow/shrink shapes
            }
            
        };
        shapePanel.addMouseMotionListener(a);
        shapePanel.addMouseListener(a);
    }
    
    /**
     * Initialize the menu options
     */
    private void initializeMenu()
    {
        // menu bar
        JMenuBar menuBar = new JMenuBar();
        
        // file menu
        JMenu fileMenu=new JMenu("File");
        menuBar.add(fileMenu);
        // load
        JMenuItem loadItem = new JMenuItem("Load");
        fileMenu.add(loadItem);
        loadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                System.out.println(e.getActionCommand());
                JFileChooser jfc = new JFileChooser(".");

                int returnValue = jfc.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    System.out.println("load from " +selectedFile.getAbsolutePath());
                    try {
                        push();
                        scene.loadFromFile(selectedFile);
                        repaint(); 

                    } catch(Exception ex){
                        JOptionPane.showMessageDialog(null, ex);
                    }

                    
                } 


            }
        });
        // save
        JMenuItem saveItem = new JMenuItem("Save");
        fileMenu.add(saveItem);
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                System.out.println(e.getActionCommand());
                JFileChooser jfc = new JFileChooser(".");

                // int returnValue = jfc.showOpenDialog(null);
                int returnValue = jfc.showSaveDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();

                    String sceneToString = scene.toString();
                    try (PrintWriter out = new PrintWriter(selectedFile)){
                        out.write(sceneToString);

                    } catch (Exception ex){
                        JOptionPane.showMessageDialog(null,ex ); 
                    }
                }
            }
        });
        fileMenu.addSeparator();
        // edit
        JMenuItem itemExit = new JMenuItem ("Exit");
        fileMenu.add(itemExit);
        itemExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text=e.getActionCommand();
                System.out.println(text);
                System.exit(0);
            }
        });

        // color menu
        JMenu colorMenu = new JMenu("Color");
        menuBar.add(colorMenu);

        // red color
        JMenuItem redColorItem= new JMenuItem ("Red");
        colorMenu.add(redColorItem);
        redColorItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text=e.getActionCommand();
                System.out.println(text);
                // change the color instance variable to red
                color = Color.RED;
            }
        });
        
        
        // blue color
        JMenuItem blueColorItem = new JMenuItem ("Blue");
        colorMenu.add(blueColorItem);
        blueColorItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text=e.getActionCommand();
                System.out.println(text);
                // change the color instance variable to blue
                color = Color.BLUE;
            }
        });
        // yellow
        JMenuItem yellowItem = new JMenuItem("Yellow");
        colorMenu.add(yellowItem);
        yellowItem.addActionListener(e -> {
            System.out.println("Yellow");
            color = Color.YELLOW;
        });

        // green
        JMenuItem greenItem = new JMenuItem("Green");
        colorMenu.add(greenItem);
        greenItem.addActionListener(e -> {
            System.out.println("Green");
            color = Color.GREEN;
        });

        // magenta
        JMenuItem magentaItem = new JMenuItem("Magenta");
        colorMenu.add(magentaItem);
        magentaItem.addActionListener(e -> {
            System.out.println("Magenta");
            color = Color.MAGENTA;
        });


        
        // shape menu
        JMenu shapeMenu = new JMenu("Shape");
        menuBar.add(shapeMenu);
        
        // square
        JMenuItem squareItem = new JMenuItem("Square");
        shapeMenu.add(squareItem);
        squareItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Square");
                shapeType = ShapeType.SQUARE;
            }
        });
        
        // circle
        JMenuItem circleItem = new JMenuItem("Circle");
        shapeMenu.add(circleItem);
        circleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Circle");
                shapeType = ShapeType.CIRCLE;
            }

        });
        // rectangle
        JMenuItem rectangleItem = new JMenuItem("Rectangle");
        shapeMenu.add(rectangleItem);
        rectangleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Rectangle");
                shapeType = ShapeType.RECTANGLE;
            }
        });
        
        
        // operation mode menu
        JMenu operationModeMenu=new JMenu("Operation");
        menuBar.add(operationModeMenu);
        
        // draw option
        JMenuItem drawItem=new JMenuItem("Resize");
        operationModeMenu.add(drawItem);
        drawItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text=e.getActionCommand();
                System.out.println(text);
            }

        
        });

        JMenuItem randomizeItem = new JMenuItem("Randomize Positions");
        operationModeMenu.add(randomizeItem);
        randomizeItem.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            push(); 
            scene.randomizePositions(700, 600); 
        repaint();
    }
});
JMenuItem sortSizeItem = new JMenuItem("Sort by Size");
operationModeMenu.add(sortSizeItem);
sortSizeItem.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        push();
        scene.sortBySizeAndArrangeInGrid(700, 20, 20);
        repaint();
    }
});


        
        // select option
        /*
        JMenuItem selectItem=new JMenuItem("Move");
        operationModeMenu.add(selectItem);
        selectItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text=e.getActionCommand();
                System.out.println(text);
                if (text=="a") { 
                    scene.s
            }


        }})
        */
        ;
        

        // set the menu bar for this frame
        this.setJMenuBar(menuBar);
    }
    
    /**
     * Initialize the keyboard listener.
     */
    private void initializeKeyListener()
    {
        shapePanel.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                System.out.println("key typed: " +e.getKeyChar());
            }
            public void keyReleased(KeyEvent e){
                // TODO: implement this method if you need it
            }
            public void keyTyped(KeyEvent e) {
                char text=e.getKeyChar();
                System.out.println(text);
                if (text=='a') { 
                    push();
                    scene.moveSelected(-distance, 0);
                }else if (text=='d') {
                    push();
                    scene.moveSelected(distance, 0);
                    
                }else if (text=='w') { 
                    push();
                    scene.moveSelected(0, -distance);
                }else if (text=='s') {
                    push();
                    scene.moveSelected(0, distance);
                
                }else if (text == 'z') {
                    try {
                        undoStack.pop(); 
                        Scene previousScene = undoStack.peek();
                        scene.reload(previousScene);
                        repaint();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Nothing to undo!", "Undo Error", JOptionPane.WARNING_MESSAGE);
                    }
                } else if (text == '='){
                    push();
                    for (IShape s : scene){
                    if (s.isSelected()) s.scaleUp(); 
                }
                }else if (text == '-'){
                    push();
                    for (IShape s : scene){
                    if (s.isSelected()) s.scaleDown(); 
                }
                }
                

                repaint();
            }
            
            
        }
        );
    }
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        DrawShapes shapes=new DrawShapes(700, 600);
        shapes.setVisible(true);
    }



}
