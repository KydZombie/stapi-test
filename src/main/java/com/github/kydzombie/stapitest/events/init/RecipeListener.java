package com.github.kydzombie.stapitest.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.recipe.SmeltingRegistry;

public class RecipeListener {

    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {
        if (event.recipeId.equals(RecipeRegisterEvent.Vanilla.SMELTING.type())) {
            SmeltingRegistry.addSmeltingRecipe(ItemListener.ironDust.id, new ItemInstance(ItemBase.ironIngot));
            SmeltingRegistry.addSmeltingRecipe(ItemListener.goldDust.id, new ItemInstance(ItemBase.goldIngot));
        }
        else if (event.recipeId.equals(RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type())) {
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.battery, 1, ItemListener.battery.getDurability()), BlockBase.DIRT);
        }
    }
}
