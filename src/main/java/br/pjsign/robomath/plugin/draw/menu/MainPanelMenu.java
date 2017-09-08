package br.pjsign.robomath.plugin.draw.menu;

import br.pjsign.robomath.Point2D;
import br.pjsign.robomath.plugin.draw.PanelMenu;
import br.pjsign.robomath.plugin.draw.menu.PanelMenuDefault;
import tools.devnull.robobundle.Bot;
import tools.devnull.robobundle.annotation.When;
import tools.devnull.robobundle.calc.Point;
import tools.devnull.robobundle.util.Drawer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.awt.Color.GREEN;
import static tools.devnull.robobundle.event.Events.DRAW;

public class MainPanelMenu extends PanelMenuDefault {
    final Font FONT = new Font("Dialog", Font.PLAIN, 12);
    private static final int LINE_LEN   = 20;
    private static final int PADDING    = 4;
    private static final int WIDTH      = 300;

    private List<PanelMenu> panelMenuList;
    private Point2D point2D;

    public MainPanelMenu(Bot bot, Point2D point2D) {
        super(bot);
        this.point2D = point2D;
        this.panelMenuList = new LinkedList<PanelMenu>();
    }

    public void addPanelMenu(PanelMenu panelMenu) {
        this.panelMenuList.add(panelMenu);
    }

    @When(DRAW)
    public void paint(final Drawer drawer) {

        final Point start = new Point(point2D.getX(), point2D.getY());
        int i = 1;

        for (PanelMenu menu: panelMenuList) {
            if(menu.isShow()) {
                final List<String> builders = new ArrayList<String>();
                menu.writePanel(builders);
                for (String st: builders) {
                    drawer.graphics().setColor(Color.DARK_GRAY);
                    drawer.graphics().fillRect(
                            (int) start.down(LINE_LEN * i).x() - PADDING,
                            (int) start.down(LINE_LEN * i).y() - PADDING, WIDTH, LINE_LEN);
                    drawer.graphics().setFont(FONT);
                    drawer.graphics().setRenderingHint(
                            RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    drawer.draw(Drawer.Mode.TRANSPARENT, GREEN)
                            .string("%s", st).at(start.down(LINE_LEN * i));
                    i++;
                }
            }
            i++;
        }
    }

    @Override
    public void activeCommand(KeyEvent event) {
        for (PanelMenu menu: panelMenuList) {
            menu.activeCommand(event);
        }
    }

    @Override
    public void writePanel(List<String> builds) { }
}