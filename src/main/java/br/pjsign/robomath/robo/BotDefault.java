package br.pjsign.robomath.robo;

import br.pjsign.robomath.Point2D;
import br.pjsign.robomath.plugin.draw.menu.EnemyPainel;
import br.pjsign.robomath.plugin.draw.menu.MainPanelMenu;
import br.pjsign.robomath.plugin.draw.menu.StatisticsPanelMenu;
import br.pjsign.robomath.robo.radar.RadarBotDefault;
import br.pjsign.robomath.robo.radar.ScannedEnemyDefault;
import robocode.ScannedRobotEvent;
import tools.devnull.robobundle.BaseBot;
import tools.devnull.robobundle.ScannedEnemy;
import tools.devnull.robobundle.parts.Radar;

import java.awt.event.KeyEvent;

public abstract class BotDefault extends BaseBot {

    private RadarBotDefault radarBotDefault;
    private MainPanelMenu mainPanel;

    @Override
    protected void configure() {
        this.mainPanel = new MainPanelMenu(this, new Point2D(-300, (float) getBattleFieldHeight()));
        this.mainPanel.addPanelMenu(new StatisticsPanelMenu(this));
        this.mainPanel.addPanelMenu(new EnemyPainel(this));

        radar().forScanning()
                .use(this.radarBotDefault.getEnemyScannigSystem());

        plug(this.radarBotDefault.getEnemyHistory());
        plug(mainPanel);
    }

    @Override
    protected ScannedEnemy createEnemy(ScannedRobotEvent event) {
        return new ScannedEnemyDefault(this, event);
    }

    @Override
    protected Radar createRadar() {
        this.radarBotDefault = new RadarBotDefault(this);
        return this.radarBotDefault;
    }

    // Called when a key has been pressed
    public void onKeyPressed(KeyEvent e) {
        this.mainPanel.activeCommand(e);
    }
}
