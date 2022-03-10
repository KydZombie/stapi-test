package com.github.kydzombie.stapitest.recipe;

import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.recipe.SmeltingRegistry;

public class ElectricFurnaceRecipeRegistry extends ProcessingRegistry {

    @Override
    public ItemInstance getOutput(ItemInstance item) {
        ItemInstance tempResult = super.getOutput(item);
        if (tempResult != null) {
            return tempResult;
        }
        return SmeltingRegistry.getResultFor(item);
    }

}
