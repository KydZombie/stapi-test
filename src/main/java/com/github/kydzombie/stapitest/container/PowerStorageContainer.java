package com.github.kydzombie.stapitest.container;

import com.github.kydzombie.stapitest.tileentity.TileMachine;
import net.minecraft.container.slot.Slot;
import net.minecraft.entity.player.PlayerInventory;

public class PowerStorageContainer extends ContainerMachine {
    public PowerStorageContainer(PlayerInventory playerInventory, TileMachine powerStorage) {
        super(playerInventory, powerStorage);
        this.addSlot(new Slot(powerStorage, 0, 56, 53));
        this.addSlot(new Slot(powerStorage, 1, 56, 17));
    }
}
