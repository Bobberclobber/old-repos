package body;

import graphics.Canvas;

import java.awt.*;

/**
 * Created by Josef on 14/07/2015.
 * <p>
 *     Interface used by all geometrical bodies in this simulation
 * </p>
 */
public interface Body {
    public Body addToCanvas(Canvas canvas);
    public Body setColor(Color color);
}
