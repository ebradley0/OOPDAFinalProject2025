import java.awt.Graphics;

/**
 * Circle2D class that extends Shape2D to represent a circle in 2D space.
 * It includes an aditional member variable for the radius of the circle, which
 * is used in the Draw() method.
 * 
 * The move method updates the position of the circle based on its velocity,
 * which is how many pixels the shape is transformed by per frame.
 * 
 * Constructor makes use of getters and setters from superclass to update the
 * common member variables.
 * 
 * @version 1
 * @author Eric Bradley
 */

public class Circle2D extends Shape2D {
    private int radius;

    public Circle2D() {
        super();
        this.radius = 20;
    }

    public Circle2D(int fillColorIndex, int xPosition, int yPosition, int radius, boolean fill, boolean outline,
            int outlineColorIndex, int xVelocity, int yVelocity) {
        setXPos(xPosition);
        setYPos(yPosition);
        setFillColorIndex(fillColorIndex);
        this.radius = radius;
        setFillColor(COLORS[fillColorIndex]);
        setOutlineColorIndex(outlineColorIndex);
        setOutlineColor(COLORS[outlineColorIndex]);
        setFill(fill);
        setOutline(outline);
        setXVelocity(xVelocity);
        setYVelocity(yVelocity);
    }

    @Override // Radius multiplied by 2 because fillOval uses lengths, rather than a radius.
    public void Draw(Graphics g) {

        if (isFill()) {
            g.setColor(getFillColor());
            g.fillOval(getXPos(), getYPos(), radius * 2, radius * 2);
        }

        if (isOutline()) {
            g.setColor(getOutlineColor());
            g.drawOval(getXPos(), getYPos(), radius * 2, radius * 2);
        }
    }

    @Override
    public void move() {
        setXPos(getXPos() + getXVelocity());
        setYPos(getYPos() + getXVelocity());
    }

    public int getRadius() {
        return this.radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

}
