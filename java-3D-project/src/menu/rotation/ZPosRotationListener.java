package menu.rotation;

import graphics.Canvas;
import math.Matrix3f;
import menu.MetaListener;

/**
 * Created by Josef on 12/07/2015.
 * <p>
 *     Rotates around the z-axis in the positive direction
 * </p>
 */
public class ZPosRotationListener extends MetaListener {
    public final double DEG = Math.PI / 180;

    public ZPosRotationListener(Canvas canvas) {
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
                    Math.cos(DEG), -Math.sin(DEG), 0,
                    Math.sin(DEG), Math.cos(DEG), 0,
                    0, 0, 1);
            canvas.rotateAll(rMatrix);
        }
    }
}
