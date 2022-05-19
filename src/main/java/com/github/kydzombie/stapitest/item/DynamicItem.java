package com.github.kydzombie.stapitest.item;

import com.github.kydzombie.stapitest.events.init.StapiTest;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.client.gui.CustomTooltipProvider;
import net.modificationstation.stationapi.api.item.nbt.StationNBT;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ItemRegistry;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

public class DynamicItem extends TemplateItemBase implements CustomTooltipProvider {
    public DynamicItem(Identifier identifier) {
        super(identifier);
        setTranslationKey(identifier.toString());
    }

    @Override
    public void onCreation(ItemInstance item, Level arg1, PlayerBase arg2) {
        super.onCreation(item, arg1, arg2);
        updateStats(item);
    }

    public void updateStats(ItemInstance item) {
        CompoundTag nbt = StationNBT.cast(item).getStationNBT();
        if (!nbt.containsKey("material")) {
            nbt.put("material", "missingMaterial");
        }
    }

    public static ItemInstance convert(ItemBase itemBase, String material) {
        return convert(new ItemInstance(itemBase), material);
    }

    public static ItemInstance convert(ItemBase itemBase, String material, int count) {
        ItemInstance newItem = convert(new ItemInstance(itemBase), material);
        newItem.count = count;
        return newItem;
    }

    public static ItemInstance convert(ItemInstance item, String material) {
        StationNBT.cast(item).getStationNBT().put("material", material);
        return item;
    }

    @Override
    public String[] getTooltip(ItemInstance item, String originalTooltip) {
        CompoundTag nbt = StationNBT.cast(item).getStationNBT();
        String untranslatedName = getTranslationKey();
        untranslatedName = untranslatedName.substring(untranslatedName.indexOf(":") + 1).replace("%s ", "");
        String modId = ItemRegistry.INSTANCE.getIdentifier(item.getType()).modID.toString();
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
