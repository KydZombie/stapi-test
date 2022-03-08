package com.github.kydzombie.stapitest.container;

import com.github.kydzombie.stapitest.tileentity.TileBattery;
import net.minecraft.container.slot.Slot;
import net.minecraft.entity.player.PlayerInventory;

public class ContainerBattery extends ContainerMachine {
    public ContainerBattery(PlayerInventory playerInventory, TileBattery battery) {
        super(playerInventory, battery);
    }
}
