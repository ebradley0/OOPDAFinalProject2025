
/**
 * Shape2D abstract class
 * Represents a 2D shape with various parameters, including positions, velocity, colors, as well as enabling or disabling outlines.
 * All graphic subclasses must use implement this class, as it includes the necessary member variables for any 2D shapes.
 * Provides basic framework for how the shapes will be drawn and moved on a canvas via the move() method.
 * Must implement the Draw() method to draw the shape on the canvas.
 * 
 * 
 * @version 1
 * @author Eric Bradley
 */

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape2D {

    public static final Color[] COLORS = {
            // R G B
            new Color(255, 0, 0), // Red 0
            new Color(0, 255, 0), // Green 1
            new Color(0, 0, 255), // Blue 2
            new Color(0, 0, 0), // Black 3
            new Color(128, 128, 128), // Grey 4
            new Color(255, 255, 255), // White 5
            new Color(255, 255, 0), // Yellow 6
            new Color(0, 255, 255), // Cyan 7
            new Color(255, 0, 255), // Magenta 8
            new Color(165, 42, 42), // Brown 9
            new Color(255, 38, 38),
            new Color(255, 168, 38),
            new Color(212, 255, 38),
            new Color(82, 255, 38),
            new Color(38, 255, 125),
            new Color(38, 255, 255),
            new Color(38, 125, 255),
            new Color(82, 38, 255),
            new Color(212, 38, 255),
            new Color(255, 38, 168),
    };

    private int xPos, yPos, xVelocity, yVelocity, fillColorIndex, outlineColorIndex;
    private Color fillColor, outlineColor;
    private boolean fill, outline;

    Shape2D() {
        this.xPos = 0;
        this.yPos = 0;
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.fillColorIndex = 2;
        this.fillColor = COLORS[2];
        this.outlineColorIndex = 3;
        this.outlineColor = COLORS[3];
        this.fill = true;
        this.outline = true;
    }

    public abstract void Draw(Graphics g);

    public abstract void move();

    public int getXPos() {
        return this.xPos;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public int getYPos() {
        return this.yPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    public int getXVelocity() {
        return this.xVelocity;
    }

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getYVelocity() {
        return this.yVelocity;
    }

    public void setYVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public int getFillColorIndex() {
        return this.fillColorIndex;
    }

    public void setFillColorIndex(int fillColorIndex) {
        this.fillColorIndex = fillColorIndex;
    }

    public int getOutlineColorIndex() {
        return this.outlineColorIndex;
    }

    public void setOutlineColorIndex(int outlineColorIndex) {
        this.outlineColorIndex = outlineColorIndex;
    }

    public Color getFillColor() {
        return this.fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Color getOutlineColor() {
        return this.outlineColor;
    }

    public void setOutlineColor(Color outlineColor) {
        this.outlineColor = outlineColor;
    }

    public boolean isFill() {
        return this.fill;
    }

    public boolean getFill() {
        return this.fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public boolean isOutline() {
        return this.outline;
    }

    public boolean getOutline() {
        return this.outline;
    }

    public void setOutline(boolean outline) {
        this.outline = outline;
    }

}
