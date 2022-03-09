package com.github.kydzombie.stapitest.events.init;

import com.github.kydzombie.stapitest.recipe.GrinderRecipeRegistry;
import com.github.kydzombie.stapitest.recipe.PressRecipeRegistry;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.recipe.SmeltingRegistry;
import net.modificationstation.stationapi.api.registry.Identifier;

public class RecipeListener {

    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {
        if (event.recipeId.equals(RecipeRegisterEvent.Vanilla.SMELTING.type())) {
            SmeltingRegistry.addSmeltingRecipe(StapiTest.ironDust.id, new ItemInstance(ItemBase.ironIngot));
            SmeltingRegistry.addSmeltingRecipe(StapiTest.goldDust.id, new ItemInstance(ItemBase.goldIngot));
        } else if (event.recipeId.equals(RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type())) {
            CraftingRegistry.addShapedRecipe(new ItemInstance(StapiTest.cableItem), "XXX", 'X', BlockBase.WOOL);
            CraftingRegistry.addShapedRecipe(new ItemInstance(StapiTest.powerCableItem), "XXX", 'X', new ItemInstance(BlockBase.WOOL, 1, 15));
            CraftingRegistry.addShapedRecipe(new ItemInstance(StapiTest.itemCableItem), "XXX", 'X', new ItemInstance(BlockBase.WOOL, 1, 5));

            CraftingRegistry.addShapedRecipe(new ItemInstance(StapiTest.ironPlate, 2), "X", "X", "X", 'X', ItemBase.ironIngot);
            CraftingRegistry.addShapedRecipe(new ItemInstance(StapiTest.goldPlate, 2), "X", "X", "X", 'X', ItemBase.goldIngot);

            CraftingRegistry.addShapedRecipe(new ItemInstance(StapiTest.ironGear), " X ", "XSX", " X ", 'X', ItemBase.ironIngot, 'S', ItemBase.stick);
            CraftingRegistry.addShapedRecipe(new ItemInstance(StapiTest.goldGear), " X ", "XSX", " X ", 'X', ItemBase.goldIngot, 'S', ItemBase.stick);

            CraftingRegistry.addShapedRecipe(new ItemInstance(StapiTest.wrench), "I I", " G ", " I ", 'I', ItemBase.ironIngot, 'G', StapiTest.goldGear);

            CraftingRegistry.addShapedRecipe(new ItemInstance(StapiTest.batteryItem, 1, StapiTest.batteryItem.getDurability()), " C ", "PRP", "PRP", 'C', StapiTest.powerCableItem, 'P', StapiTest.ironPlate, 'R', ItemBase.redstoneDust);

            CraftingRegistry.addShapedRecipe(new ItemInstance(StapiTest.powerToolHandle), "XGX", "XBX", "XVX", 'X', StapiTest.ironPlate, 'G', StapiTest.goldGear, 'B', new ItemInstance(StapiTest.battery, 1, -1), 'V', StapiTest.goldPlate);
            CraftingRegistry.addShapedRecipe(new ItemInstance(StapiTest.drillHead), " XX", "XRX", "RX ", 'X', StapiTest.ironPlate, 'R', StapiTest.goldGear);
            CraftingRegistry.addShapedRecipe(new ItemInstance(StapiTest.sawHead), " XX", "XRX", "RX ", 'X', StapiTest.ironGear, 'R', ItemBase.redstoneDust);
            CraftingRegistry.addShapedRecipe(new ItemInstance(StapiTest.diamondDrillHead), " D ", "DHD", " D ", 'D', ItemBase.diamond, 'H', StapiTest.drillHead);
            CraftingRegistry.addShapedRecipe(new ItemInstance(StapiTest.diamondSawHead), " D ", "DHD", " D ", 'D', ItemBase.diamond, 'H', StapiTest.sawHead);

            CraftingRegistry.addShapedRecipe(new ItemInstance(StapiTest.drill, 1, StapiTest.drill.getDurability()), "PD", "HP", 'P', StapiTest.ironPlate, 'D', StapiTest.drillHead, 'H', StapiTest.powerToolHandle);
            CraftingRegistry.addShapedRecipe(new ItemInstance(StapiTest.saw, 1, StapiTest.drill.getDurability()), "PS", "HP", 'P', StapiTest.ironPlate, 'S', StapiTest.sawHead, 'H', StapiTest.powerToolHandle);
            CraftingRegistry.addShapedRecipe(new ItemInstance(StapiTest.diamondDrill, 1, StapiTest.diamondDrill.getDurability()), "PD", "HP", 'P', StapiTest.goldPlate, 'D', StapiTest.diamondDrillHead, 'H', StapiTest.powerToolHandle);
            CraftingRegistry.addShapedRecipe(new ItemInstance(StapiTest.diamondSaw, 1, StapiTest.diamondSaw.getDurability()), "PS", "HP", 'P', StapiTest.goldPlate, 'S', StapiTest.diamondSawHead, 'H', StapiTest.powerToolHandle);

            CraftingRegistry.addShapedRecipe(new ItemInstance(StapiTest.generator), " P ", "PBP", "PFP", 'P', StapiTest.ironPlate, 'B', new ItemInstance(StapiTest.battery, 1, -1), 'F', BlockBase.FURNACE);
            CraftingRegistry.addShapedRecipe(new ItemInstance(StapiTest.electricFurnace), "PPP", "PFP", "PPP", 'P', StapiTest.ironPlate, 'F', BlockBase.FURNACE);
            CraftingRegistry.addShapedRecipe(new ItemInstance(StapiTest.grinder), "PPP", "PSP", "PPP", 'P', StapiTest.ironPlate, 'S', StapiTest.sawHead);
            CraftingRegistry.addShapedRecipe(new ItemInstance(StapiTest.press), "PGP", "LLL", "PFP", 'P', StapiTest.ironPlate, 'G', StapiTest.goldGear, 'L', StapiTest.goldPlate, 'F', StapiTest.electricFurnace);
        }
        else if (event.recipeId.equals(RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type())) {
            CraftingRegistry.addShapelessRecipe(new ItemInstance(StapiTest.battery), new ItemInstance(StapiTest.batteryItem, 1, -1));
        }
        else if (event.recipeId.equals(Identifier.of(StapiTest.MOD_ID, "grinder"))) {
            GrinderRecipeRegistry.addRecipe(new ItemInstance(BlockBase.COBBLESTONE), new ItemInstance(BlockBase.GRAVEL));
            GrinderRecipeRegistry.addRecipe(new ItemInstance(BlockBase.GRAVEL), new ItemInstance(BlockBase.SAND));
            GrinderRecipeRegistry.addRecipe(new ItemInstance(BlockBase.IRON_ORE), new ItemInstance(StapiTest.ironDust, 2));
            GrinderRecipeRegistry.addRecipe(new ItemInstance(BlockBase.GOLD_ORE), new ItemInstance(StapiTest.goldDust, 2));
        }
        else if (event.recipeId.equals(Identifier.of(StapiTest.MOD_ID, "press"))) {
            PressRecipeRegistry.addRecipe(new ItemInstance(ItemBase.ironIngot), new ItemInstance(StapiTest.ironPlate));
            PressRecipeRegistry.addRecipe(new ItemInstance(ItemBase.goldIngot), new ItemInstance(StapiTest.goldPlate));
            PressRecipeRegistry.addRecipe(new ItemInstance(StapiTest.ironPlate), new ItemInstance(StapiTest.ironGear));
            PressRecipeRegistry.addRecipe(new ItemInstance(StapiTest.goldPlate), new ItemInstance(StapiTest.goldGear));
        }
    }
}
