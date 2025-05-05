
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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CanvasPanel_Final extends JPanel {
    private final static int X_CORNER = 25;
    private final static int Y_CORNER = 25;
    private final static int buffer = 25;
    // private final static int CANVAS_WIDTH = 400;
    // private final static int CANVAS_HEIGHT = 400;
    private final static int CANVAS_WIDTH = 800;
    private final static int CANVAS_HEIGHT = 800;
    private Rectangle2D apple; // Apple object
    private Shape2D[][] grid;
    private int frameNumber;
    private boolean isPaused = false;
    private int gridSize = 16;
    private int collumns = CANVAS_HEIGHT / gridSize;
    private int rows = CANVAS_WIDTH / gridSize;
    private int centerOffset = (gridSize) / (gridSize / 2);
    private String direction = "UP"; // Initial direction of the snake
    Snake snake; // Snake object
    private int gameSpeed = 90;
    Timer renderLoop;

    // Collision detection variables
    private CollisionDetector collisionDetector = new CollisionDetector(); // Collision detector object

    public CanvasPanel_Final() {
        // Creating assorted shapes with various sizes and colors
        // Initial panel rendering
        grid = new Shape2D[rows][collumns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < collumns; j++) {
                grid[i][j] = (new Rectangle2D(4, centerOffset + buffer + i * gridSize,
                        centerOffset + buffer + j * gridSize,
                        gridSize - 4, gridSize - 4, true, false, 0, 0, 0)); // Generate
                // a

            }
        }

        // Snake init
        snake = new Snake(grid[0][0].getXPos(), grid[0][0].getYPos()); // Create a snake at the first grid slot

        // Callback for keyboard events
        this.setFocusable(true);
        this.addKeyListener(new myActionListener()); // Respond to keyboard events
        System.out.println("keyboard event registered");

        // Create a render loop
        // Create a Swing Timer that will tick 30 times a second
        // At each tick the ActionListener that was registered via the lambda expression
        // will be invoked
        // lambda expression for ActionListener implements actionPerformed method
        renderLoop = new Timer(gameSpeed, (ActionEvent ev) -> {
            if (!isPaused) { // if not paused, allow the frames to be updated and redrawn once the simulation
                             // is applied.
                
                Simulate();
                repaint();
            }

        }); // lambda expression for ActionListener implements actionPerformed
        renderLoop.start();
    }

    public void Simulate() {
        

        if (apple == null) {
            generateApple(); // Generate a new apple if there is none
        }

        switch (direction) {
            case "RIGHT":
                if (snake.getXPos() >= grid[collumns - 1][0].getXPos()) {
                    snake.move(grid[0][0].getXPos(), snake.getYPos()); // Wrap around to the left side of the grid
                    break;
                }
                snake.move(snake.getXPos() + gridSize, snake.getYPos());

                break;
            case "LEFT":
                if (snake.getXPos() <= grid[0][0].getXPos()) {
                    snake.move(grid[collumns - 1][0].getXPos(), snake.getYPos()); // Wrap around to the left side of the
                    break; // grid
                }
                snake.move(snake.getXPos() - gridSize, snake.getYPos());

                break;
            case "UP":
                if (snake.getYPos() <= grid[0][0].getYPos()) {
                    snake.move(snake.getXPos(), grid[0][rows - 1].getYPos()); // Wrap around to the bottom side of the
                    break; // grid
                }
                snake.move(snake.getXPos(), snake.getYPos() - gridSize);

                break;
            case "DOWN":
                if (snake.getYPos() >= grid[0][rows - 1].getYPos()) {
                    snake.move(snake.getXPos(), grid[0][0].getYPos()); // Wrap around to the top side of the
                    break; // grid
                }
                snake.move(snake.getXPos(), snake.getYPos() + gridSize);

                break;

            default:
                break;

        }

        Rectangle2D head = snake.getSnakeParts().get(0); // Get the head of the snake

        if (collisionDetector.isColliding(head, apple)) {
            snake.addSegment(); // Add a new segment to the snake if it collides with the apple
            apple = null; // Remove the apple after it has been eaten
            System.out.println("Apple eaten!"); // Print a message to the console;
            generateApple(); // Generate a new apple

        }

        // Check if the head is colliding with any of the snake segments, if it is, game over.

      
        for (int i = 2; i < snake.getSnakeParts().size(); i++)
        {
            if (collisionDetector.isColliding(snake.getSnakeParts().get(i), snake.getSnakeParts().get(0)))
            {
                System.out.println("Collission detected, game over!");
                System.out.println("You got" + snake.getSnakeParts().size() + " points!");
                renderLoop.stop();
                gameOverPrompt();
                
            }
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
        g.setColor(Color.BLACK);
        g.fillRect(X_CORNER, Y_CORNER, CANVAS_WIDTH, CANVAS_HEIGHT); // make the canvas white

        // Need to make draw polymorphic and in a List

        // Init painting
        for (Shape2D[] row : grid) { // For each row in the grid
            for (Shape2D gridSlot : row) { // For each grid slot, draw a square.
                gridSlot.Draw(g2);
            }
        }
        snake.render(g2); // Draw the snake

        if (apple != null) {
            apple.Draw(g2); // Draw the apple if it exists
        }

        //Score rendering

        g2.drawString("Points: " + (snake.getSnakeParts().size() - 1), 400, 20);

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

    public void generateApple() {
        int randX = (int) (Math.random() * rows); // Random x position
        int randY = (int) (Math.random() * collumns); // Random y position

        // Check all snake segments to ensure the apple doesnt spawn on the snake.
        ArrayList<Rectangle2D> snakeParts = snake.getSnakeParts(); // Get the snake parts
        for (Rectangle2D part : snakeParts) {
            if (part.getXPos() == grid[randX][randY].getXPos() && part.getYPos() == grid[randX][randY].getYPos()) {
                // If the apple spawns on the snake, generate a new apple
                generateApple();
                return;
            }
        }

        apple = new Rectangle2D(0, grid[randX][randY].getXPos(), grid[randX][randY].getYPos(), 12, 12,
                true, false, 0, 0, 0); // Create a new apple at the random position

    }

    public class myActionListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:

                    if (direction == "DOWN") {
                        break;
                    }
                    direction = "UP"; // Set the direction to UP
                    break;
                case KeyEvent.VK_DOWN:

                    if (direction == "UP") {
                        break;
                    }
                    direction = "DOWN"; // Set the direction to DOWN
                    break;
                case KeyEvent.VK_LEFT:

                    if (direction == "RIGHT") {
                        break;
                    }
                    direction = "LEFT"; // Set the direction to LEFT
                    break;
                case KeyEvent.VK_RIGHT:

                    if (direction == "LEFT") {
                        break;
                    }
                    direction = "RIGHT"; // Set the direction to RIGHT
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

    public void gameOverPrompt()

    {
        JOptionPane.showMessageDialog(null, "Game over!");
    }
}
