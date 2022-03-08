package com.github.kydzombie.stapitest.gui;

import com.github.kydzombie.stapitest.container.PowerStorageContainer;
import com.github.kydzombie.stapitest.tileentity.TileGenerator;
import net.minecraft.entity.player.PlayerInventory;
import org.lwjgl.opengl.GL11;

public class GuiGenerator extends GuiMachine {

    public GuiGenerator(PlayerInventory playerInventory, TileGenerator tileEntity) {
        super(new PowerStorageContainer(playerInventory, tileEntity), tileEntity, "Generator");
    }

    public void renderContainerBackground(float tickDelta) {
        int var2 = this.minecraft.textureManager.getTextureId("/assets/stapitest/stationapi/gui/generator.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.textureManager.bindTexture(var2);
        int var3 = (this.width - this.containerWidth) / 2;
        int var4 = (this.height - this.containerHeight) / 2;
        this.blit(var3, var4, 0, 0, this.containerWidth, this.containerHeight);
        int var5;
        if (((TileGenerator)tileEntity).isBurning()) {
            var5 = ((TileGenerator)tileEntity).getFuelTimeDelta(12);
            this.blit(var3 + 56, var4 + 36 + 12 - var5, 176, 12 - var5, 14, var5 + 2);
        }
    }
}