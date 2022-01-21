package com.github.kydzombie.stapitest.tileentity;

import com.github.kydzombie.stapitest.util.machine.power.PowerUtils;

public class TileGenerator extends TileEntityMachine {

    private final int TICK_TIMER = 3;
    private int tick_timer = TICK_TIMER;

    private int fuelTime = 0;

    public TileGenerator() {
        super(80, 0);
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

        if (fuelTime > 0) {
            power++;
            fuelTime--;
        }

        if (power >= 5) {
            power -= PowerUtils.sendPowerToConnections(connectedMachines, power, 5);
        }


    }

    public void insertFuel(int fuelId) {
        System.out.println("Inserted fuel");
        fuelTime += 30;
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
