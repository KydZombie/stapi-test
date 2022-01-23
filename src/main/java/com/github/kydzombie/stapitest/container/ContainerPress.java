package com.github.kydzombie.stapitest.container;

import com.github.kydzombie.stapitest.gui.GrinderOutput;
import com.github.kydzombie.stapitest.tileentity.TilePress;
import net.minecraft.container.slot.Slot;
import net.minecraft.entity.player.PlayerInventory;

public class ContainerPress extends ContainerMachine {
    public ContainerPress(PlayerInventory playerInventory, TilePress furnace) {
        super(playerInventory, furnace);
        this.addSlot(new Slot(furnace, 0, 56, 35));
        this.addSlot(new GrinderOutput(playerInventory.player, furnace, 1, 116, 35));
    }
}
