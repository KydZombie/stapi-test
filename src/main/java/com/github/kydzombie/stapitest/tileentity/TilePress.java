package com.github.kydzombie.stapitest.tileentity;

import com.github.kydzombie.stapitest.recipe.PressRecipeRegistry;
import net.minecraft.item.ItemInstance;

public class TilePress extends ProcessingMachine {

    public TilePress() {
        super(1800, "Press", 120, 4);
    }

    @Override
    public ItemInstance getOutput(ItemInstance input) {
        return PressRecipeRegistry.getRecipe(input);
    }
}
