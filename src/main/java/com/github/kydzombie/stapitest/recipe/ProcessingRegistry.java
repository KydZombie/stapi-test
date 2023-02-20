package com.github.kydzombie.stapitest.recipe;

import com.github.kydzombie.stapitest.util.ItemUtil;
import net.minecraft.item.ItemInstance;

import java.util.ArrayList;
import java.util.List;

public abstract class ProcessingRegistry {
    public final List<ItemInstance[]> recipes = new ArrayList<>();

    public void addRecipe(ItemInstance input, ItemInstance output) {
        recipes.add(new ItemInstance[]{ input, output });
    }

    public ItemInstance getOutput(ItemInstance item) {
        for (ItemInstance[] recipe: recipes) {
            if (recipe[0] != null && ItemUtil.compare(recipe[0], item)) {
                return recipe[1];
            }
        }
        return null;
    }

}
