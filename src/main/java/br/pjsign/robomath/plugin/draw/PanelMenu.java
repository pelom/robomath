package br.pjsign.robomath.plugin.draw;

import java.awt.event.KeyEvent;
import java.util.List;

public interface PanelMenu {

    Boolean isShow();
    void activeCommand(KeyEvent event);

    void writePanel(List<String> builders);

}