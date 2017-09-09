package br.pjsign.robomath.robo.radar;

import br.pjsign.robomath.Vector2D;
import br.pjsign.robomath.location.Direction;
import robocode.ScannedRobotEvent;
import tools.devnull.robobundle.Bot;
import tools.devnull.robobundle.ScannedEnemy;

public class ScannedEnemyDefault extends ScannedEnemy {

    private Direction direction;
    private Vector2D vector2D;
    public ScannedEnemyDefault(Bot bot, ScannedRobotEvent event) {
        super(bot, event);
        this.vector2D = new Vector2D((float) location().x(), (float) location().y());
    }

    public Vector2D getVector2D() {
        return this.vector2D;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "ScannedEnemyDefault{" +
                "time=" +  when() +
                ", name=" + name() +
                ", heading=" + heading() +
                ", bearing=" + bearing() +
                ", absoluteBearing=" + absoluteBearing() +
                ", distance=" + distance() +
                ", energy=" + energy() +
                ", location=" + location() +
                ", position=" + position() +
                ", direction=" + direction +
                ", vector2D=" + vector2D +
                '}';
    }
}
