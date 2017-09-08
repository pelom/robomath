package br.pjsign.robomath.plugin.draw.menu;

import br.pjsign.robomath.plugin.draw.PanelMenu;
import tools.devnull.robobundle.Bot;

import java.util.List;

public abstract class PanelMenuDefault implements PanelMenu {

    private static final String LINE = "---------- ---------- ----------";

    private final Bot bot;
    private boolean show;

    public PanelMenuDefault(Bot bot) {
        this.bot = bot;
        this.show = false;
    }

    public Bot getBot() {
        return bot;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    @Override
    public Boolean isShow() {
        return show;
    }

    protected void writeTitle(String title, boolean line, List<String> builders) {
        builders.add(title.toUpperCase());
        if(line) { builders.add(LINE); }
    }

    public String fValue(double floatValue) {
        return String.format("%.2f", floatValue);
    }
}
