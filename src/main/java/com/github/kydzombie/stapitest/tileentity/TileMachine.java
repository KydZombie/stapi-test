package com.github.kydzombie.stapitest.tileentity;

import net.minecraft.inventory.InventoryBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.io.ListTag;

public abstract class TileMachine extends TilePowered implements InventoryBase {

    private static final int DEFAULT_INV_SIZE = 2;

    ItemInstance[] inventory;
    String containerName;

    public TileMachine(String containerName) {
        super();
        inventory = new ItemInstance[DEFAULT_INV_SIZE];
        this.containerName = containerName;
    }

    public TileMachine setInventorySize(int inventorySize) {
        inventory = new ItemInstance[inventorySize];
        return this;
    }

    @Override
    public int getInventorySize() {
        return inventory.length;
    }

    @Override
    public ItemInstance getInventoryItem(int index) {
        return inventory[index];
    }

    @Override
    public ItemInstance takeInventoryItem(int index, int count) {
        if (inventory[index] != null) {
            ItemInstance var3;
            if (inventory[index].count <= count) {
                var3 = inventory[index];
                inventory[index] = null;
            } else {
                var3 = inventory[index].split(count);
                if (inventory[index].count == 0) {
                    inventory[index] = null;
                }
            }
            return var3;
        } else {
            return null;
        }
    }

    @Override
    public void setInventoryItem(int slot, ItemInstance stack) {
        inventory[slot] = stack;
        if (stack != null && stack.count > getMaxItemCount()) {
            stack.count = getMaxItemCount();
        }
    }

    @Override
    public String getContainerName() {
        return containerName;
    }

    @Override
    public int getMaxItemCount() {
        return 64;
    }

    @Override
    public void readIdentifyingData(CompoundTag tag) {
        super.readIdentifyingData(tag);

        ListTag var2 = tag.getListTag("Items");
        inventory = new ItemInstance[getInventorySize()];
        for (int var3 = 0; var3 < var2.size(); ++var3) {
            CompoundTag var4 = (CompoundTag) var2.get(var3);
            byte var5 = var4.getByte("Slot");
            if (var5 >= 0 && var5 < inventory.length) {
                inventory[var5] = new ItemInstance(var4);
            }
        }
    }

    @Override
    public void writeIdentifyingData(CompoundTag tag) {
        super.writeIdentifyingData(tag);

        ListTag var2 = new ListTag();
        for (int var3 = 0; var3 < inventory.length; ++var3) {
            if (inventory[var3] != null) {
                CompoundTag var4 = new CompoundTag();
                var4.put("Slot", (byte) var3);
                inventory[var3].toTag(var4);
                var2.add(var4);
            }
        }

        tag.put("Items", var2);
    }
}
