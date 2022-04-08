package com.github.kydzombie.stapitest.recipe;

import com.github.kydzombie.stapitest.events.init.StapiTest;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.item.nbt.StationNBT;

public class CentrifugeRecipeRegistry extends ProcessingRegistry {
    @Override
    public ItemInstance getOutput(ItemInstance item) {
        if (item.itemId == ItemBase.redstoneDust.id) {
            ItemInstance output = new ItemInstance(StapiTest.sludge);
            CompoundTag nbt = StationNBT.cast(output).getStationNBT();
            nbt.put("material", "redstone");
            return output;
        }
        return super.getOutput(item);
    }
}
