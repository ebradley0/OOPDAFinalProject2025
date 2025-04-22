
/**
 * 2D Frame for 2D Graphics
 * 
 *
 * @author (Prof R)
 * @version (v1.0 11-17-22)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CanvasFrame {
    private JFrame frame; // the actual frame(window) we'll be showing
    private CanvasPanel_Final canvas; // the canvas we'll be drawing

    /**
     * Creates a new CanvasFrame object.
     */
    public CanvasFrame() {
        frame = new JFrame("OPPDA Spring 2025 CanvasFrame"); // make the JFrame, and set thw window bar title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new CanvasPanel_Final(); // CanvasPanel extends a JPanel

        // Use the canvasPanel size & borders to define window size
        canvas.setPreferredSize(new Dimension(2 * canvas.getCanvasXBorder() + canvas.getCanvasWidth(),
                2 * canvas.getCanvasYBorder() + canvas.getCanvasHeight()));
        frame.getContentPane().add(canvas); // put the canvas (JPanel) in the frame

        frame.pack(); // make everything the preferred size
        frame.setVisible(true); // show the frame
    }
}
