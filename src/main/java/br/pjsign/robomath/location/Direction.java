package br.pjsign.robomath.location;

import br.pjsign.robomath.Angle;
import br.pjsign.robomath.Vector2D;
import br.pjsign.robomath.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Direction {

    public static final Vector2D NEEDLE_SOUTH      = new Vector2D(0, 1);
    public static final Vector2D NEEDLE_NORTH      = new Vector2D(0, -1);
    public static final Vector2D NEEDLE_EAST       = new Vector2D(1, 0);
    public static final Vector2D NEEDLE_WEST       = new Vector2D(-1, 0);
    public static final Vector2D NEEDLE_NORTHWEST  = new Vector2D(-1, -1);
    public static final Vector2D NEEDLE_SOUTHEAST  = new Vector2D(1, 1);
    public static final Vector2D NEEDLE_NORTHEAST  = new Vector2D(-1, 1);
    public static final Vector2D NEEDLE_SOUTHWEST  = new Vector2D(1, -0);

    public static final Direction SOUTH     = createDirectionDeg(
            "S", "SOUTH",    0f,    22.5f, NEEDLE_SOUTH);
    public static final Direction SOUTHEAST = createDirectionDeg(
            "SE","SOUTHEAST",22.5f, 67.5f, NEEDLE_SOUTHEAST);
    public static final Direction EAST      = createDirectionDeg(
            "E", "EAST",     67.5f, 112.5f, NEEDLE_EAST);
    public static final Direction NORTHEAST = createDirectionDeg(
            "NE","NORTHEAST",112.5f,157.5f, NEEDLE_NORTHEAST);
    public static final Direction NORTH     = createDirectionDeg(
            "N", "NORTH",    157.5f,202.5f, NEEDLE_NORTH);
    public static final Direction NORTHWEST = createDirectionDeg(
            "NW","NORTHWEST",202.5f,247.5f, NEEDLE_NORTHWEST);
    public static final Direction WEST      = createDirectionDeg(
            "W", "WEST",     247.5f,292.5f, NEEDLE_WEST);
    public static final Direction SOUTHWEST = createDirectionDeg(
            "SW","SOUTHWEST",292.5f,337.5f, NEEDLE_SOUTHWEST);
    public static final Direction SOUTH2    = createDirectionDeg(
            "S", "SOUTH",    337.5f,360f, NEEDLE_SOUTH);

    private static final List<Direction> WIND_ROSE = new ArrayList<Direction>();

    static {
        WIND_ROSE.add(SOUTH);
        WIND_ROSE.add(SOUTHEAST);
        WIND_ROSE.add(EAST);
        WIND_ROSE.add(NORTH);
        WIND_ROSE.add(NORTHEAST);
        WIND_ROSE.add(NORTHWEST);
        WIND_ROSE.add(WEST);
        WIND_ROSE.add(SOUTHWEST);
        WIND_ROSE.add(SOUTH2);
    }

    private static Direction createDirectionDeg(
            String name, String description, float min, float max, Vector2D sense) {
        return new Direction(name, description,
                (float) Math.toRadians(min), (float) Math.toRadians(max), sense);
    }

    public static List<Direction> getWindRose() {
        return WIND_ROSE;
    }

    public static Direction getDirection(final Angle angle) {
        for (Direction direction : getWindRose()) {
            if(direction.isDirection(angle)) {
                return direction;
            }
        }
        return null;
    }

    public static Angle getAngle(Vector2D v, Vector2D u, Vector2D director) {
       return director.getAngle(v.minusV(u).unit());
    }

    public static Direction getDirectionSouth(Vector2D v, Vector2D u) {
        final Angle angle = getAngle(v, u, WIND_ROSE.get(0).getSense());
        return getDirection(angle);
    }

    private String name;
    private String description;
    private Angle angleMax;
    private Angle angleMin;
    private Vector2D sense;

    private Direction(String name, String description, float angleRadMin, float angleRadMax, Vector2D sense) {
        this.name = name;
        this.sense = sense;
        this.description = description;
        this.angleMax = new Angle(angleRadMax);
        this.angleMin = new Angle(angleRadMin);
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Angle getAngleMax() {
        return angleMax;
    }

    public Angle getAngleMin() {
        return angleMin;
    }

    public Vector2D getSense() {
        return sense;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Direction direction = (Direction) o;

        return getName().equals(direction.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    public Boolean isDirection(Angle angle) {
        double a = Utils.normalAbsoluteAngle(angle.radians());
        double mix = this.getAngleMin().radians();
        double max = this.getAngleMax().radians();
        return mix <= a && a <= max;
    }

    @Override
    public String toString() {
        return "Direction{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", sense=" + sense +
                '}';
    }
}