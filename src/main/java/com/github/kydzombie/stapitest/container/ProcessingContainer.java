package com.github.kydzombie.stapitest.container;

import com.github.kydzombie.stapitest.gui.ProcessingOutput;
import com.github.kydzombie.stapitest.tileentity.ProcessingMachine;
import net.minecraft.container.slot.Slot;
import net.minecraft.entity.player.PlayerInventory;

public class ProcessingContainer extends ContainerMachine {
    public ProcessingContainer(PlayerInventory playerInventory, ProcessingMachine machine) {
        super(playerInventory, machine);
        this.addSlot(new Slot(machine, 0, 56, 35));
        this.addSlot(new ProcessingOutput(playerInventory.player, machine, 1, 116, 35));
    }
}
