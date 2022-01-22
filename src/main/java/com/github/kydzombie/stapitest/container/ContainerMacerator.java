package com.github.kydzombie.stapitest.container;

import com.github.kydzombie.stapitest.gui.MaceratorOutput;
import com.github.kydzombie.stapitest.tileentity.TileMacerator;
import net.minecraft.container.slot.Slot;
import net.minecraft.entity.player.PlayerInventory;

public class ContainerMacerator extends ContainerMachine {
    public ContainerMacerator(PlayerInventory playerInventory, TileMacerator furnace) {
        super(playerInventory, furnace);
        this.addSlot(new Slot(furnace, 0, 56, 17));
        this.addSlot(new MaceratorOutput(playerInventory.player, furnace, 1, 116, 35));
    }
}
