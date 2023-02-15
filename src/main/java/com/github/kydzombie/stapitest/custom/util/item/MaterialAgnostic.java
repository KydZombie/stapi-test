package com.github.kydzombie.stapitest.custom.util.item;

import com.github.kydzombie.stapitest.material.Material;
import com.github.kydzombie.stapitest.material.Materials;
import net.minecraft.item.ItemInstance;
import net.minecraft.util.io.CompoundTag;

public interface MaterialAgnostic {
    static Material getUniqueMaterial(ItemInstance item) {
        CompoundTag nbt = item.getStationNBT();
        return Materials.get(nbt.getString("material"));
    }

    default void updateStats(ItemInstance item) {
        CompoundTag nbt = item.getStationNBT();
        if (!nbt.containsKey("material")) {
            nbt.put("material", "missingMaterial");
        }
    }
}
