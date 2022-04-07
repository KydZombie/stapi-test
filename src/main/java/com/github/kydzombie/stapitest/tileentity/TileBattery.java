package com.github.kydzombie.stapitest.tileentity;

import com.github.kydzombie.stapitest.item.Battery;
import com.github.kydzombie.stapitest.custom.util.machine.power.ItemPowerStorage;
import com.github.kydzombie.stapitest.custom.util.machine.power.PowerUtils;

public class TileBattery extends TileMachine {

    private static final int OUTPUT_AMOUNT = 30;
    private static final int MAX_CHARGE = 5;

    public TileBattery() {
        super("Battery");
        setMaxPower(3200);
    }

    // TODO make stacked batteries work

    @Override
    public void tick() {
        super.tick();

        if (inventory[0] != null) {
            if (inventory[0].getType() instanceof Battery) {
                power += PowerUtils.attemptConsumeItemPower(inventory[0], Math.min(MAX_CHARGE, maxPower - power));
            }
        }

        if (power > 0) {
            if (inventory[1] != null && inventory[1].getType() instanceof ItemPowerStorage) {
                power -= PowerUtils.attemptChargeItem(inventory[1], power, OUTPUT_AMOUNT);
            } else {
                power -= PowerUtils.sendPowerToConnections(connectedMachines, power, OUTPUT_AMOUNT);
            }
        }
    }
}
