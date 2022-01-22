package com.github.kydzombie.stapitest.gui;

import net.minecraft.container.slot.Slot;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.item.ItemInstance;

public class MaceratorOutput extends Slot {
    public MaceratorOutput(PlayerBase arg, InventoryBase arg1, int i, int j, int k) {
        super(arg1, i, j, k);
    }

    public boolean canInsert(ItemInstance stack) {
        return false;
    }
}
