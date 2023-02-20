package com.github.kydzombie.stapitest.util;

import net.minecraft.item.ItemInstance;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.nbt.NBTHelper;

import java.util.Collection;

public class ItemUtil {
    public static boolean compare(ItemInstance filter, ItemInstance item) {
        boolean sameId = filter.itemId == item.itemId;
        if(!item.getStationNBT().values().isEmpty() && !filter.getStationNBT().values().isEmpty()) {
            boolean sameNBT = item.getStationNBT().getString("material")
                    .equals(filter.getStationNBT().getString("material"));
            return sameId && sameNBT;
        }
        return sameId;
    }
}
