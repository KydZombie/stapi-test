package com.github.kydzombie.stapitest.tileentity;

import com.github.kydzombie.stapitest.recipe.PressRecipeRegistry;
import net.minecraft.item.ItemInstance;

public class TilePress extends ProcessingMachine {

    public TilePress() {
        super("Press");
        setMaxPower(1800);
        setTotalCookTime(120);
        setPowerUsage(4);
    }

    @Override
    public ItemInstance getOutput(ItemInstance input) {
        return PressRecipeRegistry.getRecipe(input);
    }
}
