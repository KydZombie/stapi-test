package com.github.kydzombie.stapitest.recipe;

import net.minecraft.item.ItemInstance;

import java.util.ArrayList;
import java.util.List;

public abstract class ProcessingRegistry {

    public static ProcessingRegistry INSTANCE;

    private static final List<ItemInstance[]> recipes = new ArrayList<>();

    public ProcessingRegistry() {
        INSTANCE = this;
    }

    public static void addRecipe(ItemInstance input, ItemInstance output) {
        recipes.add(new ItemInstance[]{ input, output });
    }

    public ItemInstance getOutput(ItemInstance item) {
        for (ItemInstance[] recipe: recipes) {
            if (recipe[0] != null && recipe[0].itemId == item.itemId) {
                return recipe[1];
            }
        }
        return null;
    }

}
