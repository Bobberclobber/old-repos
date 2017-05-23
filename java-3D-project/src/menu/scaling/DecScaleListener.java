package menu.scaling;

import graphics.Canvas;
import menu.MetaListener;

/**
 * Created by Josef on 12/07/2015.
 * <p>
 *     Reduces the length of the x-axis component
 * </p>
 */
public class DecScaleListener extends MetaListener {

    public DecScaleListener(Canvas canvas) {
        super();
        super.setTransformation(new Trans(canvas));
    }

    private class Trans implements Transformation {
        Canvas canvas;

        public Trans(Canvas canvas) {
            this.canvas = canvas;
        }

        @Override
        public void execute() {
            canvas.scaleAll(-0.5);
        }
    }
}
