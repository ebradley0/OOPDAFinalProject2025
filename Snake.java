import java.awt.Graphics;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * 
 * Snake class that stores all data for the snake in the game.
 * 
 * Constructor forms an arraylist of Rectangle2D objects that represent the
 * snake itself.
 * This design allows for ease of use when shifting the snake's direction via
 * the move() method.
 * 
 * move method() takes in the x and y cordinates to move the head of the snake
 * to. Each subsequeny segment is then moved to the position fo the one ahead of
 * it.
 * This is done by storing the cordinates of each segment, before moving the
 * head. Each segment is then moved to the cordinates of the segment before it.
 * 
 * addSegment() method simply generates a new Rectangle2D object and adds it to
 * the end of the snake.
 * 
 * render() method iterates through each of the segments, calling the draw
 * method to render the snake visually on the players screen.
 * 
 * Getter methods are used to track the position of the head of the snake, as
 * well as the total number of segments
 * 
 * @version 1
 * @author Eric Bradley
 * 
 * 
 */

public class Snake {
    private ArrayList<Rectangle2D> snakeParts;
    private String direction;
    private int length;

    public Snake() {
        snakeParts = new ArrayList<>();
        direction = "RIGHT"; // Initial direction
        length = 1; // Initial length of the snake
        snakeParts.add(new Rectangle2D(1, 0, 0, 16, 16, true, true, 3, 0, 0)); // Initial position of the snake
    }

    public Snake(int xPos, int yPos) {
        snakeParts = new ArrayList<>();
        direction = "RIGHT"; // Initial direction
        length = 1; // Initial length of the snake
        snakeParts.add(new Rectangle2D(1, xPos, yPos, 12, 12, true, true, 3, 0, 0)); // Initial position of the snake
    }

    public void move(int xPos, int yPos) {
        // Store the snakes current positions to use when moving the snake. This is so
        // the segments don't stack from recursively updating their positions.

        ArrayList<Integer> prevX = new ArrayList<>();
        ArrayList<Integer> prevY = new ArrayList<>();

        for (Rectangle2D part : snakeParts) {
            prevX.add(part.getXPos()); // Store the x position of each segment
            prevY.add(part.getYPos()); // Store the y position of each segment
        }

        Rectangle2D head = snakeParts.get(0);
        head.setXPos(xPos);
        head.setYPos(yPos);

        for (int i = 1; i < snakeParts.size(); i++) {
            Rectangle2D part = snakeParts.get(i);
            part.setXPos(prevX.get(i - 1)); // Set the x position of the segment to the previous position
            part.setYPos(prevY.get(i - 1)); // Set the y position of the segment to the previous position
        }

    }

    public void addSegment() {

        Rectangle2D tail = snakeParts.get(snakeParts.size() - 1); // Get the last segment (tail)
        Rectangle2D newSegment = new Rectangle2D(1, tail.getXPos(), tail.getYPos(), 12, 12, true, true, 3, 0, 0); // Create
                                                                                                                  // a
                                                                                                                  // new
                                                                                                                  // segment
                                                                                                                  // at
                                                                                                                  // the
                                                                                                                  // tail's
                                                                                                                  // position
        snakeParts.add(newSegment); // Add the new segment to the snake
        length++; // Increase the length of the snake
    }

    public void render(Graphics g) {
        for (Rectangle2D part : snakeParts) {
            part.Draw(g); // Call the draw method for each segment of the snake
        }
    }

    public int getXPos() {
        return snakeParts.get(0).getXPos(); // Return the x position of the head of the snake
    }

    public int getYPos() {
        return snakeParts.get(0).getYPos(); // Return the y position of the head of the snake
    }

    public ArrayList<Rectangle2D> getSnakeParts() {
        return snakeParts; // Return the list of snake parts
    }

}
