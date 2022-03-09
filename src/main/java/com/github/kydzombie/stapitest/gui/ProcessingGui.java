package com.github.kydzombie.stapitest.gui;

import com.github.kydzombie.stapitest.container.ProcessingContainer;
import com.github.kydzombie.stapitest.tileentity.ProcessingMachine;
import com.github.kydzombie.stapitest.tileentity.TileMachine;
import net.minecraft.container.ContainerBase;
import net.minecraft.entity.player.PlayerInventory;
import org.lwjgl.opengl.GL11;

public class ProcessingGui extends GuiMachine {
    public ProcessingGui(PlayerInventory playerInventory, ProcessingMachine machineEntity, String titleText) {
        super(new ProcessingContainer(playerInventory, machineEntity), machineEntity, titleText);
    }

    @Override
    public void renderContainerBackground(float tickDelta) {
        int var2 = this.minecraft.textureManager.getTextureId("/assets/stapitest/stationapi/gui/basic_machine.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.textureManager.bindTexture(var2);
        int var3 = (this.width - this.containerWidth) / 2;
        int var4 = (this.height - this.containerHeight) / 2;
        this.blit(var3, var4, 0, 0, this.containerWidth, this.containerHeight);
        int var5 = ((ProcessingMachine) tileEntity).getCookTimeDelta(24);
        this.blit(var3 + 79, var4 + 34, 176, 14, var5 + 1, 16);
    }
}
