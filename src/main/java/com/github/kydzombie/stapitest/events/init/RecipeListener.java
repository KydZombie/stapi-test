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
        else if (event.recipeId.equals(RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type())) {
            CraftingRegistry.addShapedRecipe(new ItemInstance(ItemListener.cable), "XXX", 'X', BlockBase.WOOL);
            CraftingRegistry.addShapedRecipe(new ItemInstance(ItemListener.powerCable), "XXX", 'X', new ItemInstance(BlockBase.WOOL, 1, 15));
            CraftingRegistry.addShapedRecipe(new ItemInstance(ItemListener.itemCable), "XXX", 'X', new ItemInstance(BlockBase.WOOL, 1, 5));

            CraftingRegistry.addShapedRecipe(new ItemInstance(ItemListener.ironPlate, 2), "X", "X", "X", 'X', ItemBase.ironIngot);
            CraftingRegistry.addShapedRecipe(new ItemInstance(ItemListener.goldPlate, 2), "X", "X", "X", 'X', ItemBase.goldIngot);

            CraftingRegistry.addShapedRecipe(new ItemInstance(ItemListener.ironGear), " X ", "XSX", " X ", 'X', ItemBase.ironIngot, 'S', ItemBase.stick);
            CraftingRegistry.addShapedRecipe(new ItemInstance(ItemListener.goldGear), " X ", "XSX", " X ", 'X', ItemBase.goldIngot, 'S', ItemBase.stick);

            CraftingRegistry.addShapedRecipe(new ItemInstance(ItemListener.wrench), "I I", " G ", " I ", 'I', ItemBase.ironIngot, 'G', ItemListener.goldGear);

            CraftingRegistry.addShapedRecipe(new ItemInstance(ItemListener.battery, 1, ItemListener.battery.getDurability()), " C ", "PRP", "PRP", 'C', ItemListener.powerCable, 'P', ItemListener.ironPlate, 'R', ItemBase.redstoneDust);

            CraftingRegistry.addShapedRecipe(new ItemInstance(ItemListener.powerToolHandle), "XGX", "XBX", "XVX", 'X', ItemListener.ironPlate, 'G', ItemListener.goldGear, 'B', new ItemInstance(ItemListener.battery, 1, -1), 'V', ItemListener.goldPlate);
            CraftingRegistry.addShapedRecipe(new ItemInstance(ItemListener.drillHead), " XX", "XRX", "RX ", 'X', ItemListener.ironPlate, 'R', ItemListener.goldGear);
            CraftingRegistry.addShapedRecipe(new ItemInstance(ItemListener.sawHead), " XX", "XRX", "RX ", 'X', ItemListener.ironGear, 'R', ItemBase.redstoneDust);
            CraftingRegistry.addShapedRecipe(new ItemInstance(ItemListener.diamondDrillHead), " D ", "DHD", " D ", 'D', ItemBase.diamond, 'H', ItemListener.drillHead);
            CraftingRegistry.addShapedRecipe(new ItemInstance(ItemListener.diamondSawHead), " D ", "DHD", " D ", 'D', ItemBase.diamond, 'H', ItemListener.sawHead);

            CraftingRegistry.addShapedRecipe(new ItemInstance(ItemListener.drill, 1, ItemListener.drill.getDurability()), "PD", "HP", 'P', ItemListener.ironPlate, 'D', ItemListener.drillHead, 'H', ItemListener.powerToolHandle);
            CraftingRegistry.addShapedRecipe(new ItemInstance(ItemListener.saw, 1, ItemListener.drill.getDurability()), "PS", "HP", 'P', ItemListener.ironPlate, 'S', ItemListener.sawHead, 'H', ItemListener.powerToolHandle);
            CraftingRegistry.addShapedRecipe(new ItemInstance(ItemListener.diamondDrill, 1, ItemListener.drill.getDurability()), "PD", "HP", 'P', ItemListener.goldPlate, 'D', ItemListener.diamondDrillHead, 'H', ItemListener.powerToolHandle);
            CraftingRegistry.addShapedRecipe(new ItemInstance(ItemListener.diamondSaw, 1, ItemListener.drill.getDurability()), "PS", "HP", 'P', ItemListener.goldPlate, 'S', ItemListener.diamondSawHead, 'H', ItemListener.powerToolHandle);

            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.generator), " P ", "PBP", "PFP", 'P', ItemListener.ironPlate, 'B', new ItemInstance(ItemListener.battery, 1, -1), 'F', BlockBase.FURNACE);
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.electricFurnace), "PPP", "PFP", "PPP", 'P', ItemListener.ironPlate, 'F', BlockBase.FURNACE);
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.grinder), "PPP", "PSP", "PPP", 'P', ItemListener.ironPlate, 'S', ItemListener.sawHead);
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.press), "PGP", "LLL", "PFP", 'P', ItemListener.ironPlate, 'G', ItemListener.goldGear, 'L', ItemListener.goldPlate, 'F', BlockListener.electricFurnace);
        }
//        else if (event.recipeId.equals(RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type())) {
//        }
    }
}
