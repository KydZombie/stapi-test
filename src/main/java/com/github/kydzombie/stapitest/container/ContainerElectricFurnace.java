package com.github.kydzombie.stapitest.container;

import com.github.kydzombie.stapitest.tileentity.TileElectricFurnace;
import net.minecraft.entity.player.PlayerInventory;

public class ContainerElectricFurnace extends ProcessingContainer {
    public ContainerElectricFurnace(PlayerInventory playerInventory, TileElectricFurnace furnace) {
        super(playerInventory, furnace);
    }
}
