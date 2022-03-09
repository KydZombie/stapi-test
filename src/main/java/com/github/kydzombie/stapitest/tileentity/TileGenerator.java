package com.github.kydzombie.stapitest.tileentity;

import com.github.kydzombie.stapitest.item.Battery;
import com.github.kydzombie.stapitest.util.machine.power.ItemPowerStorage;
import com.github.kydzombie.stapitest.util.machine.power.PowerUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.recipe.SmeltingRegistry;

public class TileGenerator extends TileMachine {

    private static final int GENERATION_RATE = 2;
    private static final int OUTPUT_AMOUNT = 30;
    private static final int MAX_CHARGE = 5;

    private int fuelTime = 0;
    private int burnTime = 0;

    public TileGenerator() {
        super("Generator");
        setMaxPower(1600);
    }

    @Override
    public void tick() {
        super.tick();

        if (burnTime > 0) {
            power = Math.min(power + GENERATION_RATE, maxPower);
            burnTime--;
        } else if (inventory[0] != null) {

            if (SmeltingRegistry.getFuelTime(inventory[0]) > 0 && power != maxPower) {
                fuelTime = burnTime = SmeltingRegistry.getFuelTime(inventory[0]);
                takeInventoryItem(0, 1);
            } else if (inventory[0].getType() instanceof Battery) {
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

    @Override
    public void readIdentifyingData(CompoundTag tag) {
        super.readIdentifyingData(tag);
        burnTime = tag.getInt("burnTime");
        fuelTime = tag.getInt("fuelTime");
    }

    @Override
    public void writeIdentifyingData(CompoundTag tag) {
        super.writeIdentifyingData(tag);
        tag.put("burnTime", burnTime);
        tag.put("fuelTime", fuelTime);
    }

    public boolean isBurning() {
        return burnTime > 0;
    }

    @Environment(EnvType.CLIENT)
    public int getFuelTimeDelta(int multiplier) {
        if (fuelTime == 0) {
            fuelTime = 200;
        }

        return burnTime * multiplier / fuelTime;
    }

    @Override
    public int charge(int chargeAmount, int side, boolean simulate) {
        return 0;
    }
}
