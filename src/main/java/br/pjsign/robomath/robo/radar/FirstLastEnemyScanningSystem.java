package br.pjsign.robomath.robo.radar;

import robocode.RobotDeathEvent;
import tools.devnull.robobundle.BaseBot;
import tools.devnull.robobundle.Enemy;
import tools.devnull.robobundle.annotation.When;
import tools.devnull.robobundle.calc.Angle;
import tools.devnull.robobundle.event.EnemyScannedEvent;

import java.util.*;

import static tools.devnull.robobundle.event.Events.ENEMY_SCANNED;
import static tools.devnull.robobundle.event.Events.ROBOT_DEATH;

public class FirstLastEnemyScanningSystem implements EnemyScannigSystem {

    private static final int FIND_TARGET_MAX = 8;
    private int rotate;
    private boolean activeFindTarget;
    private int findTargetCount;
    private Enemy target;
    private ChooseFirstLast chooseFirstLast;

    private Angle direction;
    private BaseBot bot;
    private Map<String, Enemy> enemyScannedEnemyDefaultMap;

    public FirstLastEnemyScanningSystem(BaseBot bot) {
        this.bot = bot;

        this.rotate = 0;
        this.findTargetCount = 0;
        this.direction = Angle.PI_OVER_FOUR;
        this.enemyScannedEnemyDefaultMap = new HashMap<String, Enemy>();
    }

    @Override
    public void execute() {
        bot.radar().turn(direction);
    }

    @When(ENEMY_SCANNED)
    public void onEnemyScannedSearch(EnemyScannedEvent event) {
        this.enemyScannedEnemyDefaultMap.put(event.enemy().name(), event.enemy());

        if(!this.isActiveFindTarget()) {
            return;
        }

        if(isEqualsEnemy(this.target, event.enemy()) || isFindTargetMax()) {
            setActiveFindTarget(false);
            //    this.activeFindTarget = false;
        }
    }

    @When(ROBOT_DEATH)
    public void onRobotDeath(RobotDeathEvent event) {
        defineTarget(null, false);
    }

    @Override
    public void onPreScan() {
        this.bot.log("onPreScan() %s", this.toString());
    }

    @Override
    public void onPosScan() {
        this.rotate++;
        this.bot.log("onPosScan() %s", this.toString());
        System.out.println("Rot: " + (rotate * Angle.PI_OVER_FOUR.radians()));

        if (isActiveFindTarget()) {
            if(this.rotate > 6) {
                defineTarget(null, false);
            }
            return;
        }

        findTarget();



//        if(isActiveFindTarget()) {
//            ScannedEnemyDefault v = (ScannedEnemyDefault) this.chooseFirstLast.getFirstTarget();
//            ScannedEnemyDefault u = (ScannedEnemyDefault) this.chooseFirstLast.getLastTarget();
//
//            final Vector2D center = new Vector2D((float)bot.location().x(), (float)bot.location().y());
//            final Vector2D a = v.getVector2D().minusV(center).unit();
//            final Vector2D b = u.getVector2D().minusV(center).unit();
//            System.out.println("Angle: " + a.getAngle(b));
////            final float ang = Math.abs(a.getAngle(b).radians());
////
////            if(this.findTargetCicleCount % FIND_TARGET_MAX == 0
////                    && ang > Angle.TWO_PI.radians()) {
////                defineTarget(null, false);
////            }
//        }

    }

    public boolean isActiveFindTarget() {
        return this.activeFindTarget;
    }

    private boolean isFindTargetMax() {
        return this.findTargetCount >= FIND_TARGET_MAX;
    }

    private void findTarget() {
        if(isActiveFindTarget()) {
            this.findTargetCount++;

            if(isFindTargetMax()) {
                this.direction = direction.inverse();
            }
            return;
        }

        final Vector<Enemy> enemyFind = new Vector<Enemy>(this.enemyScannedEnemyDefaultMap.values());

        this.bot.log("activeFindTarget() %s", enemyFind.size());

        if(!isAllFindEnemy(enemyFind)) {
            return;
        }

        this.chooseFirstLast = new ChooseFirstLast(enemyFind);

        this.bot.log("chooseFirstLast: %s", this.chooseFirstLast);

        final Enemy first = chooseFirstLast.getFirstTarget();
        final Enemy last = chooseFirstLast.getLastTarget();

        if(isEqualsEnemy(first, last) && bot.getOthers() != 1) {
            return;
        }

        if(isNotExistTarget()) {
            defineTarget(last, true);
        } else if(isEqualsEnemy(this.target, last)) {
            defineTarget(first, true);
        } else {
            defineTarget(last, true);
        }
    }

    private boolean isAllFindEnemy(final Collection<Enemy> enemyList) {
        return enemyList.size() == this.bot.getOthers();
    }

    private boolean isEqualsEnemy(Enemy enemy, Enemy other) {
        return enemy.name().equals(other.name());
    }

    private boolean isNotExistTarget() {
        return this.target == null;
    }

    private void defineTarget(Enemy target, boolean activeFindTarget) {
        this.target = target;
        setActiveFindTarget(activeFindTarget);
        this.findTargetCount = 0;
        this.rotate = 0;
        this.direction = this.direction.inverse();
        this.enemyScannedEnemyDefaultMap.clear();
    }

    private void setActiveFindTarget(boolean activeFindTarget) {
        this.activeFindTarget = activeFindTarget;
    }

    @Override
    public String toString() {
        return "FirstLastEnemyScanningSystem{" +
                "activeFindTarget=" + activeFindTarget +
                ", findTargetCount=" + findTargetCount +
                ", target=" + (target !=null ? target.name() : null) +
                ", direction=" + direction.radians() +
                ", chooseFirstLast=" + chooseFirstLast +
                '}';
    }

    private class ChooseFirstLast implements Comparator<Enemy> {
        private Enemy lastTarget;
        private Enemy firstTarget;

        public ChooseFirstLast(Vector<Enemy> enemyFind) {
            Collections.sort(enemyFind, this);
            this.firstTarget = enemyFind.firstElement();
            this.lastTarget = enemyFind.lastElement();
        }

        @Override
        public int compare(Enemy o1, Enemy o2) {
            if(o1.when() < o2.when()) { return -1; }
            if(o1.when() > o2.when()) { return 1; }
            return 0;
        }

        public Enemy getLastTarget() {
            return lastTarget;
        }

        public Enemy getFirstTarget() {
            return firstTarget;
        }

        @Override
        public String toString() {
            return "ChooseFirstLast{" +
                    "lastTarget=" + lastTarget.name() +
                    ", firstTarget=" + firstTarget.name() +
                    '}';
        }
    }
}