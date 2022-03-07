package com.github.kydzombie.stapitest.tileentity;

import com.github.kydzombie.stapitest.events.init.StapiTest;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.util.io.CompoundTag;

public class TileGrinder extends TileMachine {
    private final int COOK_TIME = 80;
    public int cookTime = 0;

    private int powerUsage = 2;

    public TileGrinder() {
        super(1200, 2);
        containerName = "Grinder";
    }

    @Override
    public void tick() {
        super.tick();

        if (!level.isServerSide) {

            if (canAcceptRecipeOutput()) {
                if (power >= powerUsage) {
                    ++cookTime;
                    power -= powerUsage;
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
            ItemInstance output = getOutput(inventory[0].getType().id);
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

    public void craftRecipe() {
        if (canAcceptRecipeOutput()) {
            ItemInstance output = getOutput(inventory[0].getType().id);
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

    private ItemInstance getOutput(int itemId) {
        if (itemId == BlockBase.IRON_ORE.id || itemId == ItemBase.ironIngot.id) {
            return new ItemInstance(StapiTest.ironDust, 2);
        }
        else if (itemId == BlockBase.GOLD_ORE.id || itemId == ItemBase.goldIngot.id) {
            return new ItemInstance(StapiTest.goldDust, 2);
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
