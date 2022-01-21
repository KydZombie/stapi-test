package com.github.kydzombie.stapitest.gui;

import com.github.kydzombie.stapitest.container.ContainerElectricFurnace;
import com.github.kydzombie.stapitest.tileentity.TileElectricFurnace;
import net.minecraft.client.gui.screen.container.ContainerBase;
import net.minecraft.entity.player.PlayerInventory;
import org.lwjgl.opengl.GL11;

public class GuiElectricFurnace extends ContainerBase {

    private TileElectricFurnace furnace;

    public GuiElectricFurnace(PlayerInventory playerInventory, TileElectricFurnace furnace) {
        super(new ContainerElectricFurnace(playerInventory, furnace));
        this.furnace = furnace;
    }

    protected void renderForeground() {
        this.textManager.drawText("Electric Furnace", 50, 6, 4210752);

        String powerText = "Power: " + furnace.getCurrentPower();

        this.textManager.drawText(powerText, this.containerWidth - (6 * powerText.length()), this.containerHeight - 96 + 2, 16711680);

        this.textManager.drawText("Inventory", 8, this.containerHeight - 96 + 2, 4210752);

    }

    protected void renderContainerBackground(float tickDelta) {
        int var2 = this.minecraft.textureManager.getTextureId("/gui/furnace.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.textureManager.bindTexture(var2);
        int var3 = (this.width - this.containerWidth) / 2;
        int var4 = (this.height - this.containerHeight) / 2;
        this.blit(var3, var4, 0, 0, this.containerWidth, this.containerHeight);
        int var5 = this.furnace.getCookTimeDelta(24);
        this.blit(var3 + 79, var4 + 34, 176, 14, var5 + 1, 16);
//        this.textManager.drawText("Power: " + furnace.getCurrentPower(), 64, this.containerHeight - 72 + 2, 4210752);
    }
}
