package math;

/**
 * Created by Josef on 18/06/2015.
 * <p>
 *     A vector of size three, used to store
 *     coordinates in the room.
 * </p>
 */
public class Vector3f {
    public double x, y, z;

    public Vector3f() {
        x = 0f;
        y = 0f;
        z = 0f;
    }

    public Vector3f(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double lengthSquared() {
        return x * x + y * y + z * z;
    }

    public double length() {
        return (double) Math.sqrt(lengthSquared());
    }

    public Vector3f normalize() {
        double length = length();
        // Error handling for the null vector
        if (length == 0) return new Vector3f();
        return divide(length);
    }

    // A special kind of normalization which
    // returns a vector which is 1% the length of the original
    public Vector3f sNormalize() {
        return divide(100);
    }

    public Vector3f add(Vector3f vec) {
        return new Vector3f(this.x + vec.x, this.y + vec.y, this.z + vec.z);
    }

    public Vector3f subtract(Vector3f vec) {
        return this.add(vec.negate());
    }

    public Vector3f negate() {
        return scale(-1f);
    }

    public Vector3f scale(double scalar) {
        return new Vector3f(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    public Vector3f divide(double scalar) {
        return scale(1f / scalar);
    }

    public double dot(Vector3f vec) {
        return this.x * vec.x + this.y * vec.y + this.z * vec.z;
    }

    public Vector3f cross(Vector3f vec) {
        double x = this.y * vec.z - this.z * vec.y;
        double y = this.z * vec.x - this.x * vec.z;
        double z = this.x * vec.y - this.y * vec.x;
        return new Vector3f(x, y, z);
    }

    public Vector3f matrixMul(Matrix3f matrix) {
        double x = this.x * matrix.x1 + this.y * matrix.y1 + this.z * matrix.z1;
        double y = this.x * matrix.x2 + this.y * matrix.y2 + this.z * matrix.z2;
        double z = this.x * matrix.x3 + this.y * matrix.y3 + this.z * matrix.z3;
        return new Vector3f(x, y, z);
    }

    public String getID() {
        return Integer.toString((int) Math.round(x)) +
                Integer.toString((int) Math.round(y)) +
                Integer.toString((int) Math.round(z));
    }
}
