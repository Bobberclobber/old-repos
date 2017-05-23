import graphics.Window;
import menu.Menu;

/**
 * Created by Josef on 20/06/2015.
 * <p>
 *     Main class of the 3D project
 * </p>
 */
public class Main {
    public static void main(String[] args) {
        new Menu(new Window().canvas);
    }
}
