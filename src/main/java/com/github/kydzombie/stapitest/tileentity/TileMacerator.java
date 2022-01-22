package com.github.kydzombie.stapitest.tileentity;

import com.github.kydzombie.stapitest.events.init.ItemListener;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.recipe.SmeltingRecipeRegistry;
import net.minecraft.util.io.CompoundTag;

public class TileMacerator extends TileMachineWithStorage {
    private final int COOK_TIME = 80;
    public int cookTime = 0;

    public TileMacerator() {
        super(1200, 2);
        this.containerName = "Macerator";
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level.isClient) {

            if (this.canAcceptRecipeOutput()) {
                if (power >= 2) {
                    ++this.cookTime;
                    power -= 2;
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
            ItemInstance var1 = getOutput(inventory[0].getType().id);
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
        if (this.canAcceptRecipeOutput()) {
            ItemInstance output = getOutput(inventory[0].getType().id);
            if (output != null) {
                if (this.inventory[1] == null) {
                    this.inventory[1] = output.copy();
                } else if (this.inventory[1].itemId == output.itemId) {
                    ++this.inventory[1].count;
                }

                --this.inventory[0].count;
                if (this.inventory[0].count <= 0) {
                    this.inventory[0] = null;
                }
            }
        }
    }

    private ItemInstance getOutput(int itemId) {
        if (itemId == BlockBase.IRON_ORE.id || itemId == ItemBase.ironIngot.id) {
            return new ItemInstance(ItemListener.ironDust, 2);
        }
        else if (itemId == BlockBase.GOLD_ORE.id || itemId == ItemBase.goldIngot.id) {
            return new ItemInstance(ItemListener.goldDust, 2);
        }
        return null;
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
