package br.pjsign.robomath.plugin.draw.menu;

import br.pjsign.robomath.Angle;
import br.pjsign.robomath.location.Direction;
import br.pjsign.robomath.robo.radar.ScannedEnemyDefault;
import tools.devnull.robobundle.Bot;
import tools.devnull.robobundle.Enemy;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.awt.event.KeyEvent.VK_BACK_SPACE;
import static java.awt.event.KeyEvent.VK_E;

public class EnemyPainel extends PanelMenuDefault {

    private Map<Integer, ScannedEnemyDefault> enemyMap;
    private Integer enemyIndex;

    public EnemyPainel(Bot bot) {
        super(bot);
        this.enemyMap = new HashMap<Integer, ScannedEnemyDefault>();
    }

    @Override
    public void activeCommand(KeyEvent event) {
        selectCommand(event);
        switch (event.getKeyCode()) {
            case VK_E:
                this.setShow(!this.isShow());
                break;
            case VK_BACK_SPACE:
                this.enemyIndex = null;
                break;
        }
    }

    private void selectCommand(KeyEvent event) {
        try {
            Integer value = Integer.valueOf(KeyEvent.getKeyText(event.getKeyChar()));
            final ScannedEnemyDefault en = this.enemyMap.get(value);
            if(en != null) {
                this.enemyIndex = value;
            }
        } catch (Exception ex) { }
    }

    @Override
    public void writePanel(List<String> builders) {
        updateEnemy();

        if(isNotTarget()) {
            writeTitle("Enemies", true, builders);
            writeList(builders);
        } else {
            final ScannedEnemyDefault enemy = this.enemyMap.get(enemyIndex);
            writeTitle(enemy.name(), true, builders);
            writeEnemy(enemy, builders);
        }

    }

    private Boolean isNotTarget() {
        return this.enemyIndex == null;
    }

    private void writeList(List<String> builders) {
        for (Integer i : this.enemyMap.keySet()) {
            ScannedEnemyDefault enemyDefault = this.enemyMap.get(i);
            this.enemyMap.put(Integer.valueOf(i), enemyDefault);
            builders.add("("+ i +") > " + enemyDefault.name().toUpperCase());
        }
    }

    private void updateEnemy() {
        int i = 0;
        for (Enemy enemy : getBot().radar().knownEnemies()) {
            ScannedEnemyDefault enemyDefault = (ScannedEnemyDefault) enemy;
            this.enemyMap.put(Integer.valueOf(i), enemyDefault);
            i++;
        }
    }

    private void writeEnemy(ScannedEnemyDefault enemyDefault, List<String> builders) {
        final Direction dHead = Direction.getDirection(
                new Angle((float) enemyDefault.heading().radians()));
        final Direction dBear = Direction.getDirection(
                new Angle((float) enemyDefault.bearing().radians()));

        builders.add("Time: " + enemyDefault.when());
        builders.add("Distance: " + enemyDefault.distance());
        builders.add("Velocity: " + enemyDefault.velocity());
        builders.add("Energy: " + enemyDefault.energy());
        builders.add("");
        builders.add("Location: " + enemyDefault.location());
        builders.add("Position: " + enemyDefault.position());
        builders.add("Heading: " + enemyDefault.heading().degrees() + " " + dHead.getName());
        builders.add("Bearing: " + enemyDefault.bearing().degrees() + " " + dBear.getName());
        builders.add("");

        if(enemyDefault.getDirection() != null) {
            builders.add("Direction: " + enemyDefault.getDirection().getDescription());
            builders.add("Vector: " + enemyDefault.getVector2D().getSize());
        }
        builders.add("");
    }
}