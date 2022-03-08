package com.github.kydzombie.stapitest.gui;

import com.github.kydzombie.stapitest.container.PowerStorageContainer;
import com.github.kydzombie.stapitest.tileentity.TileBattery;
import net.minecraft.entity.player.PlayerInventory;
import org.lwjgl.opengl.GL11;

public class GuiBattery extends GuiMachine {

    public GuiBattery(PlayerInventory playerInventory, TileBattery tileEntity) {
        super(new PowerStorageContainer(playerInventory, tileEntity), tileEntity, "Battery");
    }

    public void renderContainerBackground(float tickDelta) {
        int var2 = this.minecraft.textureManager.getTextureId("/assets/stapitest/stationapi/gui/generator.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.textureManager.bindTexture(var2);
        int var3 = (this.width - this.containerWidth) / 2;
        int var4 = (this.height - this.containerHeight) / 2;
        this.blit(var3, var4, 0, 0, this.containerWidth, this.containerHeight);
    }
}