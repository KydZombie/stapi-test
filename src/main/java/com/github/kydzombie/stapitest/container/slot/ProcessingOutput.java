package com.github.kydzombie.stapitest.container.slot;

import net.minecraft.container.slot.Slot;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.item.ItemInstance;

public class ProcessingOutput extends Slot {
    public ProcessingOutput(PlayerBase arg, InventoryBase arg1, int i, int j, int k) {
        super(arg1, i, j, k);
    }

    public boolean canInsert(ItemInstance stack) {
        return false;
    }
}
