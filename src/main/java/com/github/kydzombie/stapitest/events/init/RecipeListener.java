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

import java.util.Arrays;

import static com.github.kydzombie.stapitest.item.DynamicItem.*;

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
            SmeltingRegistry.addSmeltingRecipe(DynamicItem.dust("iron"), new ItemInstance(ItemBase.ironIngot));
            SmeltingRegistry.addSmeltingRecipe(DynamicItem.dust("gold"), new ItemInstance(ItemBase.goldIngot));
        } else if (event.recipeId.equals(RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type())) {
            ItemInstance result = new ItemInstance(StapiTest.pickaxe);
            CompoundTag nbt = result.getStationNBT();
            nbt.put("material", "steel");
            CraftingRegistry.addShapedRecipe(result, "XXX", " S ", " S ", 'X', DynamicItem.ingot("steel"), 'S', ItemBase.stick);

//            for (Material material : Materials.allMaterials()) {
//                if (material.getToolMaterial() != null) {
//                    ItemInstance result = new ItemInstance(StapiTest.pickaxe);
//                    CompoundTag nbt = result.getStationNBT();
//                    nbt.put("material", material.name);
////                    CraftingRegistry.addShapedRecipe(result, "XXX", " S ", " S ", 'X', material.getCraftingMaterial(), 'S', ItemBase.stick);
//                }
//            }
        } else if (event.recipeId.equals(RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type())) {
            CraftingRegistry.addShapelessRecipe(new ItemInstance(StapiTest.battery), new ItemInstance(StapiTest.portableBattery, 1, -1));
            CraftingRegistry.addShapelessRecipe(DynamicItem.ingot("steel"), ItemBase.coal);
            CraftingRegistry.addShapelessRecipe(DynamicItem.ingot("redstone"), ItemBase.redstoneDust);
            CraftingRegistry.addShapelessRecipe(DynamicItem.ingot("stainlessSteel"), ItemBase.stick);
        } else if (event.recipeId.equals(StapiTest.MOD_ID.id("grinder"))) {
            StapiTest.grinderRegistry.addRecipe(new ItemInstance(BlockBase.COBBLESTONE), new ItemInstance(BlockBase.GRAVEL));
            StapiTest.grinderRegistry.addRecipe(new ItemInstance(BlockBase.GRAVEL), new ItemInstance(BlockBase.SAND));
            StapiTest.grinderRegistry.addRecipe(new ItemInstance(BlockBase.IRON_ORE), DynamicItem.dust("iron", 2));
            StapiTest.grinderRegistry.addRecipe(new ItemInstance(BlockBase.GOLD_ORE), DynamicItem.dust("gold", 2));
            StapiTest.grinderRegistry.addRecipe(DynamicItem.sludge("redstone"),
                    DynamicItem.impureDust("chromium"));
        } else if (event.recipeId.equals(StapiTest.MOD_ID.id("press"))) {
            String prevName = "iron";
            for (String name: Arrays.stream(Materials.allNames()).sorted().toList()) {
                StapiTest.pressRegistry.addRecipe(
                        DynamicItem.ingot(prevName),
                        DynamicItem.ingot(name)
                );
                prevName = name;
            }
        } else if (event.recipeId.equals(StapiTest.MOD_ID.id("centrifuge"))) {
            StapiTest.centrifugeRegistry.addRecipe(new ItemInstance(ItemBase.redstoneDust), DynamicItem.sludge("redstone"));
        }
    }
}
