package com.github.kydzombie.stapitest.events.init;

import com.github.kydzombie.stapitest.material.Material;
import com.github.kydzombie.stapitest.item.DynamicItem;
import com.github.kydzombie.stapitest.material.Materials;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.event.mod.PreInitEvent;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.recipe.SmeltingRegistry;
import net.modificationstation.stationapi.api.registry.Identifier;

public class RecipeListener {
    @EventListener
    public void preInit(PreInitEvent event) {
        StationAPI.EVENT_BUS.post(RecipeRegisterEvent.builder().recipeId(StapiTest.MOD_ID.id("electricFurnace")).build());
        StationAPI.EVENT_BUS.post(RecipeRegisterEvent.builder().recipeId(StapiTest.MOD_ID.id("grinder")).build());
        StationAPI.EVENT_BUS.post(RecipeRegisterEvent.builder().recipeId(StapiTest.MOD_ID.id("press")).build());
        StationAPI.EVENT_BUS.post(RecipeRegisterEvent.builder().recipeId(StapiTest.MOD_ID.id("centrifuge")).build());
    }

    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {
        if (event.recipeId.equals(RecipeRegisterEvent.Vanilla.SMELTING.type())) {
            SmeltingRegistry.addSmeltingRecipe(DynamicItem.convert(StapiTest.dust, "iron"), new ItemInstance(ItemBase.ironIngot));
            SmeltingRegistry.addSmeltingRecipe(DynamicItem.convert(StapiTest.dust, "gold"), new ItemInstance(ItemBase.goldIngot));
        } else if (event.recipeId.equals(RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type())) {
            for (Material material : Materials.allMaterials()) {
                if (material.getToolMaterial() != null) {
                    ItemInstance result = new ItemInstance(StapiTest.pickaxe);
                    CompoundTag nbt = result.getStationNBT();
                    nbt.put("material", material.name);
//                    CraftingRegistry.addShapedRecipe(result, "XXX", " S ", " S ", 'X', material.getCraftingMaterial(), 'S', ItemBase.stick);
                }
            }
        } else if (event.recipeId.equals(RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type())) {
            CraftingRegistry.addShapelessRecipe(new ItemInstance(StapiTest.battery), new ItemInstance(StapiTest.portableBattery, 1, -1));
            CraftingRegistry.addShapelessRecipe(DynamicItem.convert(StapiTest.ingot, "steel"), ItemBase.coal);
            CraftingRegistry.addShapelessRecipe(DynamicItem.convert(StapiTest.ingot, "redstone"), ItemBase.redstoneDust);
            CraftingRegistry.addShapelessRecipe(DynamicItem.convert(StapiTest.ingot, "stainlessSteel"), ItemBase.stick);
        } else if (event.recipeId.equals(StapiTest.MOD_ID.id("grinder"))) {
            StapiTest.grinderRegistry.addRecipe(new ItemInstance(BlockBase.COBBLESTONE), new ItemInstance(BlockBase.GRAVEL));
            StapiTest.grinderRegistry.addRecipe(new ItemInstance(BlockBase.GRAVEL), new ItemInstance(BlockBase.SAND));
            StapiTest.grinderRegistry.addRecipe(new ItemInstance(BlockBase.IRON_ORE), DynamicItem.convert(StapiTest.dust, "iron", 2));
            StapiTest.grinderRegistry.addRecipe(new ItemInstance(BlockBase.GOLD_ORE), DynamicItem.convert(StapiTest.dust, "gold", 2));
            StapiTest.grinderRegistry.addRecipe(DynamicItem.convert(StapiTest.sludge, "redstone"),
                    DynamicItem.convert(StapiTest.impureDust, "chromium"));
        } else if (event.recipeId.equals(StapiTest.MOD_ID.id("press"))) {
            String prevName = "iron";
            for (String name: Materials.allNames()) {
                StapiTest.pressRegistry.addRecipe(
                        DynamicItem.convert(StapiTest.ingot, prevName),
                        DynamicItem.convert(StapiTest.ingot, name)
                );
                prevName = name;
            }
        } else if (event.recipeId.equals(StapiTest.MOD_ID.id("centrifuge"))) {
            StapiTest.centrifugeRegistry.addRecipe(new ItemInstance(ItemBase.redstoneDust), DynamicItem.convert(StapiTest.sludge, "redstone"));
        }
    }
}
