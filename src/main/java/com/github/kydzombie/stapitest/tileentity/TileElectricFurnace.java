package com.github.kydzombie.stapitest.tileentity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemInstance;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.recipe.SmeltingRegistry;

public class TileElectricFurnace extends ProcessingMachine {

    public TileElectricFurnace() {
        super(800, "ElectricFurnace", 30, 0);
    }

    @Override
    public ItemInstance getOutput(ItemInstance input) {
        return SmeltingRegistry.getResultFor(input);
    }
}
