package com.github.kydzombie.stapitest.item.tool;

import com.github.kydzombie.stapitest.custom.util.item.MaterialAgnostic;
import com.github.kydzombie.stapitest.custom.util.machine.power.ItemPowerStorage;
import com.github.kydzombie.stapitest.item.DynamicItem;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.TextRenderer;
import net.minecraft.client.render.entity.ItemRenderer;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.client.gui.CustomItemOverlay;
import net.modificationstation.stationapi.api.item.nbt.StationNBT;
import net.modificationstation.stationapi.api.item.tool.ToolLevel;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.util.Colours;
import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.opengl.GL11;

public class DynamicTool extends DynamicItem implements ToolLevel, CustomItemOverlay {
    public DynamicTool(Identifier identifier) {
        super(identifier);
        this.setMaxStackSize(1);
        setTranslationKey(identifier.toString());
    }

    public static int getDurability(ItemInstance item) {
        CompoundTag nbt = StationNBT.cast(item).getStationNBT();
        ((DynamicTool)item.getType()).updateStats(item);
        return (nbt.getInt("maxDurability") - nbt.getInt("damage"));
    }

    @Override
    public boolean useOnTile(ItemInstance item, PlayerBase player, Level level, int x, int y, int z, int facing) {
        CompoundTag nbt = StationNBT.cast(item).getStationNBT();
        if (nbt.getString("material").equals("gold")) {
            nbt.put("material", "diamond");
        }
        else {
            nbt.put("material", "gold");
        }
        updateStats(item);
        return super.useOnTile(item, player, level, x, y, z, facing);
    }

    @Override
    public void updateStats(ItemInstance item) {
        super.updateStats(item);
        CompoundTag nbt = StationNBT.cast(item).getStationNBT();
        nbt.put("maxDurability", getMaterial(item).getDurability());
        if (!nbt.containsKey("damage")) {
            nbt.put("damage", 0);
        }
    }

    @Override
    public ToolMaterial getMaterial(ItemInstance item) {
        return MaterialAgnostic.getUniqueMaterial(item).getToolMaterial();
    }

    @Override
    public boolean postHit(ItemInstance item, Living damageSource, Living damageTarget) {
        applyDamage(item, 2, damageTarget);
        return true;
    }

    @Override
    public boolean postMine(ItemInstance item, int i, int j, int k, int i1, Living damageTarget) {
        CompoundTag nbt = StationNBT.cast(item).getStationNBT();
        if (!nbt.getString("material").equals("missingMaterial")) {
            applyDamage(item, 1, damageTarget);
        }
        return true;
    }

    public void applyDamage(ItemInstance item, int damage, Living damageTarget) {
        applyDamage(item, damage);
    }

    public void applyDamage(ItemInstance item, int damage) {
        CompoundTag nbt = StationNBT.cast(item).getStationNBT();
        int newDamage = nbt.getInt("damage") + damage;
        if (newDamage > nbt.getInt("maxDurability")) {
            item.count = 0;
        }
        else {
            nbt.put("damage", newDamage);
        }
    }

    @Override
    public void renderItemOverlay(ItemRenderer itemRenderer, int itemX, int itemY, ItemInstance itemInstance, TextRenderer textRenderer, TextureManager textureManager) {
        CompoundTag nbt = StationNBT.cast(itemInstance).getStationNBT();
        if (nbt.getInt("damage") == 0) {
            return;
        }
        double maxDurability = nbt.getInt("maxDurability");
        double currentDurability = maxDurability - nbt.getInt("damage");

        int barLength = (int) Math.round((currentDurability / maxDurability) * 13);
        int colourOffset = -48 - (int) Math.round((currentDurability / maxDurability) * 225);
        GL11.glDisable(2896);
        GL11.glDisable(2929);
        GL11.glDisable(3553);
        Tessellator var8 = Tessellator.INSTANCE;
        int barColour = Math.max((colourOffset / 8) - 130, 100) << 16 | (233 - colourOffset) << 8 | 255 - (colourOffset / 4);
        int backgroundColour = (255 - colourOffset) / 4 << 16 | 16128;
        method_1485(var8, itemX + 2, itemY + 13, 13, 2, 0);
        method_1485(var8, itemX + 2, itemY + 13, 12, 1, backgroundColour);
        method_1485(var8, itemX + 2, itemY + 13, barLength, 1, barColour);
        GL11.glEnable(3553);
        GL11.glEnable(2896);
        GL11.glEnable(2929);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void method_1485(Tessellator arg, int i, int j, int k, int i1, int i2) {
        arg.start();
        arg.colour(i2);
        arg.addVertex(i, j, 0.0D);
        arg.addVertex(i, j + i1, 0.0D);
        arg.addVertex(i + k, j + i1, 0.0D);
        arg.addVertex(i + k, j, 0.0D);
        arg.draw();
    }

    @Override
    public String[] getTooltip(ItemInstance item, String originalTooltip) {
        CompoundTag nbt = StationNBT.cast(item).getStationNBT();
        return ArrayUtils.add(super.getTooltip(item, originalTooltip),
                "Durability: " + (nbt.getInt("maxDurability") - nbt.getInt("damage")) + "/" + nbt.getInt("maxDurability"));
    }
}
