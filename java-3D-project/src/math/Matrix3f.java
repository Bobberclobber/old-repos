package math;

/**
 * Created by Josef on 18/06/2015.
 * <p>
 *     A 3x3 matrix
 * </p>
 */
public class Matrix3f {
    public double x1, y1, z1;
    public double x2, y2, z2;
    public double x3, y3, z3;

    public Matrix3f() {
        x1 = y1 = z1 = 0f;
        x2 = y2 = z2 = 0f;
        x3 = y3 = z3 = 0f;
    }

    public Matrix3f(Vector3f r1, Vector3f r2, Vector3f r3) {
        x1 = r1.x; y1 = r1.y; z1 = r1.z;
        x2 = r2.x; y2 = r2.y; z2 = r2.z;
        x3 = r3.x; y3 = r3.y; z3 = r3.z;
    }

    public Matrix3f(double x1, double y1, double z1,
                    double x2, double y2, double z2,
                    double x3, double y3, double z3) {
        this.x1 = x1; this.y1 = y1; this.z1 = z1;
        this.x2 = x2; this.y2 = y2; this.z2 = z2;
        this.x3 = x3; this.y3 = y3; this.z3 = z3;
    }
}
