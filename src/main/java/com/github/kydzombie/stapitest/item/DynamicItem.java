package com.github.kydzombie.stapitest.item;

import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.client.gui.CustomTooltipProvider;
import net.modificationstation.stationapi.api.item.nbt.StationNBT;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

public class DynamicItem extends TemplateItemBase implements CustomTooltipProvider {
    public DynamicItem(Identifier identifier) {
        super(identifier);
        setTranslationKey(identifier.toString());
    }

    public static ItemInstance convert(ItemBase itemBase, String material) {
        return convert(new ItemInstance(itemBase), material);
    }

    public static ItemInstance convert(ItemInstance item, String material) {
        StationNBT.cast(item).getStationNBT().put("material", material);
        return item;
    }

    @Override
    public String getTranslationKey(ItemInstance item) {
        CompoundTag nbt = StationNBT.cast(item).getStationNBT();
        if (nbt.containsKey("material") && nbt.getString("material").equals("redstone")) {
            return "item.stapitest:redSludge";
        }
        return super.getTranslationKey();
    }

    @Override
    public String[] getTooltip(ItemInstance itemInstance, String originalTooltip) {
        CompoundTag nbt = StationNBT.cast(itemInstance).getStationNBT();
        if (!nbt.containsKey("material") || nbt.getString("material").equals("redstone")) {
            return new String[] {
                    originalTooltip
            };
        }
        return new String[] {
                I18n.translate(String.format("material.stapitest:%s.name", nbt.getString("material"))) + " " + originalTooltip
        };
    }
}
