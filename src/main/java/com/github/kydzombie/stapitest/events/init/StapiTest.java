package com.github.kydzombie.stapitest.events.init;

import com.github.kydzombie.stapitest.block.cable.*;
import com.github.kydzombie.stapitest.block.machine.*;
import com.github.kydzombie.stapitest.custom.UniqueMaterial;
import com.github.kydzombie.stapitest.custom.util.ColorConverter;
import com.github.kydzombie.stapitest.custom.util.item.MaterialAgnostic;
import com.github.kydzombie.stapitest.item.DynamicItem;
import com.github.kydzombie.stapitest.item.Battery;
import com.github.kydzombie.stapitest.item.ColoredItem;
import com.github.kydzombie.stapitest.item.Wrench;
import com.github.kydzombie.stapitest.item.tool.Chainsaw;
import com.github.kydzombie.stapitest.item.tool.ElectricTool;
import com.github.kydzombie.stapitest.item.tool.DynamicTool;
import com.github.kydzombie.stapitest.recipe.CentrifugeRecipeRegistry;
import com.github.kydzombie.stapitest.recipe.ElectricFurnaceRecipeRegistry;
import com.github.kydzombie.stapitest.recipe.GrinderRecipeRegistry;
import com.github.kydzombie.stapitest.recipe.PressRecipeRegistry;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.client.colour.block.BlockColours;
import net.modificationstation.stationapi.api.client.colour.item.ItemColours;
import net.modificationstation.stationapi.api.client.event.colour.block.BlockColoursRegisterEvent;
import net.modificationstation.stationapi.api.client.event.colour.item.ItemColoursRegisterEvent;
import net.modificationstation.stationapi.api.event.item.ItemStrengthOnBlockEvent;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.item.tool.ToolMaterialFactory;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.tags.TagRegistry;
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
        UniqueMaterial.registerNewUniqueMaterial(ColorConverter.colorToInt(new Color(0xEFCA2B)), ToolMaterial.field_1692, "gold").setCraftingMaterial(ItemBase.goldIngot);
        UniqueMaterial.registerNewUniqueMaterial(ColorConverter.colorToInt(new Color(0xFF6D6D)), "redstone");
        UniqueMaterial.registerNewUniqueMaterial(ColorConverter.colorToInt(new Color(0xCFDBC5)), "chromium");
        UniqueMaterial.registerNewUniqueMaterial(ColorConverter.colorToInt(new Color(0x3B3B3B)), ToolMaterialFactory.create("steel", 3, 1561, 8.0F, 3)).setCraftingMaterial(DynamicItem.convert(ingot, "steel"));
        UniqueMaterial.registerNewUniqueMaterial(ColorConverter.colorToInt(new Color(0x898383)), ToolMaterialFactory.create("stainlessSteel", 3, 1561, 8.0F, 3)).setCraftingMaterial(DynamicItem.convert(ingot, "steel"));

        pickaxe = new DynamicTool(Identifier.of(MOD_ID, "pickaxe"));
        axe = new DynamicTool(Identifier.of(MOD_ID, "axe"));
        shovel = new DynamicTool(Identifier.of(MOD_ID, "shovel"));
        sword = new DynamicTool(Identifier.of(MOD_ID, "sword"));
        hoe = new DynamicTool(Identifier.of(MOD_ID, "hoe"));

        drill = new ElectricTool(Identifier.of(MOD_ID, "drill"));
        saw = new Chainsaw(Identifier.of(MOD_ID, "saw"));

        TagRegistry.INSTANCE.register(Identifier.of("tools/pickaxes"), new ItemInstance(pickaxe), e -> e.itemId == pickaxe.id);
        TagRegistry.INSTANCE.register(Identifier.of("tools/axes"), new ItemInstance(axe), e -> e.itemId == axe.id);
        TagRegistry.INSTANCE.register(Identifier.of("tools/shovels"), new ItemInstance(shovel), e -> e.itemId == shovel.id);
        TagRegistry.INSTANCE.register(Identifier.of("tools/swords"), new ItemInstance(sword), e -> e.itemId == sword.id);
        TagRegistry.INSTANCE.register(Identifier.of("tools/hoes"), new ItemInstance(hoe), e -> e.itemId == hoe.id);

        TagRegistry.INSTANCE.register(Identifier.of("tools/pickaxes"), new ItemInstance(drill), e -> e.itemId == drill.id);
        TagRegistry.INSTANCE.register(Identifier.of("tools/shovels"), new ItemInstance(drill), e -> e.itemId == drill.id);

        TagRegistry.INSTANCE.register(Identifier.of("tools/axes"), new ItemInstance(saw), e -> e.itemId == saw.id);

        portableBattery = new Battery(Identifier.of(MOD_ID, "battery"), 400);
    }

    @EventListener
    private static void registerBlockColours(BlockColoursRegisterEvent event) {
        BlockColours blockColours = event.getBlockColours();
        for (MachineBlock machine: new MachineBlock[] {
                grinder,
                press,
                electricFurnace,
                centrifuge,
                generator
             }) {
            blockColours.registerColourProvider((state, world, pos, tintIndex) -> machine.getMachineColor(), machine);
        }
    }

    @EventListener
    private static void registerItemColours(ItemColoursRegisterEvent event) {
        ItemColours itemColours = event.getItemColours();
        for (MachineBlock machine: new MachineBlock[] {
                grinder,
                press,
                electricFurnace,
                centrifuge,
                generator
        }) {
            itemColours.register((itemInstance, tintIndex) -> machine.getMachineColor(), machine);
        }
        for (TemplateItemBase tool: new TemplateItemBase[] {
                pickaxe,
                axe,
                shovel,
                sword,
                hoe
        }) {
            itemColours.register((itemInstance, tintIndex) -> tintIndex == 1 ? MaterialAgnostic.getUniqueMaterial(itemInstance).getMaterialColor() : -1, tool);
        }
        for (TemplateItemBase tool: new TemplateItemBase[] {
                drill,
                saw
        }) {
            itemColours.register((itemInstance, tintIndex) -> tintIndex == 0 ? MaterialAgnostic.getUniqueMaterial(itemInstance).getMaterialColor() : -1, tool);
        }

        for (TemplateItemBase item: new TemplateItemBase[] {
                ingot,
                dust,
                sludge,
                impureDust
        }) {
            itemColours.register((itemInstance, tintIndex) -> MaterialAgnostic.getUniqueMaterial(itemInstance).getMaterialColor(), item);
        }

    }

    @EventListener
    private static void strengthOnBlock(ItemStrengthOnBlockEvent event) {
        if ((event.itemInstance.getType() instanceof DynamicTool) &&
                DynamicTool.getDurability(event.itemInstance) < 1 ||
                (event.itemInstance.getType() instanceof ElectricTool &&
                        ((ElectricTool) event.itemInstance.getType()).getCurrentPower(event.itemInstance) < 5)
        ) {
            event.strength = 0;
        }
    }

}
