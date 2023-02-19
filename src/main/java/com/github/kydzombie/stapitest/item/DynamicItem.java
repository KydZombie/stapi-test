package com.github.kydzombie.stapitest.item;

import com.github.kydzombie.stapitest.material.Material;
import com.github.kydzombie.stapitest.material.Materials;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.client.gui.CustomTooltipProvider;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ItemRegistry;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

import java.util.HashMap;
import java.util.Objects;

import static com.github.kydzombie.stapitest.events.init.StapiTest.MOD_ID;

public class DynamicItem extends TemplateItemBase implements CustomTooltipProvider {

    public static TemplateItemBase[] materialState;
    private static TemplateItemBase ingot;
    private static TemplateItemBase dust;
    private static TemplateItemBase sludge;
    private static TemplateItemBase impureDust;

    public DynamicItem(Identifier identifier) {
        super(identifier);
        setTranslationKey(identifier.toString());
    }
    public static void init() {
        ingot = new DynamicItem(MOD_ID.id("ingot"));
        dust = new DynamicItem(MOD_ID.id("dust"));
        sludge = new DynamicItem(MOD_ID.id("sludge"));
        impureDust = new DynamicItem(MOD_ID.id("impureDust"));
        materialState = new TemplateItemBase[] {
                ingot, dust, sludge, impureDust
        };
    }

    @Override
    public void onCreation(ItemInstance item, Level arg1, PlayerBase arg2) {
        super.onCreation(item, arg1, arg2);
        updateStats(item);
    }

    public void updateStats(ItemInstance item) {
        CompoundTag nbt = item.getStationNBT();
        if (!nbt.containsKey("material")) {
            nbt.put("material", "missingMaterial");
        }
    }

    public static ItemInstance convert(ItemBase itemBase, String material) {
        return convert(new ItemInstance(itemBase), material);
    }
    public static ItemInstance convert(ItemInstance item, String material) {
        item.getStationNBT().put("material", material);
        return item;
    }

    public static ItemInstance ingot(String material, int count) {
        ItemInstance item = ingot(material);
        item.count = count;
        return item;
    }
    public static ItemInstance ingot(String material) {
        return convert(ingot, material);
    }
    public static ItemInstance dust(String material, int count) {
        ItemInstance item = dust(material);
        item.count = count;
        return item;
    }
    public static ItemInstance dust(String material) {
        return convert(dust, material);
    }
    public static ItemInstance impureDust(String material, int count) {
        ItemInstance item = impureDust(material);
        item.count = count;
        return item;
    }
    public static ItemInstance impureDust(String material) {
        return convert(impureDust, material);
    }
    public static ItemInstance sludge(String material, int count) {
        ItemInstance item = sludge(material);
        item.count = count;
        return item;
    }
    public static ItemInstance sludge(String material) {
        return  convert(sludge, material);
    }

    @Override
    public String[] getTooltip(ItemInstance item, String originalTooltip) {
        CompoundTag nbt = item.getStationNBT();
        String untranslatedName = getTranslationKey();
        untranslatedName = untranslatedName.substring(untranslatedName.indexOf(":") + 1).replace("%s ", "");
        String modId = Objects.requireNonNull(ItemRegistry.INSTANCE.getId(item.getType())).modID.toString();
        String material = nbt.getString("material");
        String materialName = I18n.translate(String.format("material.%s:%s.name", modId, material));
        String itemName = I18n.translate(String.format("item.%s:%s_%s.name", modId, material, untranslatedName));
        if (!itemName.contains(".name")) {
            return new String[] {
                    itemName
            };
        }

        return new String[] {
                String.format(originalTooltip, materialName)
        };
    }
}
