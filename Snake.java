import java.awt.Graphics;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Snake {
    private ArrayList<Rectangle2D> snakeParts;
    private String direction;
    private int length;

    public Snake() {
        snakeParts = new ArrayList<>();
        direction = "RIGHT"; // Initial direction
        length = 1; // Initial length of the snake
        snakeParts.add(new Rectangle2D(1, 0, 0, 16, 16, true, false, 0, 0, 0)); // Initial position of the snake
    }

    public Snake(int xPos, int yPos) {
        snakeParts = new ArrayList<>();
        direction = "RIGHT"; // Initial direction
        length = 1; // Initial length of the snake
        snakeParts.add(new Rectangle2D(1, xPos, yPos, 12, 12, true, false, 0, 0, 0)); // Initial position of the snake
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
        Rectangle2D newSegment = new Rectangle2D(1, tail.getXPos(), tail.getYPos(), 12, 12, true, false, 0, 0, 0); // Create
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
