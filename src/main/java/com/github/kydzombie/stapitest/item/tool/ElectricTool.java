package com.github.kydzombie.stapitest.item.tool;

import com.github.kydzombie.stapitest.custom.util.machine.power.ItemPowerStorage;
import com.github.kydzombie.stapitest.events.init.StapiTest;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.TextRenderer;
import net.minecraft.client.render.entity.ItemRenderer;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.item.nbt.StationNBT;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.util.Colours;
import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.opengl.GL11;

public class ElectricTool extends DynamicTool implements ItemPowerStorage {

    public ElectricTool(Identifier identifier) {
        super(identifier);
    }

    @Override
    public void onCreation(ItemInstance item, Level arg1, PlayerBase arg2) {
        super.onCreation(item, arg1, arg2);
        CompoundTag nbt = StationNBT.cast(item).getStationNBT();
        if (!nbt.containsKey("material")) {
            nbt.put("material", "missingMaterial");
        }
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
    public String[] getTooltip(ItemInstance itemInstance, String originalTooltip) {
        return ArrayUtils.add(super.getTooltip(itemInstance, originalTooltip),
                "" + Colours.RED + ((ItemPowerStorage) itemInstance.getType()).getCurrentPower(itemInstance) + Colours.WHITE +
                        "/" + Colours.DARK_AQUA + ((ItemPowerStorage) itemInstance.getType()).getMaxPower(itemInstance) + Colours.WHITE + " power stored");
    }

    public void renderItemOverlay(ItemRenderer itemRenderer, int itemX, int itemY, ItemInstance itemInstance, TextRenderer textRenderer, TextureManager textureManager) {
        CompoundTag nbt = StationNBT.cast(itemInstance).getStationNBT();
        int barOffset = nbt.getInt("damage") > 0 ? 2 : 0;

        int barLength = (int) Math.round((((double) getCurrentPower(itemInstance) / (double) getMaxPower(itemInstance)) * 13));
        int colourOffset = 255 - (int) Math.round((((double) getCurrentPower(itemInstance) / (double) getMaxPower(itemInstance)) * 225));
        GL11.glDisable(2896);
        GL11.glDisable(2929);
        GL11.glDisable(3553);
        Tessellator var8 = Tessellator.INSTANCE;
        int barColour = Math.max((colourOffset / 8) - 130, 100) << 16 | (233 - colourOffset) << 8 | 255 - (colourOffset / 4);
        int backgroundColour = (255 - colourOffset) / 4 << 16 | 16128;
        method_1485(var8, itemX + 2, itemY + 13 - barOffset, 13, 2, 0);
        method_1485(var8, itemX + 2, itemY + 13 - barOffset, 12, 1, backgroundColour);
        method_1485(var8, itemX + 2, itemY + 13 - barOffset, barLength, 1, barColour);
        GL11.glEnable(3553);
        GL11.glEnable(2896);
        GL11.glEnable(2929);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        super.renderItemOverlay(itemRenderer, itemX, itemY, itemInstance, textRenderer, textureManager);
    }
}
