package com.github.kydzombie.stapitest.util.machine.power;

import com.github.kydzombie.stapitest.item.tool.PoweredTool;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.TextRenderer;
import net.minecraft.client.render.entity.ItemRenderer;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.item.ItemInstance;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.client.gui.CustomItemOverlay;
import net.modificationstation.stationapi.api.client.gui.CustomTooltipProvider;
import net.modificationstation.stationapi.api.item.nbt.StationNBT;
import net.modificationstation.stationapi.api.util.Colours;
import org.lwjgl.opengl.GL11;

public interface ItemPowerStorage extends CustomTooltipProvider, CustomItemOverlay {

    default int getCurrentPower(ItemInstance item) {
        CompoundTag nbt = StationNBT.cast(item).getStationNBT();
        if (!nbt.containsKey("power")) {
            nbt.put("power", 0);
        }
        return nbt.getInt("power");
    }

    int getMaxPower(ItemInstance item);

    default int charge(ItemInstance item, int chargeAmount, boolean simulate) {
        PoweredTool poweredTool = ((PoweredTool)item.getType());
        chargeAmount = Math.min(chargeAmount, poweredTool.getMaxPower(item) - poweredTool.getCurrentPower(item));
        if (!simulate) {
            StationNBT.cast(item).getStationNBT().put("power", poweredTool.getCurrentPower(item) + chargeAmount);
        }

        return chargeAmount;
    }

    default int consume(ItemInstance item, int consumeAmount, boolean simulate) {
        PoweredTool poweredTool = ((PoweredTool)item.getType());
        consumeAmount = Math.min(poweredTool.getCurrentPower(item), consumeAmount);
        if (!simulate) {
            StationNBT.cast(item).getStationNBT().put("power", poweredTool.getCurrentPower(item) - consumeAmount);
        }

        return consumeAmount;
    }

    default String[] getTooltip(ItemInstance itemInstance, String originalTooltip) {
        return new String[]{
                originalTooltip,
                "" + Colours.RED + ((ItemPowerStorage) itemInstance.getType()).getCurrentPower(itemInstance) + Colours.WHITE + "/" + Colours.DARK_AQUA + ((ItemPowerStorage) itemInstance.getType()).getMaxPower(itemInstance) + Colours.WHITE + " power stored"
        };
    }

    default void renderItemOverlay(ItemRenderer itemRenderer, int itemX, int itemY, ItemInstance itemInstance, TextRenderer textRenderer, TextureManager textureManager) {
        int barLength = (int)Math.round((((double)getCurrentPower(itemInstance) / (double)getMaxPower(itemInstance)) * 13));
        int colourOffset = 255-(int)Math.round((((double)getCurrentPower(itemInstance) / (double)getMaxPower(itemInstance)) * 225));
        GL11.glDisable(2896);
        GL11.glDisable(2929);
        GL11.glDisable(3553);
        Tessellator var8 = Tessellator.INSTANCE;
        int barColour = Math.max((colourOffset/8) - 130, 100) << 16 | (233-colourOffset) << 8 | 255-(colourOffset/4);
        int backgroundColour = (255 - colourOffset) / 4 << 16 | 16128;
        int barOffset = itemInstance.isDamaged()? 2 : 0;
        method_1485(var8, itemX + 2, itemY + 13 - barOffset, 13, 2, 0);
        method_1485(var8, itemX + 2, itemY + 13 - barOffset, 12, 1, backgroundColour);
        method_1485(var8, itemX + 2, itemY + 13 - barOffset, barLength, 1, barColour);
        GL11.glEnable(3553);
        GL11.glEnable(2896);
        GL11.glEnable(2929);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    default void method_1485(Tessellator arg, int i, int j, int k, int i1, int i2) {
        arg.start();
        arg.colour(i2);
        arg.addVertex(i, j, 0.0D);
        arg.addVertex(i, j + i1, 0.0D);
        arg.addVertex(i + k, j + i1, 0.0D);
        arg.addVertex(i + k, j, 0.0D);
        arg.draw();
    }

}
