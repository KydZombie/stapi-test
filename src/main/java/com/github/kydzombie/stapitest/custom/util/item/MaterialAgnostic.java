package com.github.kydzombie.stapitest.custom.util.item;

import com.github.kydzombie.stapitest.custom.UniqueMaterial;
import net.minecraft.item.ItemInstance;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.item.nbt.StationNBT;

public interface MaterialAgnostic {
    static UniqueMaterial getUniqueMaterial(ItemInstance item) {
        CompoundTag nbt = StationNBT.cast(item).getStationNBT();
        return UniqueMaterial.getUniqueMaterial(nbt.getString("material"));
    }

    default void updateStats(ItemInstance item) {
        CompoundTag nbt = StationNBT.cast(item).getStationNBT();
        if (!nbt.containsKey("material")) {
            nbt.put("material", "missingMaterial");
        }
    }
}
