package com.github.kydzombie.stapitest.gui;

import com.github.kydzombie.stapitest.container.ProcessingContainer;
import com.github.kydzombie.stapitest.tileentity.TileGrinder;
import net.minecraft.entity.player.PlayerInventory;

public class GuiGrinder extends ProcessingGui {

    public GuiGrinder(PlayerInventory playerInventory, TileGrinder tileEntity) {
        super(new ProcessingContainer(playerInventory, tileEntity), tileEntity, "Grinder");
    }
}