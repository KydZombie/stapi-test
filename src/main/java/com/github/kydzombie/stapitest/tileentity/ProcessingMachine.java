package com.github.kydzombie.stapitest.tileentity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemInstance;
import net.minecraft.util.io.CompoundTag;

public abstract class ProcessingMachine extends TileMachine {

    final int COOK_TIME;
    final int POWER_USAGE;
    public int cookTime = 0;

    public ProcessingMachine(int maxPower, int inventorySize, String containerName, int cookTime, int powerUsage) {
        super(maxPower, inventorySize, containerName);
        this.COOK_TIME = cookTime;
        this.POWER_USAGE = powerUsage;
    }

    public ProcessingMachine(int maxPower, String containerName, int cookTime, int powerUsage) {
        super(maxPower, 2, containerName);
        this.COOK_TIME = cookTime;
        this.POWER_USAGE = powerUsage;
    }

    public ProcessingMachine(int maxPower, int inventorySize, String containerName) {
        super(maxPower, inventorySize, containerName);
        this.COOK_TIME = 80;
        this.POWER_USAGE = 2;
    }

    public ProcessingMachine(int maxPower, String containerName) {
        super(maxPower, 2, containerName);
        this.COOK_TIME = 80;
        this.POWER_USAGE = 2;
    }

    @Environment(EnvType.CLIENT)
    public int getCookTimeDelta(int multiplier) {
        return cookTime * multiplier / COOK_TIME;
    }

    @Override
    public void tick() {
        super.tick();

        if (!level.isServerSide) {

            if (canAcceptRecipeOutput()) {
                if (power >= POWER_USAGE) {
                    ++cookTime;
                    power -= POWER_USAGE;
                    if (cookTime == COOK_TIME) {
                        cookTime = 0;
                        craftRecipe();
                    }
                }
            } else {
                cookTime = 0;
            }
        }
    }

    public boolean canAcceptRecipeOutput() {
        if (inventory[0] == null) {
            return false;
        } else {
            ItemInstance output = getOutput(inventory[0]);
            if (output == null) {
                return false;
            } else if (inventory[1] == null) {
                return true;
            } else if (!inventory[1].isDamageAndIDIdentical(output)) {
                return false;
            } else if (inventory[1].count + output.count <= getMaxItemCount() && inventory[1].count + output.count <= inventory[1].getMaxStackSize()) {
                return true;
            } else {
                return inventory[1].count + output.count <= output.getMaxStackSize();
            }
        }
    }

    public ItemInstance getOutput(ItemInstance input) {
        return null;
    }

    public void craftRecipe() {
        if (canAcceptRecipeOutput()) {
            ItemInstance output = getOutput(inventory[0]);
            if (output != null) {
                if (inventory[1] == null) {
                    inventory[1] = output.copy();
                } else if (inventory[1].itemId == output.itemId) {
                    inventory[1].count += output.count;
                }

                --inventory[0].count;
                if (inventory[0].count <= 0) {
                    inventory[0] = null;
                }
            }
        }
    }

    @Override
    public void readIdentifyingData(CompoundTag tag) {
        super.readIdentifyingData(tag);
        cookTime = tag.getShort("cookTime");
    }

    @Override
    public void writeIdentifyingData(CompoundTag tag) {
        super.writeIdentifyingData(tag);
        tag.put("cookTime", (short) cookTime);
    }
}
