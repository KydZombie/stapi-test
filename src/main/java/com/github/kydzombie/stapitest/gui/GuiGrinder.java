package com.github.kydzombie.stapitest.gui;

import com.github.kydzombie.stapitest.container.ContainerGrinder;
import com.github.kydzombie.stapitest.tileentity.TileMacerator;
import net.minecraft.entity.player.PlayerInventory;
import org.lwjgl.opengl.GL11;

public class GuiGrinder extends GuiMachine {

    public GuiGrinder(PlayerInventory playerInventory, TileMacerator tileEntity) {
        super(new ContainerGrinder(playerInventory, tileEntity), tileEntity, "Grinder");
    }

    public void renderContainerBackground(float tickDelta) {
        int var2 = this.minecraft.textureManager.getTextureId("/assets/stapitest/stationapi/gui/basic_machine.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.textureManager.bindTexture(var2);
        int var3 = (this.width - this.containerWidth) / 2;
        int var4 = (this.height - this.containerHeight) / 2;
        this.blit(var3, var4, 0, 0, this.containerWidth, this.containerHeight);
        int var5 = ((TileMacerator)tileEntity).getCookTimeDelta(24);
        this.blit(var3 + 79, var4 + 34, 176, 14, var5 + 1, 16);
    }
}