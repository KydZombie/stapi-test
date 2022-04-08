package com.github.kydzombie.stapitest.events.init;

import com.github.kydzombie.stapitest.custom.UniqueMaterial;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.event.mod.PreInitEvent;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.item.nbt.StationNBT;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.recipe.SmeltingRegistry;
import net.modificationstation.stationapi.api.registry.Identifier;

public class RecipeListener {
    @EventListener
    public void preInit(PreInitEvent event) {
        StationAPI.EVENT_BUS.post(new RecipeRegisterEvent(Identifier.of(StapiTest.MOD_ID, "electricFurnace")));
        StationAPI.EVENT_BUS.post(new RecipeRegisterEvent(Identifier.of(StapiTest.MOD_ID, "grinder")));
        StationAPI.EVENT_BUS.post(new RecipeRegisterEvent(Identifier.of(StapiTest.MOD_ID, "press")));
        StationAPI.EVENT_BUS.post(new RecipeRegisterEvent(Identifier.of(StapiTest.MOD_ID, "centrifuge")));
    }

    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {
        if (event.recipeId.equals(RecipeRegisterEvent.Vanilla.SMELTING.type())) {
            SmeltingRegistry.addSmeltingRecipe(StapiTest.ironDust.id, new ItemInstance(ItemBase.ironIngot));
            SmeltingRegistry.addSmeltingRecipe(StapiTest.goldDust.id, new ItemInstance(ItemBase.goldIngot));
        } else if (event.recipeId.equals(RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type())) {
            for (UniqueMaterial material:
                 UniqueMaterial.materials.values()) {
                if (material.getCraftingMaterial() != null) {
                    ItemInstance result = new ItemInstance(StapiTest.pickaxe);
                    CompoundTag nbt = StationNBT.cast(result).getStationNBT();
                    nbt.put("material", material.getToolMaterial().name());
                    CraftingRegistry.addShapedRecipe(result, "x", "x", "x", 'x', material.getCraftingMaterial());
                }
            }
        }
        else if (event.recipeId.equals(RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type())) {
            CraftingRegistry.addShapelessRecipe(new ItemInstance(StapiTest.battery), new ItemInstance(StapiTest.portableBattery, 1, -1));
        }
        else if (event.recipeId.equals(Identifier.of(StapiTest.MOD_ID, "grinder"))) {
            StapiTest.grinderRegistry.addRecipe(new ItemInstance(BlockBase.COBBLESTONE), new ItemInstance(BlockBase.GRAVEL));
            StapiTest.grinderRegistry.addRecipe(new ItemInstance(BlockBase.GRAVEL), new ItemInstance(BlockBase.SAND));
            StapiTest.grinderRegistry.addRecipe(new ItemInstance(BlockBase.IRON_ORE), new ItemInstance(StapiTest.ironDust, 2));
            StapiTest.grinderRegistry.addRecipe(new ItemInstance(BlockBase.GOLD_ORE), new ItemInstance(StapiTest.goldDust, 2));
        }
        else if (event.recipeId.equals(Identifier.of(StapiTest.MOD_ID, "press"))) {
        }
        else if (event.recipeId.equals(Identifier.of(StapiTest.MOD_ID, "centrifuge"))) {
            StapiTest.centrifugeRegistry.addRecipe(new ItemInstance(ItemBase.redstoneDust), new ItemInstance(StapiTest.sludge));
        }
    }
}
