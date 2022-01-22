package com.github.kydzombie.stapitest.tileentity;

import com.github.kydzombie.stapitest.util.machine.power.PowerUtils;
import net.minecraft.util.io.CompoundTag;

public class TileGenerator extends TileEntityMachine {

    private final int TICK_TIMER = 3;
    private int tick_timer = TICK_TIMER;

    private final int GENERATION_RATE = 2;

    private final int OUTPUT_AMOUNT = 30;

    private int fuelTime = 0;

    public TileGenerator() {
        super(1600, 0);
    }

    @Override
    public void tick() {
        super.tick();

        if (fuelTime > 0) {
            power += GENERATION_RATE;
            fuelTime--;
        }

        if (power > 0) {
            power -= PowerUtils.sendPowerToConnections(connectedMachines, power, OUTPUT_AMOUNT);
        }
    }

    public void insertFuel() {
        fuelTime += 400;
        System.out.println("Added 400");
//        fuelTime += ((Fuel)fuel.getType()).getFuelTime(fuel) / 4;
//        System.out.println("Added " + ((Fuel)fuel.getType()).getFuelTime(fuel) / 4);
    }

    @Override
    public void readIdentifyingData(CompoundTag tag) {
        super.readIdentifyingData(tag);
        fuelTime = tag.getInt("fuelTime");
    }

    @Override
    public void writeIdentifyingData(CompoundTag tag) {
        super.writeIdentifyingData(tag);
        tag.put("fuelTime", fuelTime);
    }

    @Override
    public int charge(int chargeAmount, int side, boolean simulate) {
        return 0;
    }
}
