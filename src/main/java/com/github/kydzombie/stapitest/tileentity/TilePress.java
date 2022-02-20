package com.github.kydzombie.stapitest.tileentity;

import com.github.kydzombie.stapitest.events.init.ItemListener;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.util.io.CompoundTag;

public class TilePress extends TileMachine {
    private final int COOK_TIME = 120;
    public int cookTime = 0;

    private int powerUsage = 4;

    public TilePress() {
        super(1800, 2);
        this.containerName = "Press";
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
        if (this.canAcceptRecipeOutput()) {
            ItemInstance output = getOutput(inventory[0].getType().id);
            if (output != null) {
                if (inventory[1] == null) {
                    inventory[1] = output.copy();
                } else if (this.inventory[1].itemId == output.itemId) {
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
        if (itemId == ItemBase.ironIngot.id) {
            return new ItemInstance(ItemListener.ironPlate);
        }
        else if (itemId == ItemBase.goldIngot.id) {
            return new ItemInstance(ItemListener.goldPlate);
        }
        else if (itemId == ItemListener.ironPlate.id) {
            return new ItemInstance(ItemListener.ironGear);
        }
        else if (itemId == ItemListener.goldPlate.id) {
            return new ItemInstance(ItemListener.goldGear);
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
