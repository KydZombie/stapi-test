package com.github.kydzombie.stapitest.gui;

import com.github.kydzombie.stapitest.container.ProcessingContainer;
import com.github.kydzombie.stapitest.tileentity.TilePress;
import net.minecraft.entity.player.PlayerInventory;

public class GuiPress extends ProcessingGui {

    public GuiPress(PlayerInventory playerInventory, TilePress tileEntity) {
        super(new ProcessingContainer(playerInventory, tileEntity), tileEntity, "Press");
    }
}