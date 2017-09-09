package br.pjsign.robomath.utils;

import br.pjsign.robomath.Point2D;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Utils {

    public static BigDecimal toBigecimal(float a) {
        return BigDecimal.valueOf(a).setScale(6, RoundingMode.HALF_UP);
    }

    public static boolean areEquals(float a, float b) {
        return toBigecimal(a).equals(toBigecimal(b));
    }

    public static double distance(final Point2D p1, final Point2D p2) {
        final float x = p1.getX() - p2.getX();
        final float y = p1.getY() - p2.getY();
        final int squared = 2;
        return sqrt(pow(x, squared) + pow(y, squared));
    }

    public static double normalAbsoluteAngle(double angle) {
        return (angle %= 6.283185307179586D) >= 0.0D ? angle : angle + 6.283185307179586D;
    }

    public static double normalAbsoluteAngleDegrees(double angle) {
        return (angle %= 360.0D) >= 0.0D ? angle : angle + 360.0D;
    }

    public static double normalRelativeAngle(double angle) {
        return (angle %= 6.283185307179586D) >= 0.0D ? (angle < 3.141592653589793D ? angle : angle - 6.283185307179586D) : (angle >= -3.141592653589793D ? angle : angle + 6.283185307179586D);
    }

    public static double normalRelativeAngleDegrees(double angle) {
        return (angle %= 360.0D) >= 0.0D ? (angle < 180.0D ? angle : angle - 360.0D) : (angle >= -180.0D ? angle : angle + 360.0D);
    }

    public static double normalNearAbsoluteAngleDegrees(double angle) {
        angle = (angle %= 360.0D) >= 0.0D ? angle : angle + 360.0D;
        if (isNear(angle, 180.0D)) {
            return 180.0D;
        } else {
            if (angle < 180.0D) {
                if (isNear(angle, 0.0D)) {
                    return 0.0D;
                }

                if (isNear(angle, 90.0D)) {
                    return 90.0D;
                }
            } else {
                if (isNear(angle, 270.0D)) {
                    return 270.0D;
                }

                if (isNear(angle, 360.0D)) {
                    return 0.0D;
                }
            }

            return angle;
        }
    }

    public static double normalNearAbsoluteAngle(double angle) {
        angle = (angle %= 6.283185307179586D) >= 0.0D ? angle : angle + 6.283185307179586D;
        if (isNear(angle, 3.141592653589793D)) {
            return 3.141592653589793D;
        } else {
            if (angle < 3.141592653589793D) {
                if (isNear(angle, 0.0D)) {
                    return 0.0D;
                }

                if (isNear(angle, 1.5707963267948966D)) {
                    return 1.5707963267948966D;
                }
            } else {
                if (isNear(angle, 4.71238898038469D)) {
                    return 4.71238898038469D;
                }

                if (isNear(angle, 6.283185307179586D)) {
                    return 0.0D;
                }
            }

            return angle;
        }
    }

    public static boolean isNear(double value1, double value2) {
        return Math.abs(value1 - value2) < 1.0E-5D;
    }
}
