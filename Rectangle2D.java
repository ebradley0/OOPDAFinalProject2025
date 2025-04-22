import java.awt.Graphics;

/**
 * Rectangle2D class that extends Shape2D abstract class to represent an
 * rectangle on a 2D canvas.
 * Contains length and width as additional variables, which define the
 * corresponding length and width of the shape. These are used in the Draw()
 * method when rendering the shape.
 * 
 * The move() method updates the position of the rectangle according to its set
 * velocity, where velocity is how many pixels the shape moves per frame.
 * 
 * The constructor makes use of getters and setters from the superclass,
 * Shape2D, to define all of the parameters of the shape.
 * 
 * @version 1
 * @author Eric Bradley
 */
public class Rectangle2D extends Shape2D {
    private int length, width;

    public Rectangle2D() {
        super();
        this.length = 20; // Default length
        this.width = 10; // Default width
    }

    public Rectangle2D(int fillColorIndex, int xPosition, int yPosition, int length, int width, boolean fill,
            boolean outline,
            int outlineColorIndex, int xVelocity, int yVelocity) {
        setXPos(xPosition);
        setYPos(yPosition);
        setFillColorIndex(fillColorIndex);
        this.length = length;
        this.width = width;
        setFillColor(COLORS[fillColorIndex]);
        setOutlineColorIndex(outlineColorIndex);
        setOutlineColor(COLORS[outlineColorIndex]);
        setFill(fill);
        setOutline(outline);
        setXVelocity(xVelocity);
        setYVelocity(yVelocity);
    }

    @Override
    public void Draw(Graphics g) {
        if (isFill()) {
            g.setColor(getFillColor());
            g.fillRect(getXPos(), getYPos(), length, width);
        }
        if (isOutline()) {
            g.setColor(getOutlineColor());
            g.drawRect(getXPos(), getYPos(), length, width);
        }

    }

    public void move() {
        setXPos(getXPos() + getXVelocity());
        setYPos(getYPos() + getYVelocity());
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}
