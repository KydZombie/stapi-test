package com.github.kydzombie.stapitest.item;

import com.github.kydzombie.stapitest.util.machine.power.ItemPowerStorage;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.TextRenderer;
import net.minecraft.client.render.entity.ItemRenderer;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;
import org.lwjgl.opengl.GL11;

public abstract class PoweredItem extends TemplateItemBase implements ItemPowerStorage {
    public PoweredItem(Identifier identifier, int maxPower) {
        super(identifier);
        this.setMaxStackSize(1);
        setDurability(maxPower);
        setTranslationKey(identifier.toString());
    }

    @Override
    public int getCurrentPower(ItemInstance item) {
        return item.getDurability() - item.getDamage();
    }

    @Override
    public int getMaxPower(ItemInstance item) {
        return item.getDurability();
    }

    @Override
    public int charge(ItemInstance item, int chargeAmount, boolean simulate) {
        chargeAmount = Math.min(item.getDamage(), chargeAmount);
        if (!simulate) {
            item.setDamage(item.getDamage() - chargeAmount);
        }

        return chargeAmount;
    }

    @Override
    public int consume(ItemInstance item, int consumeAmount, boolean simulate) {
        consumeAmount = Math.min(((PoweredItem)item.getType()).getCurrentPower(item), consumeAmount);
        if (!simulate) {
            item.setDamage(item.getDamage() + consumeAmount);
        }

        return consumeAmount;
    }

    @Override
    public void renderItemOverlay(ItemRenderer itemRenderer, int itemX, int itemY, ItemInstance itemInstance, TextRenderer textRenderer, TextureManager textureManager) {
        int barLength = (int)Math.round((((double)getCurrentPower(itemInstance) / (double)getMaxPower(itemInstance)) * 13));
        int colourOffset = 255-(int)Math.round((((double)getCurrentPower(itemInstance) / (double)getMaxPower(itemInstance)) * 225));
        GL11.glDisable(2896);
        GL11.glDisable(2929);
        GL11.glDisable(3553);
        Tessellator var8 = Tessellator.INSTANCE;
        int barColour = Math.max((colourOffset/8) - 130, 100) << 16 | (233-colourOffset) << 8 | 255-(colourOffset/4);
        int backgroundColour = (255 - colourOffset) / 4 << 16 | 16128;
        int barOffset = 0;
        method_1485(var8, itemX + 2, itemY + 13 - barOffset, 13, 2, 0);
        method_1485(var8, itemX + 2, itemY + 13 - barOffset, 12, 1, backgroundColour);
        method_1485(var8, itemX + 2, itemY + 13 - barOffset, barLength, 1, barColour);
        GL11.glEnable(3553);
        GL11.glEnable(2896);
        GL11.glEnable(2929);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
