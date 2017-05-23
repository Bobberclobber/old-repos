package menu.translation;

import graphics.Canvas;
import math.Vector3f;
import menu.MetaListener;

/**
 * Created by Josef on 12/07/2015.
 * <p>
 *      Positive translation along the y-axis
 * </p>
 */
public class YIncTranslationListener extends MetaListener {
    public YIncTranslationListener(Canvas canvas) {
        super();
        super.setTransformation(new Trans(canvas));
    }

    private class Trans implements MetaListener.Transformation {
        Canvas canvas;

        public Trans(Canvas canvas) {
            this.canvas = canvas;
        }

        @Override
        public void execute() {
            canvas.translateAll(new Vector3f(0, 1, 0));
        }
    }
}
