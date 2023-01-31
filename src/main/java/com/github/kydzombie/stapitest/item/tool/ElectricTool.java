package com.github.kydzombie.stapitest.item.tool;

import com.github.kydzombie.stapitest.custom.util.machine.power.ItemPowerStorage;
import com.github.kydzombie.stapitest.events.init.StapiTest;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.TextRenderer;
import net.minecraft.client.render.entity.ItemRenderer;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.entity.Living;
import net.minecraft.item.ItemInstance;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.util.Colours;
import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.opengl.GL11;

public class ElectricTool extends DynamicTool implements ItemPowerStorage {

    public ElectricTool(Identifier identifier, String effectiveOn) {
        super(identifier, effectiveOn);
    }

    @Override
    public void applyDamage(ItemInstance item, int damage, Living damageTarget) {
        for (int i = 0; i < damage; i++) {
            consume(item, 5, false);
            if (rand.nextInt(5) == 0) {
                super.applyDamage(item, damage, damageTarget);
                if (item.count == 0) {
                    damageTarget.dropItem(new ItemInstance(StapiTest.powerToolHandle), 2);
                }
            }
        }
    }

    @Override
    public int getMaxPower(ItemInstance item) {
        return 1200;
    }

    @Override
    public String[] getTooltip(ItemInstance item, String originalTooltip) {
        return ArrayUtils.add(super.getTooltip(item, originalTooltip),
                "" + Colours.RED + ((ItemPowerStorage) item.getType()).getCurrentPower(item) + Colours.WHITE +
                        "/" + Colours.DARK_AQUA + ((ItemPowerStorage) item.getType()).getMaxPower(item) + Colours.WHITE + " power stored");
    }

    public void renderItemOverlay(ItemRenderer itemRenderer, int itemX, int itemY, ItemInstance item, TextRenderer textRenderer, TextureManager textureManager) {
        CompoundTag nbt = item.getStationNBT();
        int barOffset = nbt.getInt("damage") > 0 ? 2 : 0;

        int barLength = (int) Math.round((((double) getCurrentPower(item) / (double) getMaxPower(item)) * 13));
        int colorOffset = 255 - (int) Math.round((((double) getCurrentPower(item) / (double) getMaxPower(item)) * 225));
        GL11.glDisable(2896);
        GL11.glDisable(2929);
        GL11.glDisable(3553);
        Tessellator var8 = Tessellator.INSTANCE;
        int barColour = Math.max((colorOffset / 8) - 130, 100) << 16 | (233 - colorOffset) << 8 | 255 - (colorOffset / 4);
        int backgroundColour = (255 - colorOffset) / 4 << 16 | 16128;
        method_1485(var8, itemX + 2, itemY + 13 - barOffset, 13, 2, 0);
        method_1485(var8, itemX + 2, itemY + 13 - barOffset, 12, 1, backgroundColour);
        method_1485(var8, itemX + 2, itemY + 13 - barOffset, barLength, 1, barColour);
        GL11.glEnable(3553);
        GL11.glEnable(2896);
        GL11.glEnable(2929);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        super.renderItemOverlay(itemRenderer, itemX, itemY, item, textRenderer, textureManager);
    }
}
