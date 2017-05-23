package menu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Josef on 12/07/2015.
 * <p>
 *     A super class for several listeners used for the different buttons
 * </p>
 */
public class MetaListener extends MouseAdapter {
    private Transformation transformation;
    private boolean pressed = false;

    public MetaListener() {}

    public void setTransformation(Transformation transformation) {
        this.transformation = transformation;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        pressed = true;

        new Thread() {
            public void run() {
                while (pressed) {
                    transformation.execute();
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        pressed = false;
    }

    // An interface used to execute different
    // code when different buttons are pressed
    public interface Transformation {
        public void execute();
    }
}
