package com.github.kydzombie.stapitest.item.tool;

import com.github.kydzombie.stapitest.custom.UniqueMaterial;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.TextRenderer;
import net.minecraft.client.render.entity.ItemRenderer;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.client.gui.CustomItemOverlay;
import net.modificationstation.stationapi.api.client.gui.CustomTooltipProvider;
import net.modificationstation.stationapi.api.item.nbt.StationNBT;
import net.modificationstation.stationapi.api.item.tool.ToolLevel;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;
import org.lwjgl.opengl.GL11;

public class MaterialAgnosticTool extends TemplateItemBase implements ToolLevel, CustomTooltipProvider, CustomItemOverlay {
    public MaterialAgnosticTool(Identifier identifier) {
        super(identifier);
        this.setMaxStackSize(1);
        setTranslationKey(identifier.toString());
    }

    public static int getDurability(ItemInstance item) {
        CompoundTag nbt = StationNBT.cast(item).getStationNBT();
        ((MaterialAgnosticTool)item.getType()).updateStats(item);
        return (nbt.getInt("maxDurability") - nbt.getInt("damage"));
    }

    @Override
    public void onCreation(ItemInstance item, Level arg1, PlayerBase arg2) {
        super.onCreation(item, arg1, arg2);
        updateStats(item);
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

    public void updateStats(ItemInstance item) {
        CompoundTag nbt = StationNBT.cast(item).getStationNBT();
        if (!nbt.containsKey("material")) {
            nbt.put("material", "missingMaterial");
        }
        nbt.put("maxDurability", getMaterial(item).getDurability());
        if (!nbt.containsKey("damage")) {
            nbt.put("damage", 0);
        }
    }

    @Override
    public ToolMaterial getMaterial(ItemInstance item) {
        return getUniqueMaterial(item).getToolMaterial();
    }

    public static UniqueMaterial getUniqueMaterial(ItemInstance item) {
        CompoundTag nbt = StationNBT.cast(item).getStationNBT();
        return UniqueMaterial.materials.getOrDefault(nbt.getString("material"), UniqueMaterial.materials.get("missingMaterial"));
    }

    @Override
    public boolean postHit(ItemInstance item, Living damageSource, Living damageTarget) {
        applyDamage(item, 2);
        return true;
    }

    @Override
    public boolean postMine(ItemInstance item, int i, int j, int k, int i1, Living damageTarget) {
        CompoundTag nbt = StationNBT.cast(item).getStationNBT();
        if (!nbt.getString("material").equals("missingMaterial")) {
            applyDamage(item, 1);
            if (nbt.getInt("damage") >= nbt.getInt("maxDurability")) {
                item.count--;
            }
        }
        return true;
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
    public String[] getTooltip(ItemInstance itemInstance, String originalTooltip) {
        CompoundTag nbt = StationNBT.cast(itemInstance).getStationNBT();
        return new String[]{
                originalTooltip,
                "Material: " + nbt.getString("material"),
                "Durability: " + (nbt.getInt("maxDurability") - nbt.getInt("damage")) + "/" + nbt.getInt("maxDurability")
        };
    }
}