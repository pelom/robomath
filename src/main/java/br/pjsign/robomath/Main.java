package br.pjsign.robomath;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Main {

    public static void main(String[] args) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontFamilies = ge.getAvailableFontFamilyNames();
        for(String font: fontFamilies) {
            System.out.println(font);
        }

        System.out.println(Integer.valueOf(KeyEvent.getKeyText(KeyEvent.VK_0)));
    }
}
