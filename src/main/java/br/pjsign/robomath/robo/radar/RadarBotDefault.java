package br.pjsign.robomath.robo.radar;

import br.pjsign.robomath.Vector2D;
import br.pjsign.robomath.location.Direction;
import br.pjsign.robomath.robo.BotDefault;
import tools.devnull.robobundle.BaseBot;
import tools.devnull.robobundle.parts.radar.DefaultRadar;

import java.util.List;

public class RadarBotDefault extends DefaultRadar {

    private EnemyHistory enemyHistory;

    private FirstLastEnemyScanningSystem enemyScannigSystem;

    public RadarBotDefault(BaseBot bot) {
        super(bot);
        this.enemyHistory = new EnemyHistory(bot);
        this.enemyScannigSystem = new FirstLastEnemyScanningSystem(bot);
    }

    @Override
    public void scan() {
        this.enemyScannigSystem.onPreScan();
        super.scan();
        this.enemyScannigSystem.onPosScan();
    }

    /*public void onPreScan() {
        System.out.println("onPreScan() ---");
        System.out.println("isScan:" + this.enemyScannigSystem.isScan());
        System.out.println("search:" + this.enemyScannigSystem.search);
        System.out.println("target:" + this.enemyScannigSystem.target);
        System.out.println("first:" + this.enemyScannigSystem.first);
        System.out.println("first:" + this.enemyScannigSystem.last);
    }

    public void onPosScan() {
        final long time = bot.getTime();
        //if(time % 8 == 0) {

        final Vector<Enemy> enemies = new Vector<Enemy>(knownEnemies());
        for(Enemy enemy: enemies) {
            final List<ScannedEnemyDefault> history = getEnemyHistory().dataFor(enemy);

            if(isCalcDirection(history)) {
                calcDirection(history);
            }
        }


        System.out.println("onPosScan() ---");


        if(!this.enemyScannigSystem.isScan() && !enemies.isEmpty()) {
            final Enemy first = enemies.firstElement();
            final Enemy last = enemies.lastElement();
            if(!first.name().equals(last.name())) {
                this.enemyScannigSystem.search = true;

                if(this.enemyScannigSystem.target == null) {
                    this.enemyScannigSystem.target = first;
                    this.enemyScannigSystem.first = first;
                    this.enemyScannigSystem.last = last;
                } else if(this.enemyScannigSystem.target.name().equals(first.name())) {
                    this.enemyScannigSystem.target = last;
                    this.enemyScannigSystem.direction =
                            this.enemyScannigSystem.direction.inverse();
                } else {
                    this.enemyScannigSystem.target = first;
                    this.enemyScannigSystem.direction =
                            this.enemyScannigSystem.direction.inverse();
                }
            }
        }

        //}
    }*/

    private Boolean isCalcDirection(final List<ScannedEnemyDefault> history) {
        final ScannedEnemyDefault lastHistory = history.get(history.size()-1);
        return history.size() > 1 && lastHistory.getDirection() == null;
    }

    private Direction calcDirection(final List<ScannedEnemyDefault> history) {
        final ScannedEnemyDefault lastHistory = history.get(history.size()-1);
        final ScannedEnemyDefault lastHistory1 = history.get(history.size()-2);
        final Vector2D u = lastHistory.getVector2D();
        final Vector2D v = lastHistory1.getVector2D();
        final Direction direction = Direction.getDirectionSouth(u, v);
        lastHistory.setDirection(direction);
        return direction;
    }

    public EnemyHistory getEnemyHistory() {
        return enemyHistory;
    }

    public EnemyScannigSystem getEnemyScannigSystem() {
        return enemyScannigSystem;
    }

    /*public class EnemyScannigSystem implements ScanningSystem {
        private boolean search;

        private Enemy target;
        private Enemy first;
        private Enemy last;

        private Angle direction = Angle.PI_OVER_FOUR;
        public void execute() {
            bot.radar().turn(direction);
        }

        public boolean isScan() {
            if(!this.search) {
                return false;
            }

            return true;
        }

        public boolean isSearch() {
            return this.search;
        }
    }

    @When(ENEMY_SCANNED)
    public void onEnemyScannedSearch(EnemyScannedEvent event) {
        final Enemy enemy = event.enemy();
        bot.log("Enemy spotted at %s", enemy.position());

        if(!this.enemyScannigSystem.isSearch()) {
            return;
        }

        if(enemy.name().equals(this.enemyScannigSystem.target.name())) {
            this.enemyScannigSystem.search = false;
        }
    }*/
}