package com.github.kydzombie.stapitest.tileentity;

public class TileElectricFurnace extends TileEntityMachine {

    public TileElectricFurnace() {
        super(300);
    }

    @Override
    public int charge(int chargeAmount, int side, boolean simulate) {
        chargeAmount = Math.min(chargeAmount, maxPower - power);
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
