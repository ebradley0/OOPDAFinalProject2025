import java.awt.Graphics;

/**
 * Oval2D class that extends Shape2D abstract class, used for creating oval
 * shapes on 2D canvas plane.
 * Includes additional members of width and height, which are used when the
 * Draw() method is called.
 * Contains a default constructor as well as a constructor that uses getters and
 * setters from superclass to update variables.
 * Move() method updates the position of the shape based on the velocity, where
 * the velocity is how many pixels the shape moves per frame.
 * 
 * @version 1
 * @author Eric Bradley
 */
public class Oval2D extends Shape2D {
    private int width, height;

    public Oval2D() {
        super();
        this.width = 20;
        this.height = 10;
    }

    public Oval2D(int fillColorIndex, int xPosition, int yPosition, int width, int height, boolean fill,
            boolean outline,
            int outlineColorIndex, int xVelocity, int yVelocity) {
        setXPos(xPosition);
        setYPos(yPosition);
        setFillColorIndex(fillColorIndex);
        this.width = width;
        this.height = height;
        setFillColor(COLORS[fillColorIndex]);
        setOutlineColorIndex(outlineColorIndex);
        setOutlineColor(COLORS[outlineColorIndex]);
        setFill(fill);
        setOutline(outline);
        setXVelocity(xVelocity);
        setYVelocity(yVelocity);
    }

    public void Draw(Graphics g) {
        if (isFill()) {
            g.setColor(getFillColor());
            g.fillOval(getXPos(), getYPos(), width, height);
        }
        if (isOutline()) {
            g.setColor(getOutlineColor());
            g.drawOval(getXPos(), getYPos(), width, height);
        }
    }

    public void move() {
        setXPos(getXPos() + getXVelocity());
        setYPos(getYPos() + getYVelocity());
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
