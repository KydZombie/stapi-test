package com.github.kydzombie.stapitest.tileentity;

import com.github.kydzombie.stapitest.recipe.GrinderRecipeRegistry;
import net.minecraft.item.ItemInstance;

public class TileGrinder extends ProcessingMachine {
    public TileGrinder() {
        super(1200, "Grinder");
    }

    public ItemInstance getOutput(ItemInstance input) {
        return GrinderRecipeRegistry.getRecipe(input);
    }
}
