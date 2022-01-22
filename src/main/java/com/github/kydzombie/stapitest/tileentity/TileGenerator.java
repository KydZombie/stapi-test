package com.github.kydzombie.stapitest.tileentity;

import com.github.kydzombie.stapitest.item.Battery;
import com.github.kydzombie.stapitest.util.machine.power.ItemPowerStorage;
import com.github.kydzombie.stapitest.util.machine.power.PowerUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.util.io.CompoundTag;

public class TileGenerator extends TileMachine {

    private static final int GENERATION_RATE = 2;
    private static final int OUTPUT_AMOUNT = 30;
    private static final int MAX_CHARGE = 5;

    private int fuelTime = 0;
    private int burnTime = 0;

    public TileGenerator() {
        super(1600, 2);
        this.containerName = "Generator";
    }

    @Override
    public void tick() {
        super.tick();

        if (burnTime > 0) {
            power = Math.min(power + GENERATION_RATE, maxPower);
            burnTime--;
        }
        else if (inventory[0] != null){

            if (getFuelTime(inventory[0]) > 0 && power != maxPower) {
                fuelTime = burnTime = getFuelTime(inventory[0]);
                takeInventoryItem(0, 1);
            }
            else if (inventory[0].getType() instanceof Battery) {
                power += PowerUtils.attemptConsumeItemPower(inventory[0], Math.min(MAX_CHARGE, maxPower - power));
            }
        }

        if (power > 0) {
            if (inventory[1] != null && inventory[1].getType() instanceof ItemPowerStorage) {
                power -= PowerUtils.attemptChargeItem(inventory[1], power, OUTPUT_AMOUNT);
            }
            else {
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
        return this.burnTime > 0;
    }

    public static int getFuelTime(ItemInstance stack) {
        if (stack == null) {
            return 0;
        } else {
            int var2 = stack.getType().id;
            if (var2 < 256 && BlockBase.BY_ID[var2].material == Material.WOOD) {
                return 75;
            } else if (var2 == ItemBase.stick.id) {
                return 25;
            } else if (var2 == ItemBase.coal.id) {
                return 400;
            } else if (var2 == ItemBase.lavaBucket.id) {
                return 5000;
            } else {
                return var2 == BlockBase.SAPLING.id ? 25 : 0;
            }
        }
    }

    @Environment(EnvType.CLIENT)
    public int getFuelTimeDelta(int multiplier) {
        if (this.fuelTime == 0) {
            this.fuelTime = 200;
        }

        return this.burnTime * multiplier / this.fuelTime;
    }

    @Override
    public int charge(int chargeAmount, int side, boolean simulate) {
        return 0;
    }
}
