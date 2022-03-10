package com.github.kydzombie.stapitest.tileentity;

import com.github.kydzombie.stapitest.recipe.PressRecipeRegistry;

public class TilePress extends ProcessingMachine {
    public TilePress() {
        super("Press");
        setMaxPower(1800);
        setTotalCookTime(120);
        setPowerUsage(4);
        setRecipeHandler(PressRecipeRegistry.INSTANCE);
    }
}
