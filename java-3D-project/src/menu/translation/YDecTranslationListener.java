package menu.translation;

import graphics.Canvas;
import math.Vector3f;
import menu.MetaListener;

/**
 * Created by Josef on 12/07/2015.
 * <p>
 *      Negative translation along the y-axis
 * </p>
 */
public class YDecTranslationListener extends MetaListener {
    public YDecTranslationListener(Canvas canvas) {
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
            canvas.translateAll(new Vector3f(0, -1, 0));
        }
    }
}
