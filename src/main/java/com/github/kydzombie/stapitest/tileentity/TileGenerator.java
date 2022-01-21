package com.github.kydzombie.stapitest.tileentity;

import com.github.kydzombie.stapitest.util.machine.power.PowerUtils;

public class TileGenerator extends TileEntityMachine {

    private final int TICK_TIMER = 10;
    private int tick_timer = TICK_TIMER;

    public TileGenerator() {
        super(80, 80);
    }

    @Override
    public void tick() {
        super.tick();
        if (tick_timer > 0) {
            tick_timer--;
            return;
        }
        else {
            tick_timer = TICK_TIMER;
        }

        power -= PowerUtils.sendPowerToConnections(connectedMachines, power, 5);
    }

    @Override
    public int charge(int chargeAmount, int side, boolean simulate) {
        return 0;
    }

    @Override
    public int consume(int consumeAmount, int side, boolean simulate) {
        return 0;
    }
}
