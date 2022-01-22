package com.github.kydzombie.stapitest.gui;

import com.github.kydzombie.stapitest.tileentity.TilePowered;
import net.minecraft.client.gui.screen.container.ContainerBase;
import net.minecraft.client.render.Tessellator;
import org.lwjgl.opengl.GL11;

public abstract class GuiMachine extends ContainerBase {

    TilePowered tileEntity;
    String titleText;
    final PowerBar powerBar = new PowerBar(4, 24, 4, 70, true);

    public GuiMachine(net.minecraft.container.ContainerBase container) {
        super(container);
    }

    @Override
    protected void renderForeground() {
        this.textManager.drawText(titleText, 50, 6, 4210752);

        int power = tileEntity.getCurrentPower();
        int maxPower = tileEntity.getMaxPower();
        String powerText = "Power: " + power;
        this.textManager.drawText(powerText, this.containerWidth - (6 * powerText.length()), this.containerHeight - 96 + 2, 16711680);
        this.drawPower(power, maxPower);

        this.textManager.drawText("Inventory", 8, this.containerHeight - 96 + 2, 4210752);
    }

    @Override
    protected void renderContainerBackground(float tickDelta) {

    }

    void drawPower(int power, int maxPower) {
        if (powerBar.vertical) {
            this.drawBox(powerBar.x1, powerBar.x2, powerBar.y1, powerBar.y2, .2f, 0f, 0f, 1f);
            this.drawBox(powerBar.x1, powerBar.x2, getPowerHeight(power, maxPower), powerBar.y2, .7f, 0f, 0f, 1f);
        }
        else {
            System.out.println("Horizontal power bar unimplemented");
        }
    }

    private int getPowerHeight(int power, int maxPower) {
        // bottom is 70, top is 4
        int range = 66;

        int aboveTop = Math.round((float) range * ((float) power / (float) maxPower));

        return 70 - aboveTop;

    }

    // Allows for passing rgba values directly
    void drawBox(int x1, int x2, int y1, int y2, float red, float green, float blue, float alpha) {
        int var6;
        if (x1 < x2) {
            var6 = x1;
            x1 = x2;
            x2 = var6;
        }

        if (y1 < y2) {
            var6 = y1;
            y1 = y2;
            y2 = var6;
        }

        Tessellator var10 = Tessellator.INSTANCE;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(red, green, blue, alpha);
        var10.start();
        var10.addVertex(x1, y2, 0.0D);
        var10.addVertex(x2, y2, 0.0D);
        var10.addVertex(x2, y1, 0.0D);
        var10.addVertex(x1, y1, 0.0D);
        var10.draw();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
}

class PowerBar {
    final int x1;
    final int x2;
    final int y1;
    final int y2;

    final boolean vertical;

    public PowerBar(int x1, int x2, int y1, int y2, boolean vertical) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.vertical = vertical;
    }
}