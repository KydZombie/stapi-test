package com.github.kydzombie.stapitest.tileentity;

import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.recipe.SmeltingRegistry;

public class TileElectricFurnace extends ProcessingMachine {

    public TileElectricFurnace() {
        super("Electric Furnace");
        setPowerUsage(30);
    }

    @Override
    public ItemInstance getOutput(ItemInstance input) {
        return SmeltingRegistry.getResultFor(input);
    }
}
