package com.github.kydzombie.stapitest.tileentity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemInstance;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.recipe.SmeltingRegistry;

public class TileElectricFurnace extends TileMachine {
    private final int COOK_TIME = 30;
    public int cookTime = 0;

    public TileElectricFurnace() {
        super(800, 2);
        containerName = "ElectricFurnace";
    }

    @Override
    public void tick() {
        super.tick();

        if (!level.isServerSide) {
            if (canAcceptRecipeOutput()) {
                if (power > 0) {
                    ++cookTime;
                    power--;
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

    @Environment(EnvType.CLIENT)
    public int getCookTimeDelta(int multiplier) {
        return cookTime * multiplier / COOK_TIME;
    }

    private boolean canAcceptRecipeOutput() {
        if (inventory[0] == null) {
            return false;
        } else {
            ItemInstance var1 = SmeltingRegistry.getResultFor(inventory[0]);
            if (var1 == null) {
                return false;
            } else if (inventory[1] == null) {
                return true;
            } else if (!inventory[1].isDamageAndIDIdentical(var1)) {
                return false;
            } else if (inventory[1].count < getMaxItemCount() && inventory[1].count < inventory[1].getMaxStackSize()) {
                return true;
            } else {
                return inventory[1].count < var1.getMaxStackSize();
            }
        }
    }

    public void craftRecipe() {
        if (canAcceptRecipeOutput()) {
            ItemInstance var1 = SmeltingRegistry.getResultFor(inventory[0]);
            if (inventory[1] == null) {
                inventory[1] = var1.copy();
            } else if (inventory[1].itemId == var1.itemId) {
                ++inventory[1].count;
            }

            --inventory[0].count;
            if (inventory[0].count <= 0) {
                inventory[0] = null;
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
        tag.put("cookTime", (short)cookTime);
    }
}
