package com.github.kydzombie.stapitest.events.init;

import com.github.kydzombie.stapitest.block.cable.ItemCable;
import com.github.kydzombie.stapitest.block.cable.PowerCable;
import com.github.kydzombie.stapitest.block.machine.*;
import com.github.kydzombie.stapitest.custom.util.item.MaterialAgnostic;
import com.github.kydzombie.stapitest.item.Battery;
import com.github.kydzombie.stapitest.item.DynamicItem;
import com.github.kydzombie.stapitest.item.Wrench;
import com.github.kydzombie.stapitest.item.tool.Chainsaw;
import com.github.kydzombie.stapitest.item.tool.Drill;
import com.github.kydzombie.stapitest.item.tool.DynamicTool;
import com.github.kydzombie.stapitest.item.tool.ElectricTool;
import com.github.kydzombie.stapitest.material.Materials;
import com.github.kydzombie.stapitest.recipe.CentrifugeRecipeRegistry;
import com.github.kydzombie.stapitest.recipe.ElectricFurnaceRecipeRegistry;
import com.github.kydzombie.stapitest.recipe.GrinderRecipeRegistry;
import com.github.kydzombie.stapitest.recipe.PressRecipeRegistry;
import com.github.kydzombie.stapitest.tabs.TabUtils;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.modificationstation.stationapi.api.client.color.block.BlockColors;
import net.modificationstation.stationapi.api.client.color.item.ItemColors;
import net.modificationstation.stationapi.api.client.event.color.block.BlockColorsRegisterEvent;
import net.modificationstation.stationapi.api.client.event.color.item.ItemColorsRegisterEvent;
import net.modificationstation.stationapi.api.event.item.ItemMiningSpeedMultiplierOnStateEvent;
import net.modificationstation.stationapi.api.event.mod.PostInitEvent;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
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

    public static TemplateItemBase[] materialState;
    public static MachineBlock[] machines;
    public static TemplateItemBase[] tools;
    public static TemplateItemBase[] electricTools;

    @EventListener
    public void postInit(PostInitEvent event) {
        TabUtils.loadTabs();
    }
    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {
        generator = new Generator(MOD_ID.id("generator"));
        electricFurnace = new ElectricFurnace(MOD_ID.id("electricFurnace"));
        grinder = new Grinder(MOD_ID.id("grinder"));
        press = new Press(MOD_ID.id("press"));
        centrifuge = new Centrifuge(MOD_ID.id("centrifuge"));
        battery = new BatteryBlock(MOD_ID.id("batteryBlock"));

        powerCable = new PowerCable(MOD_ID.id("powerCable"));
        itemCable = new ItemCable(MOD_ID.id("itemCable"));
    }

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        Color goldColor = new Color(255, 255, 11);

        ingot = new DynamicItem(MOD_ID.id("ingot"));
        dust = new DynamicItem(MOD_ID.id("dust"));
        sludge = new DynamicItem(MOD_ID.id("sludge"));
        impureDust = new DynamicItem(MOD_ID.id("impureDust"));

        wrench = new Wrench(MOD_ID.id("wrench"));
        powerToolHandle = new TemplateItemBase(MOD_ID.id("powerToolHandle")).setTranslationKey(MOD_ID, "powerToolHandle");

        Materials.register();

        pickaxe = new DynamicTool(MOD_ID.id("pickaxe"), "mineable/pickaxe");
        axe = new DynamicTool(MOD_ID.id("axe"), "mineable/axe");
        shovel = new DynamicTool(MOD_ID.id("shovel"), "mineable/shovel");
        sword = new DynamicTool(MOD_ID.id("sword"), "mineable/sword");
        hoe = new DynamicTool(MOD_ID.id("hoe"), "mineable/hoe");

        drill = new Drill(MOD_ID.id("drill"));
        saw = new Chainsaw(MOD_ID.id("saw"));

        portableBattery = new Battery(MOD_ID.id("battery"), 400);

        materialState = new TemplateItemBase[] {
                ingot, dust, sludge, impureDust
        };
        machines = new MachineBlock[]{
                generator, electricFurnace,
                grinder, press,
                centrifuge
        };
        tools = new TemplateItemBase[]{
                pickaxe, axe, shovel,
                sword, hoe
        };
        electricTools = new TemplateItemBase[]{
                saw, drill
        };
    }

    @EventListener
    private static void registerBlockColours(BlockColorsRegisterEvent event) {
        BlockColors blockColors = event.blockColors;
        for (MachineBlock machine: machines) {
            blockColors.registerColorProvider((state, world, pos, tintIndex) -> machine.getMachineColor(), machine);
        }
    }

    @EventListener
    private static void registerItemColours(ItemColorsRegisterEvent event) {
        ItemColors itemColors = event.itemColors;
        for (MachineBlock machine: machines) {
            itemColors.register((itemInstance, tintIndex) -> machine.getMachineColor(), machine);
        }
        for (TemplateItemBase tool: tools) {
            itemColors.register((itemInstance, tintIndex) -> tintIndex == 1 ? MaterialAgnostic.getUniqueMaterial(itemInstance).getMaterialColor() : -1, tool);
        }
        for (TemplateItemBase tool: electricTools) {
            itemColors.register((itemInstance, tintIndex) -> tintIndex == 0 ? MaterialAgnostic.getUniqueMaterial(itemInstance).getMaterialColor() : -1, tool);
        }

        for (TemplateItemBase item: materialState) {
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
