
/**
 * 2D CanvasPanel
 * 
 *
 * @author (Prof R)
 * @version (v1.0 11-17-22)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class CanvasPanel_P6 extends JPanel {
    private final static int X_CORNER = 25;
    private final static int Y_CORNER = 25;
    // private final static int CANVAS_WIDTH = 400;
    // private final static int CANVAS_HEIGHT = 400;
    private final static int CANVAS_WIDTH = 800;
    private final static int CANVAS_HEIGHT = 800;

    private List<Shape2D> shapesList;
    private int frameNumber;
    private boolean isPaused = false;

    public CanvasPanel_P6() {
        // Creating assorted shapes with various sizes and colors
        shapesList = new java.util.ArrayList<>();
        shapesList.add(new Circle2D());
        shapesList.add(new Circle2D(6, 400, 400, 90, true, true, 1, 0, 5));
        shapesList.add(new Rectangle2D());
        shapesList.add(new Rectangle2D(7, 100, 500, 80, 45, true, true, 2, 5, 1));
        shapesList.add(new Rectangle2D(4, 200, 200, 80, 45, true, true, 6, 2, -1));
        shapesList.add(new Oval2D());
        shapesList.add(new Oval2D(1, 300, 300, 20, 45, true, true, 3, -2, 0));
        shapesList.add(new Oval2D(2, 400, 400, 200, 120, true, true, 9, 5, 0));

        // Create a blockhead from rectangles, circles and ovals
        // color x y width height
        shapesList.add(new Rectangle2D(2, 145, 50, 100, 140, true, false, 0, 0, 0));
        shapesList.add(new Rectangle2D(7, 185, 120, 20, 20, true, false, 0, 0, 0));
        // color x y d1 d2
        shapesList.add(new Oval2D(6, 200, 90, 40, 20, true, false, 0, 0, 0));
        shapesList.add(new Oval2D(6, 150, 90, 40, 20, true, false, 0, 0, 0));
        shapesList.add(new Rectangle2D(0, 165, 150, 60, 20, true, false, 0, 0, 0));
        // color x y diameter
        shapesList.add(new Circle2D(3, 160, 93, 15 / 2, true, false, 0, 0, 0)); // Divides by 2 to get radius
        shapesList.add(new Circle2D(3, 215, 93, 15 / 2, true, false, 0, 0, 0)); // Divides by 2 to get radius
        // Callback for keyboard events
        this.setFocusable(true);
        this.addKeyListener(new myActionListener()); // Respond to keyboard events
        System.out.println("keyboard event registered");

        // Create a render loop
        // Create a Swing Timer that will tick 30 times a second
        // At each tick the ActionListener that was registered via the lambda expression
        // will be invoked
        // lambda expression for ActionListener implements actionPerformed method
        Timer renderLoop = new Timer(30, (ActionEvent ev) -> {
            if (!isPaused) { // if not paused, allow the frames to be updated and redrawn once the simulation
                             // is applied.
                frameNumber++;
                Simulate();
                repaint();
            }

        }); // lambda expression for ActionListener implements actionPerformed
        renderLoop.start();
    }

    public void Simulate() {
        for (Shape2D shape : shapesList) { // For each shape in the list, apply the move function()
            shape.move(); // move the shape
        }
    }

    // This method is called by renderloop
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set window background to black
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, CANVAS_WIDTH + 2 * X_CORNER, CANVAS_HEIGHT + 2 * Y_CORNER); // draw the black border

        // Set canvas background to grey
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(X_CORNER, Y_CORNER, CANVAS_WIDTH, CANVAS_HEIGHT); // make the canvas white

        // Need to make draw polymorphic and in a List

        for (Shape2D shape : shapesList) {
            shape.Draw(g);
        }
    }

    public static int getCanvasWidth() {
        return CANVAS_WIDTH;
    }

    public static int getCanvasHeight() {
        return CANVAS_HEIGHT;
    }

    public static int getCanvasXBorder() {
        return X_CORNER;
    }

    public static int getCanvasYBorder() {
        return Y_CORNER;
    }

    public class myActionListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    System.out.println("press up arrow");
                    break;
                case KeyEvent.VK_DOWN:
                    System.out.println("press down arrow");
                    break;
                case KeyEvent.VK_LEFT:
                    System.out.println("press left arrow");
                    break;
                case KeyEvent.VK_RIGHT:
                    System.out.println("press right arrow");
                    break;
                case KeyEvent.VK_SPACE: // Used for updating the pause state, which enables and disables the simulation.

                    isPaused = !isPaused; // toggle the pause state
                    if (isPaused) {
                        System.out.println("Motion paused");
                    } else {
                        System.out.println("Motion unpaused");
                    }
                    break;
                default:
                    System.out.println("press some other key besides the arrow keys");
            }
        }

        public void keyReleased(KeyEvent e) {
            System.out.println("released");
        }
    }
}
