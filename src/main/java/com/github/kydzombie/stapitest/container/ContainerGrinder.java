package com.github.kydzombie.stapitest.container;

import com.github.kydzombie.stapitest.gui.GrinderOutput;
import com.github.kydzombie.stapitest.tileentity.TileGrinder;
import net.minecraft.container.slot.Slot;
import net.minecraft.entity.player.PlayerInventory;

public class ContainerGrinder extends ContainerMachine {
    public ContainerGrinder(PlayerInventory playerInventory, TileGrinder furnace) {
        super(playerInventory, furnace);
        this.addSlot(new Slot(furnace, 0, 56, 35));
        this.addSlot(new GrinderOutput(playerInventory.player, furnace, 1, 116, 35));
    }
}
