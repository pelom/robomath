package br.pjsign.robomath;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Vector2DTest {

    private Vector2D v;
    private Vector2D u;

    @Before
    public void setUp() throws Exception {
        this.v = new Vector2D(new Point2D(1, -2));
        this.u = new Vector2D(-8, 2);
    }


    @Test
    public void plus() throws Exception {
        final Vector2D wVector = v.plusV(u);
        assertTrue(wVector.equals(new Vector2D(-7, 0)));
    }

    @Test
    public void plus1() throws Exception {
        this.v.plus(-8, 2);
        assertTrue(v.equals(new Vector2D(-7, 0)));
    }

    @Test
    public void minus() throws Exception {
        final Vector2D wVector = v.minusV(u);
        assertEquals(Float.valueOf(9f), Float.valueOf(wVector.getX()));
        assertEquals(Float.valueOf(-4), Float.valueOf(wVector.getY()));
    }

    @Test
    public void minus1() throws Exception {
        this.v.minus(-8, 2);
        assertTrue(v.equals(new Vector2D(9, -4)));
    }

    @Test
    public void multi() throws Exception {
        this.v.multi(2);
        assertEquals(Double.valueOf(2), Double.valueOf(this.v.getX()));
        assertEquals(Double.valueOf(-4), Double.valueOf(this.v.getY()));
    }

    @Test
    public void dot() throws Exception {
        final double dot = v.dotV(u);
        assertEquals(Double.valueOf(-12), Double.valueOf(dot));
    }

    @Test
    public void div() throws Exception {
        final Vector2D unit = v.div(v.getSize());
        assertEquals(Float.valueOf(0.4472136F), Float.valueOf(unit.getX()));
        assertEquals(Float.valueOf(-0.8944272F), Float.valueOf(unit.getY()));
    }

    @Test
    public void vectorUnit() throws Exception {
        final Vector2D unit = v.unit();
        assertEquals(Float.valueOf(1), Float.valueOf(Math.round(unit.dotV(unit))));
    }

    @Test
    public void isUnit() throws Exception {
        float p = (float) (1/Math.sqrt(2));
        final Vector2D unit = new Vector2D(p, p);
        assertEquals(Boolean.TRUE, unit.isUnit());
    }

    @Test
    public void getSize() throws Exception {
        assertEquals(Float.valueOf(2.23606797749979f), Float.valueOf(v.getSize()));
        assertEquals(Float.valueOf(8.246211251235321f), Float.valueOf(u.getSize()));
    }

    @Test
    public void getAngle() throws Exception {
        final Vector2D vecU = new Vector2D(4,3);
        final Vector2D vecV = new Vector2D(5,-1);
        final Angle angle = vecU.getAngle(vecV);
        assertEquals(Float.valueOf(48.179832f), Float.valueOf(angle.degrees()));
        assertEquals(Float.valueOf(0.8408967f), Float.valueOf(angle.radians()));
    }
}