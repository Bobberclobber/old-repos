package menu.rotation;

import graphics.Canvas;
import math.Matrix3f;
import menu.MetaListener;

/**
 * Created by Josef on 12/07/2015.
 * <p>
 *     Rotates around the x-axis in the negative direction
 * </p>
 */
public class XNegRotationListener extends MetaListener {
    public final double DEG = -Math.PI / 180;

    public XNegRotationListener(Canvas canvas) {
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
            Matrix3f rMatrix = new Matrix3f(
                    1, 0, 0,
                    0, Math.cos(DEG), -Math.sin(DEG),
                    0, Math.sin(DEG), Math.cos(DEG));
            canvas.rotateAll(rMatrix);
        }
    }
}
