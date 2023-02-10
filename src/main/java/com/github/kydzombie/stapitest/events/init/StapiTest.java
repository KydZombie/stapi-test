package com.github.kydzombie.stapitest.events.init;

import com.github.kydzombie.stapitest.block.cable.ItemCable;
import com.github.kydzombie.stapitest.block.cable.PowerCable;
import com.github.kydzombie.stapitest.block.machine.*;
import com.github.kydzombie.stapitest.custom.UniqueMaterial;
import com.github.kydzombie.stapitest.custom.util.ColorConverter;
import com.github.kydzombie.stapitest.custom.util.item.MaterialAgnostic;
import com.github.kydzombie.stapitest.item.Battery;
import com.github.kydzombie.stapitest.item.DynamicItem;
import com.github.kydzombie.stapitest.item.Wrench;
import com.github.kydzombie.stapitest.item.tool.Chainsaw;
import com.github.kydzombie.stapitest.item.tool.Drill;
import com.github.kydzombie.stapitest.item.tool.DynamicTool;
import com.github.kydzombie.stapitest.item.tool.ElectricTool;
import com.github.kydzombie.stapitest.recipe.CentrifugeRecipeRegistry;
import com.github.kydzombie.stapitest.recipe.ElectricFurnaceRecipeRegistry;
import com.github.kydzombie.stapitest.recipe.GrinderRecipeRegistry;
import com.github.kydzombie.stapitest.recipe.PressRecipeRegistry;
import com.github.kydzombie.stapitest.tabs.TabUtils;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.client.color.block.BlockColors;
import net.modificationstation.stationapi.api.client.color.item.ItemColors;
import net.modificationstation.stationapi.api.client.event.color.block.BlockColorsRegisterEvent;
import net.modificationstation.stationapi.api.client.event.color.item.ItemColorsRegisterEvent;
import net.modificationstation.stationapi.api.event.item.ItemMiningSpeedMultiplierOnStateEvent;
import net.modificationstation.stationapi.api.event.mod.PostInitEvent;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.item.tool.ToolMaterialFactory;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;
import net.modificationstation.stationapi.api.util.Null;

import java.awt.*;

public class StapiTest {

    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();
    public static MachineBlock generator;
    public static MachineBlock electricFurnace;
    public static MachineBlock grinder;
    public static MachineBlock press;
    public static MachineBlock centrifuge;
    public static MachineBlock battery;

    public static BlockBase powerCable;
    public static BlockBase itemCable;

    public static TemplateItemBase ingot;
    public static TemplateItemBase dust;
    public static TemplateItemBase sludge;
    public static TemplateItemBase impureDust;

    public static TemplateItemBase wrench;
    public static TemplateItemBase portableBattery;
    public static TemplateItemBase powerToolHandle;

    public static TemplateItemBase pickaxe;
    public static TemplateItemBase axe;
    public static TemplateItemBase shovel;
    public static TemplateItemBase sword;
    public static TemplateItemBase hoe;
    public static TemplateItemBase drill;
    public static TemplateItemBase saw;

    public static ElectricFurnaceRecipeRegistry eFurnaceRegistry = new ElectricFurnaceRecipeRegistry();
    public static GrinderRecipeRegistry grinderRegistry = new GrinderRecipeRegistry();
    public static PressRecipeRegistry pressRegistry = new PressRecipeRegistry();
    public static CentrifugeRecipeRegistry centrifugeRegistry = new CentrifugeRecipeRegistry();

    @EventListener
    public void postInit(PostInitEvent event) {
        TabUtils.loadTabs();
    }
    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {
        generator = new Generator(Identifier.of(MOD_ID, "generator"));
        electricFurnace = new ElectricFurnace(Identifier.of(MOD_ID, "electricFurnace"));
        grinder = new Grinder(Identifier.of(MOD_ID, "grinder"));
        press = new Press(Identifier.of(MOD_ID, "press"));
        centrifuge = new Centrifuge(Identifier.of(MOD_ID, "centrifuge"));
        battery = new BatteryBlock(Identifier.of(MOD_ID, "batteryBlock"));

        powerCable = new PowerCable(Identifier.of(MOD_ID, "powerCable"));
        itemCable = new ItemCable(Identifier.of(MOD_ID, "itemCable"));
    }

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        Color goldColor = new Color(255, 255, 11);

        ingot = new DynamicItem(Identifier.of(MOD_ID, "ingot"));
        dust = new DynamicItem(Identifier.of(MOD_ID, "dust"));
        sludge = new DynamicItem(Identifier.of(MOD_ID, "sludge"));
        impureDust = new DynamicItem(Identifier.of(MOD_ID, "impureDust"));

        wrench = new Wrench(Identifier.of(MOD_ID, "wrench"));
        powerToolHandle = new TemplateItemBase(Identifier.of(MOD_ID, "powerToolHandle")).setTranslationKey(MOD_ID, "powerToolHandle");

        UniqueMaterial.registerNewUniqueMaterial(0, ToolMaterialFactory.create("missingMaterial", 0, 0, 0, 0));
        UniqueMaterial.registerNewUniqueMaterial(-1, ToolMaterial.field_1690, "iron").setCraftingMaterial(ItemBase.ironIngot);
        UniqueMaterial.registerNewUniqueMaterial(ColorConverter.colorToInt(new Color(0x4AEDD9)), ToolMaterial.field_1691, "diamond").setCraftingMaterial(ItemBase.diamond);
        UniqueMaterial.registerNewUniqueMaterial(ColorConverter.colorToInt(new Color(0xFFFF0B)), ToolMaterial.field_1692, "gold").setCraftingMaterial(ItemBase.goldIngot);
        UniqueMaterial.registerNewUniqueMaterial(ColorConverter.colorToInt(new Color(0xFF6D6D)), "redstone");
        UniqueMaterial.registerNewUniqueMaterial(ColorConverter.colorToInt(new Color(0xCFDBC5)), "chromium");
        UniqueMaterial.registerNewUniqueMaterial(ColorConverter.colorToInt(new Color(0x3B3B3B)), ToolMaterialFactory.create("steel", 3, 1561, 8.0F, 3)).setCraftingMaterial(DynamicItem.convert(ingot, "steel"));
        UniqueMaterial.registerNewUniqueMaterial(ColorConverter.colorToInt(new Color(0x898383)), ToolMaterialFactory.create("stainlessSteel", 3, 1561, 8.0F, 3)).setCraftingMaterial(DynamicItem.convert(ingot, "stainlessSteel"));

        pickaxe = new DynamicTool(Identifier.of(MOD_ID, "pickaxe"), "mineable/pickaxe");
        axe = new DynamicTool(Identifier.of(MOD_ID, "axe"), "mineable/axe");
        shovel = new DynamicTool(Identifier.of(MOD_ID, "shovel"), "mineable/shovel");
        sword = new DynamicTool(Identifier.of(MOD_ID, "sword"), "mineable/sword");
        hoe = new DynamicTool(Identifier.of(MOD_ID, "hoe"), "mineable/hoe");

        drill = new Drill(Identifier.of(MOD_ID, "drill"));
        saw = new Chainsaw(Identifier.of(MOD_ID, "saw"));

        portableBattery = new Battery(Identifier.of(MOD_ID, "battery"), 400);

    }

    @EventListener
    private static void registerBlockColours(BlockColorsRegisterEvent event) {
        BlockColors blockColors = event.blockColors;
        for (MachineBlock machine: new MachineBlock[] {
                grinder,
                press,
                electricFurnace,
                centrifuge,
                generator
             }) {
            blockColors.registerColorProvider((state, world, pos, tintIndex) -> machine.getMachineColor(), machine);
        }
    }

    @EventListener
    private static void registerItemColours(ItemColorsRegisterEvent event) {
        ItemColors itemColors = event.itemColors;
        for (MachineBlock machine: new MachineBlock[] {
                grinder,
                press,
                electricFurnace,
                centrifuge,
                generator
        }) {
            itemColors.register((itemInstance, tintIndex) -> machine.getMachineColor(), machine);
        }
        for (TemplateItemBase tool: new TemplateItemBase[] {
                pickaxe,
                axe,
                shovel,
                sword,
                hoe
        }) {
            itemColors.register((itemInstance, tintIndex) -> tintIndex == 1 ? MaterialAgnostic.getUniqueMaterial(itemInstance).getMaterialColor() : -1, tool);
        }
        for (TemplateItemBase tool: new TemplateItemBase[] {
                drill,
                saw
        }) {
            itemColors.register((itemInstance, tintIndex) -> tintIndex == 0 ? MaterialAgnostic.getUniqueMaterial(itemInstance).getMaterialColor() : -1, tool);
        }

        for (TemplateItemBase item: new TemplateItemBase[] {
                ingot,
                dust,
                sludge,
                impureDust
        }) {
            itemColors.register((itemInstance, tintIndex) -> MaterialAgnostic.getUniqueMaterial(itemInstance).getMaterialColor(), item);
        }

    }

    @EventListener
    private static void strengthOnBlock(ItemMiningSpeedMultiplierOnStateEvent event) {
        if ((event.itemStack.getType() instanceof DynamicTool) &&
                DynamicTool.getDurability(event.itemStack) < 1 ||
                (event.itemStack.getType() instanceof ElectricTool &&
                        ((ElectricTool) event.itemStack.getType()).getCurrentPower(event.itemStack) < 5)
        ) {
            event.miningSpeedMultiplier = 0;
        }
    }

}
