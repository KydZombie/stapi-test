package com.github.kydzombie.stapitest.tileentity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.recipe.SmeltingRecipeRegistry;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.io.ListTag;

public class TileElectricFurnace extends TileEntityMachine implements InventoryBase {

    private ItemInstance[] inventory = new ItemInstance[2];

    private final int COOK_TIME = 30;
    public int cookTime = 0;

    public TileElectricFurnace() {
        super(800);
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

    @Override
    public int getInventorySize() {
        return this.inventory.length;
    }

    @Override
    public ItemInstance getInventoryItem(int index) {
        return this.inventory[index];
    }

    @Override
    public ItemInstance takeInventoryItem(int index, int count) {
        if (this.inventory[index] != null) {
            ItemInstance var3;
            if (this.inventory[index].count <= count) {
                var3 = this.inventory[index];
                this.inventory[index] = null;
                return var3;
            } else {
                var3 = this.inventory[index].split(count);
                if (this.inventory[index].count == 0) {
                    this.inventory[index] = null;
                }

                return var3;
            }
        } else {
            return null;
        }
    }

    @Override
    public void setInventoryItem(int slot, ItemInstance stack) {
        this.inventory[slot] = stack;
        if (stack != null && stack.count > this.getMaxItemCount()) {
            stack.count = this.getMaxItemCount();
        }
    }

    @Override
    public String getContainerName() {
        return "ElectricFurnace";
    }

    @Override
    public int getMaxItemCount() {
        return 64;
    }

    @Override
    public boolean canPlayerUse(PlayerBase player) {
        if (this.level.getTileEntity(this.x, this.y, this.z) != this) {
            return false;
        } else {
            return !(player.squaredDistanceTo((double)this.x + 0.5D, (double)this.y + 0.5D, (double)this.z + 0.5D) > 64.0D);
        }
    }

    @Environment(EnvType.CLIENT)
    public int getCookTimeDelta(int multiplier) {
        return this.cookTime * multiplier / COOK_TIME;
    }

    private boolean canAcceptRecipeOutput() {
        if (this.inventory[0] == null) {
            return false;
        } else {
            ItemInstance var1 = SmeltingRecipeRegistry.getInstance().getResult(this.inventory[0].getType().id);
            if (var1 == null) {
                return false;
            } else if (this.inventory[1] == null) {
                return true;
            } else if (!this.inventory[1].isDamageAndIDIdentical(var1)) {
                return false;
            } else if (this.inventory[1].count < this.getMaxItemCount() && this.inventory[1].count < this.inventory[1].getMaxStackSize()) {
                return true;
            } else {
                return this.inventory[1].count < var1.getMaxStackSize();
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

        ListTag var2 = tag.getListTag("Items");
        this.inventory = new ItemInstance[this.getInventorySize()];

        for(int var3 = 0; var3 < var2.size(); ++var3) {
            CompoundTag var4 = (CompoundTag)var2.get(var3);
            byte var5 = var4.getByte("Slot");
            if (var5 >= 0 && var5 < this.inventory.length) {
                this.inventory[var5] = new ItemInstance(var4);
            }
        }

    }

    @Override
    public void writeIdentifyingData(CompoundTag tag) {
        super.writeIdentifyingData(tag);

        tag.put("cookTime", (short)cookTime);

        ListTag var2 = new ListTag();

        for(int var3 = 0; var3 < this.inventory.length; ++var3) {
            if (this.inventory[var3] != null) {
                CompoundTag var4 = new CompoundTag();
                var4.put("Slot", (byte)var3);
                this.inventory[var3].toTag(var4);
                var2.add(var4);
            }
        }

        tag.put("Items", var2);
    }
}
