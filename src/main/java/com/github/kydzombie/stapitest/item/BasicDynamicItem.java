package com.github.kydzombie.stapitest.item;

import net.minecraft.item.ItemInstance;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.item.nbt.StationNBT;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

public class BasicDynamicItem extends TemplateItemBase {
    public BasicDynamicItem(Identifier identifier) {
        super(identifier);
        setTranslationKey(identifier.toString());
    }

    @Override
    public String getTranslationKey(ItemInstance item) {
        CompoundTag nbt = StationNBT.cast(item).getStationNBT();
        System.out.println("Material: " + nbt.getString("material"));
        if (nbt.containsKey("material") && nbt.getString("material").equals("redstone")) {
            return "item.stapitest:redSludge";
        }
        return super.getTranslationKey();
    }
}
