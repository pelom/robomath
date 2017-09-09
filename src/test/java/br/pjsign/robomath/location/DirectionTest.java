package br.pjsign.robomath.location;

import br.pjsign.robomath.Angle;
import br.pjsign.robomath.Vector2D;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class DirectionTest {
    @Test
    public void getWindRose() throws Exception {
        final List<Direction> directions = Direction.getWindRose();
        assertTrue(!directions.isEmpty());
    }

    @Test
    public void getDirection() throws Exception {

        final Vector2D needle = Direction.NEEDLE_SOUTH;

        final Vector2D x1 = new Vector2D(100,800);
        final Vector2D x2 = new Vector2D(100,10);

        final Vector2D x6 = new Vector2D(50,400);
        final Vector2D x3 = new Vector2D(100,400);

        final Vector2D x4 = new Vector2D(100,100);
        final Vector2D x5 = new Vector2D(800,800);

        final Vector2D x7 = new Vector2D(800,100);
        final Vector2D x8 = new Vector2D(100,800);

        Angle angle = Direction.getAngle(x1, x2, needle);
        assertEquals(Angle.ZERO, angle);
        assertEquals(Direction.SOUTH, Direction.getDirection(angle));

        angle = Direction.getAngle(x2, x1, needle);
        assertEquals(Angle.PI, angle);
        assertEquals(Direction.NORTH, Direction.getDirection(angle));

        angle = Direction.getAngle(x3, x6, needle);
        assertEquals(Angle.PI_OVER_TWO, angle);
        assertEquals(Direction.EAST, Direction.getDirection(angle));

        angle = Direction.getAngle(x6, x3, needle);
        assertEquals(Angle.MINUS_PI_OVER_TWO, angle);
        assertEquals(Direction.WEST, Direction.getDirection(angle));

        angle = Direction.getAngle(x4, x5, needle);
        assertEquals(Angle.PI.minus(Angle.PI_OVER_FOUR).inverse(), angle);
        assertEquals(Direction.NORTHWEST, Direction.getDirection(angle));

        angle = Direction.getAngle(x5, x4, needle);
        assertEquals(Angle.PI_OVER_FOUR, angle);
        assertEquals(Direction.SOUTHEAST, Direction.getDirection(angle));

        angle = Direction.getAngle(x7, x8, needle);
        assertEquals(Angle.PI.minus(Angle.PI_OVER_FOUR), angle);
        assertEquals(Direction.NORTHEAST, Direction.getDirection(angle));

        angle = Direction.getAngle(x8, x7, needle);
        assertEquals(Angle.PI_OVER_FOUR.inverse(), angle);
        assertEquals(Direction.SOUTHWEST, Direction.getDirection(angle));
    }

}