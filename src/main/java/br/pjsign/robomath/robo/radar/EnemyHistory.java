package br.pjsign.robomath.robo.radar;

import br.pjsign.robomath.Point2D;
import br.pjsign.robomath.Vector2D;
import tools.devnull.robobundle.Bot;
import tools.devnull.robobundle.Enemy;
import tools.devnull.robobundle.annotation.When;
import tools.devnull.robobundle.calc.Point;
import tools.devnull.robobundle.event.EnemyScannedEvent;
import tools.devnull.robobundle.event.Events;
import tools.devnull.robobundle.util.Drawer;

import java.util.*;

import static java.awt.Color.*;
import static tools.devnull.robobundle.util.Drawer.Mode.TRANSPARENT;

public class EnemyHistory {

    private Map<String, List<ScannedEnemyDefault>> enemyData;

    private int historySize;

    private final Bot bot;

    public EnemyHistory(Bot bot) {
        this(bot, 20);
    }

    public EnemyHistory(Bot bot, int historySize) {
        this.enemyData = new HashMap<String, List<ScannedEnemyDefault>>();
        this.bot = bot;
        this.historySize = historySize;
    }

    @When(Events.ENEMY_SCANNED)
    public void registerEnemy(EnemyScannedEvent event) {
        final ScannedEnemyDefault enemy = (ScannedEnemyDefault) event.enemy();
        if (!this.enemyData.containsKey(enemy.name())) {
            this.enemyData.put(enemy.name(), new LinkedList<ScannedEnemyDefault>());
        }

        final List<ScannedEnemyDefault> history = this.enemyData.get(enemy.name());
        history.add(enemy);

        if (history.size() > this.historySize) {
            history.remove(0);
        }
    }

    public List<ScannedEnemyDefault> dataFor(final Enemy enemy) {
        if (enemy == null || !this.enemyData.containsKey(enemy.name())) {
            return Collections.emptyList();
        }
        return new ArrayList<ScannedEnemyDefault>(enemyData.get(enemy.name()));
    }

    @When(Events.DRAW)
    public void drawHistory(Drawer drawer) {
        Collection<Enemy> enemies = bot.radar().knownEnemies();
        for (Enemy enemy : enemies) {
            int i = 0;
            List<ScannedEnemyDefault> history = dataFor(enemy);
           // Collections.reverse(history);

            Vector2D vector2DOuther = null;
            Vector2D vector2DSum = new Vector2D(0, 0);
            for (ScannedEnemyDefault enemyHistory : history) {

                final Vector2D vector2D = enemyHistory.getVector2D();
                final Point p2 = vector2D.createPoint();

                drawer.draw(TRANSPARENT, LIGHT_GRAY).circle().at(enemyHistory.location());

                if(vector2DOuther != null) {
                    final Vector2D diff = vector2D.minusV(vector2DOuther);
                    vector2DSum = vector2DSum.plusV(diff);
                    final Point p1 = vector2DOuther.createPoint();

                    drawer.draw(TRANSPARENT, MAGENTA).line().from(p1).to(p2);

                    if(enemyHistory.getDirection() != null) {
                        drawer.draw(RED).marker().at(p2);
//                        drawer.draw(WHITE).string("%s",
//                                enemyHistory.getDirection().getName()
//                                        + " T:" + enemyHistory.when())
//                                        //+ " L:" + vector2DSum.getSize()
//                                        //+ " D:" + diff.getSize())
//                                        .at(p2);
                    }
                    if (++i == 20) {
                        break;
                    }
               } //else {
//                    drawer.draw(WHITE).string("%s",
//                            "O"
//                                    + " T:" + enemyHistory.when())
//                                    //+ " L:" + vector2DSum.getSize()
//                                    //+ " D: 0")
//                                    .at(p2);
//                }
                vector2DOuther = vector2D;
            }
        }
    }

    public boolean isEnemyStopped(Enemy enemy) {
        for (Enemy enemyHistory : dataFor(enemy)) {
            if (enemyHistory.isMoving()) {
                return false;
            }
        }
        return true;
    }

    public Boolean targetStoped() {
        if (bot.radar().hasTargetSet()) {
            Enemy target = bot.radar().target();
            return isEnemyStopped(target);
        }
        return false;
    }
}
