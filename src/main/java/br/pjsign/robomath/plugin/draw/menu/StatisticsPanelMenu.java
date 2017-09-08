package br.pjsign.robomath.plugin.draw.menu;

import br.pjsign.robomath.plugin.draw.menu.PanelMenuDefault;
import tools.devnull.robobundle.Bot;
import tools.devnull.robobundle.Enemy;
import tools.devnull.robobundle.Statistics;

import java.awt.event.KeyEvent;
import java.util.List;

import static java.awt.event.KeyEvent.VK_I;
import static java.awt.event.KeyEvent.VK_M;

public class StatisticsPanelMenu extends PanelMenuDefault {

    private Boolean showStatisticsEnemy;

    public StatisticsPanelMenu(Bot bot) {
        super(bot);
        this.showStatisticsEnemy = Boolean.FALSE;
    }

    @Override
    public void writePanel(List<String> builders) {
        writePanel(getBot().statistics().overall(), builders);
    }


    @Override
    public void activeCommand(KeyEvent event) {
        switch (event.getKeyCode()) {
            case VK_M:
                this.setShow(!this.isShow());
                break;
            case VK_I:
                this.showStatisticsEnemy = !showStatisticsEnemy;
                break;
        }
    }

    private void writePanel(Statistics statistics, List<String> builders) {
        writeTitle("Statistics", true, builders);
        builders.add(writePanelStatistics(statistics).toString());
        builders.add("");

        if(showStatisticsEnemy) {
            for (Enemy enemy : getBot().radar().knownEnemies()) {
                writePanelEnermyStatistics(enemy, builders);
            }
        }
    }

    private void writePanelEnermyStatistics(Enemy enemy, List<String> builders) {
        writeTitle(">    " + enemy.name(), false, builders);
        builders.add(writePanelStatistics(getBot().statistics().forEnemy(enemy)).toString());
        builders.add("");
    }

    private StringBuilder writePanelStatistics(Statistics statistics) {
        final StringBuilder write = new StringBuilder();
        write.append("Fired: ").append(statistics.fired());
        write.append("  Hit: ").append(statistics.hit());
        write.append("  Taken: ").append(statistics.taken());
        write.append("  Missed: ").append(statistics.missed());
        write.append("  +(%): ").append(statistics.accuracy());
        return write;
    }
}
