/**
 * CollisionDetector class provides static utility methods to detect collisions
 * between Shape2D objects.
 * 
 * This class was added to support snake self-collision, apple pickup, and other
 * collision-based mechanics in the Snake game. You can now call
 * CollisionDetector.isColliding(shape1, shape2) anywhere in your
 * game loop or logic to check for collisions.
 * 
 * @version 1
 * @author Harrison Tran
 */
public class CollisionDetector {

    /**
     * Checks if two Shape2D objects are colliding based on rectangular bounds.
     * This method assumes each shape has a rectangular bounding box.
     * 
     * @param a First shape
     * @param b Second shape
     * @return true if their bounding boxes overlap, false otherwise
     */
    public static boolean isColliding(Shape2D a, Shape2D b) {
        int ax = a.getXPos();
        int ay = a.getYPos();
        int aw = getShapeWidth(a);
        int ah = getShapeHeight(a);

        int bx = b.getXPos();
        int by = b.getYPos();
        int bw = getShapeWidth(b);
        int bh = getShapeHeight(b);

        // Axis-Aligned Bounding Box (AABB) collision check
        return (ax < bx + bw &&
                ax + aw > bx &&
                ay < by + bh &&
                ay + ah > by);
    }

    /**
     * Helper method to get shape width based on concrete class type.
     * 
     * @param shape a Shape2D object
     * @return width of the shape
     */
    private static int getShapeWidth(Shape2D shape) {
        if (shape instanceof Rectangle2D) {
            return ((Rectangle2D) shape).getLength();
        } else if (shape instanceof Oval2D) {
            return ((Oval2D) shape).getWidth();
        } else if (shape instanceof Circle2D) {
            return ((Circle2D) shape).getRadius() * 2;
        }
        return 0; // default or unknown shape
    }

    /**
     * Helper method to get shape height based on concrete class type.
     * 
     * @param shape a Shape2D object
     * @return height of the shape
     */
    private static int getShapeHeight(Shape2D shape) {
        if (shape instanceof Rectangle2D) {
            return ((Rectangle2D) shape).getWidth();
        } else if (shape instanceof Oval2D) {
            return ((Oval2D) shape).getHeight();
        } else if (shape instanceof Circle2D) {
            return ((Circle2D) shape).getRadius() * 2;
        }
        return 0; // default or unknown shape
    }
}