package com.github.kydzombie.stapitest.tileentity;

import com.github.kydzombie.stapitest.events.init.StapiTest;
import com.github.kydzombie.stapitest.recipe.ElectricFurnaceRecipeRegistry;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.recipe.SmeltingRegistry;

public class TileElectricFurnace extends ProcessingMachine {

    public TileElectricFurnace() {
        super("Electric Furnace");
        setPowerUsage(2);
        setRecipeHandler(StapiTest.eFurnaceRegistry);
    }
}
