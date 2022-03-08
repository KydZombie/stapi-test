package com.github.kydzombie.stapitest.gui;

import com.github.kydzombie.stapitest.container.ContainerPress;
import com.github.kydzombie.stapitest.tileentity.TilePress;
import net.minecraft.entity.player.PlayerInventory;
import org.lwjgl.opengl.GL11;

public class GuiPress extends ProcessingGui {

    public GuiPress(PlayerInventory playerInventory, TilePress tileEntity) {
        super(new ContainerPress(playerInventory, tileEntity), tileEntity, "Press");
    }
}