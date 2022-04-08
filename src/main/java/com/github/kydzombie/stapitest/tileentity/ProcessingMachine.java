package com.github.kydzombie.stapitest.tileentity;

import com.github.kydzombie.stapitest.recipe.ProcessingRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemInstance;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.item.nbt.StationNBT;

public abstract class ProcessingMachine extends TileMachine {

    private static final int DEFAULT_COOK_TIME = 80;
    private static final int DEFAULT_POWER_USAGE = 2;

    int totalCookTime;
    int powerUsage;
    public int cookTime = 0;

    private ProcessingRegistry recipeRegistry;

    public ProcessingMachine(String containerName) {
        super(containerName);
        this.totalCookTime = DEFAULT_COOK_TIME;
        this.powerUsage = DEFAULT_POWER_USAGE;
    }

    public ProcessingMachine setRecipeHandler(ProcessingRegistry newRegistry) {
        recipeRegistry = newRegistry;
        return this;
    }

    public ProcessingMachine setTotalCookTime(int totalCookTime) {
        this.totalCookTime = totalCookTime;
        return this;
    }

    public ProcessingMachine setPowerUsage(int powerUsage) {
        this.powerUsage = powerUsage;
        return this;
    }

    @Environment(EnvType.CLIENT)
    public int getCookTimeDelta(int multiplier) {
        return cookTime * multiplier / totalCookTime;
    }

    @Override
    public void tick() {
        super.tick();

        if (!level.isServerSide) {

            if (canAcceptRecipeOutput()) {
                if (power >= powerUsage) {
                    ++cookTime;
                    power -= powerUsage;
                    if (cookTime == totalCookTime) {
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
        if (recipeRegistry != null) {
            return recipeRegistry.getOutput(input);
        }
        System.out.println("Unimplemented recipe registry from a " + this.containerName + ", returning null.");
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
