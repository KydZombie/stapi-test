package com.github.kydzombie.stapitest.tileentity;

import com.github.kydzombie.stapitest.events.init.StapiTest;

public class TileElectricFurnace extends ProcessingMachine {
    public TileElectricFurnace() {
        super("Electric Furnace");
        setPowerUsage(2);
        setRecipeHandler(StapiTest.eFurnaceRegistry);
    }
}
