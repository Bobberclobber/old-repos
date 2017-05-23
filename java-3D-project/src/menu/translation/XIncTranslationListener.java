package menu.translation;

import graphics.Canvas;
import math.Vector3f;
import menu.MetaListener;

/**
 * Created by Josef on 12/07/2015.
 * <p>
 *      Positive translation along the x-axis
 * </p>
 */
public class XIncTranslationListener extends MetaListener {
    public XIncTranslationListener(Canvas canvas) {
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
            canvas.translateAll(new Vector3f(1, 0, 0));
        }
    }
}
