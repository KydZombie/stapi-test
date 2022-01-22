package com.github.kydzombie.stapitest.container;

import com.github.kydzombie.stapitest.tileentity.TileGenerator;
import net.minecraft.container.slot.Slot;
import net.minecraft.entity.player.PlayerInventory;

public class ContainerGenerator extends ContainerMachine {
    public ContainerGenerator(PlayerInventory playerInventory, TileGenerator generator) {
        super(playerInventory, generator);
        this.addSlot(new Slot(generator, 0, 56, 53));
        this.addSlot(new Slot(generator, 1, 56, 17));
    }
}
