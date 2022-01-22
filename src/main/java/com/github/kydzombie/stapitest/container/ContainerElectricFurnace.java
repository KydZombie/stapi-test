package com.github.kydzombie.stapitest.container;

import com.github.kydzombie.stapitest.tileentity.TileElectricFurnace;
import net.minecraft.container.slot.FurnaceOutput;
import net.minecraft.container.slot.Slot;
import net.minecraft.entity.player.PlayerInventory;

public class ContainerElectricFurnace extends ContainerMachine {
    public ContainerElectricFurnace(PlayerInventory playerInventory, TileElectricFurnace furnace) {
        super(playerInventory, furnace);
        this.addSlot(new Slot(furnace, 0, 56, 35));
        this.addSlot(new FurnaceOutput(playerInventory.player, furnace, 1, 116, 35));
    }
}
