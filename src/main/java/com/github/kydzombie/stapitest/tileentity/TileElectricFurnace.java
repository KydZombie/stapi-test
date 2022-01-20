package com.github.kydzombie.stapitest.tileentity;

import com.github.kydzombie.stapitest.util.power.PowerStorage;
import net.minecraft.tileentity.TileEntityBase;

public class TileElectricFurnace extends TileEntityBase implements PowerStorage {

    public final int MAX_POWER = 200;
    public int power = 0;

    @Override
    public int getCurrentPower() {
        return power;
    }

    @Override
    public int getMaxPower() {
        return MAX_POWER;
    }

    @Override
    public int charge(int chargeAmount, int side, boolean simulate) {
        chargeAmount = Math.min(chargeAmount, MAX_POWER - power);
        if (!simulate) {
            power += chargeAmount;
            System.out.println("Furnace Power: " + power);
        }
        return (chargeAmount);
    }

    @Override
    public int consume(int consumeAmount, int side, boolean simulate) {
        return 0;
    }
}
