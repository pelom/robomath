package br.pjsign.robomath;

import tools.devnull.robobundle.calc.Point;

public class Vector2D implements Cloneable {
    private float x;
    private float y;

    public Vector2D(Point2D point) {
        this(point.getX(), point.getY());
    }

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Point createPoint() {
        return new Point(x, y);
    }

    public Point2D createPoint2D() {
        return new Point2D(x, y);
    }

    public Vector2D plus(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector2D plusV(Vector2D other) {
        return clone().plus(other.x, other.y);
    }

    public Vector2D minus(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vector2D minusV(Vector2D other) {
        return clone().minus(other.x, other.y);
    }

    public Vector2D multi(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    public Vector2D multiV(float scalar) {
        return clone().multi(scalar);
    }

    public Vector2D div(float scalar) {
        float f = 1.0F / scalar;
        multi(f);
        return this;
    }

    public Vector2D divV(float scalar) {
        return clone().div(scalar);
    }

    public Vector2D unit() {
        return clone().div(this.getSize());
    }

    public Boolean isUnit() {
        return Math.round(this.getSize()) == 1;
    }

    public float getSize() {
        return (float) Math.sqrt( x*x + y*y);
    }

    public float dotV(Vector2D other) {
        return clone().dot(other.x, other.y);
    }

    public float dot(float x, float y) {
        return this.x * x + this.y * y;
    }

    public float perpDotV(Vector2D other) {
        return clone().perpDot(other.x, other.y);
    }
    public float perpDot(float x, float y) {
        return (this.getX()*y) - (this.getY() * x);
    }

    public Angle getAngle(Vector2D other) {
        final float lenX = this.getSize() * other.getSize();
        final float scalar = this.dotV(other);
        final float cos = scalar / lenX;
        final Angle angle = new Angle((float) Math.acos(cos));

        final float perp = this.perpDotV(other);
        //System.out.println("Per: " + perp);
        if(perp > 0) {
            return angle.inverse();
        }
        return angle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector2D vector2D = (Vector2D) o;

        if (Float.compare(vector2D.x, x) != 0) return false;
        return Float.compare(vector2D.y, y) == 0;
    }

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        return result;
    }

    @Override
    public Vector2D clone() {
        try {
            return (Vector2D) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                ", size=" + getSize() +
                '}';
    }
}