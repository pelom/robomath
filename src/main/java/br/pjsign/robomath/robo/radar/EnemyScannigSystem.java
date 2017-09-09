package br.pjsign.robomath.robo.radar;

import tools.devnull.robobundle.parts.ScanningSystem;

public interface EnemyScannigSystem extends ScanningSystem {
    void onPreScan();
    void onPosScan();
}
