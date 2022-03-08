package com.github.kydzombie.stapitest.gui;

import com.github.kydzombie.stapitest.container.ContainerElectricFurnace;
import com.github.kydzombie.stapitest.tileentity.TileElectricFurnace;
import net.minecraft.entity.player.PlayerInventory;

public class GuiElectricFurnace extends ProcessingGui {

    public GuiElectricFurnace(PlayerInventory playerInventory, TileElectricFurnace tileEntity) {
        super(new ContainerElectricFurnace(playerInventory, tileEntity), tileEntity, "Electric Furnace");
    }
}