package com.github.kydzombie.stapitest.gui;

import com.github.kydzombie.stapitest.container.ContainerGrinder;
import com.github.kydzombie.stapitest.tileentity.TileGrinder;
import net.minecraft.entity.player.PlayerInventory;
import org.lwjgl.opengl.GL11;

public class GuiGrinder extends ProcessingGui {

    public GuiGrinder(PlayerInventory playerInventory, TileGrinder tileEntity) {
        super(new ContainerGrinder(playerInventory, tileEntity), tileEntity, "Grinder");
    }
}