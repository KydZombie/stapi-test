package com.github.kydzombie.stapitest.tileentity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemInstance;
import net.minecraft.recipe.SmeltingRecipeRegistry;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.io.ListTag;

public class TileElectricFurnace extends TileMachineWithStorage {
    private final int COOK_TIME = 30;
    public int cookTime = 0;

    public TileElectricFurnace() {
        super(800, 2);
        this.containerName = "ElectricFurnace";
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level.isClient) {

            if (this.canAcceptRecipeOutput()) {
                if (power > 0) {
                    ++this.cookTime;
                    power--;
                    if (this.cookTime == COOK_TIME) {
                        this.cookTime = 0;
                        this.craftRecipe();
                    }
                }
            } else {
                this.cookTime = 0;
            }
        }
    }

    @Environment(EnvType.CLIENT)
    public int getCookTimeDelta(int multiplier) {
        return this.cookTime * multiplier / COOK_TIME;
    }

    private boolean canAcceptRecipeOutput() {
        if (inventory[0] == null) {
            return false;
        } else {
            ItemInstance var1 = SmeltingRecipeRegistry.getInstance().getResult(this.inventory[0].getType().id);
            if (var1 == null) {
                return false;
            } else if (inventory[1] == null) {
                return true;
            } else if (!inventory[1].isDamageAndIDIdentical(var1)) {
                return false;
            } else if (inventory[1].count < this.getMaxItemCount() && this.inventory[1].count < this.inventory[1].getMaxStackSize()) {
                return true;
            } else {
                return inventory[1].count < var1.getMaxStackSize();
            }
        }
    }

    public void craftRecipe() {
        if (this.canAcceptRecipeOutput()) {
            ItemInstance var1 = SmeltingRecipeRegistry.getInstance().getResult(this.inventory[0].getType().id);
            if (this.inventory[1] == null) {
                this.inventory[1] = var1.copy();
            } else if (this.inventory[1].itemId == var1.itemId) {
                ++this.inventory[1].count;
            }

            --this.inventory[0].count;
            if (this.inventory[0].count <= 0) {
                this.inventory[0] = null;
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
