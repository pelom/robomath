package br.pjsign.robomath;

import br.pjsign.robomath.utils.Utils;

public class Point2D {

    private final float x;
    private final float y;

    public Point2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public double distance(final Point2D other) {
        return Utils.distance(this, other);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point2D point = (Point2D) o;

        return Utils.areEquals(x, point.x) && Utils.areEquals(y, point.y);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + Utils.toBigecimal(x).hashCode();
        result = 37 * result + Utils.toBigecimal(y).hashCode();
        return result;
    }
}
