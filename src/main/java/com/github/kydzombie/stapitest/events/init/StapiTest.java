package com.github.kydzombie.stapitest.events.init;

import com.github.kydzombie.stapitest.block.cable.Cable;
import com.github.kydzombie.stapitest.block.cable.ItemCable;
import com.github.kydzombie.stapitest.block.cable.PowerCable;
import com.github.kydzombie.stapitest.block.machine.*;
import com.github.kydzombie.stapitest.item.Battery;
import com.github.kydzombie.stapitest.item.CableBlockItem;
import com.github.kydzombie.stapitest.item.ColoredItem;
import com.github.kydzombie.stapitest.item.Wrench;
import com.github.kydzombie.stapitest.item.tool.ElectricPickaxe;
import com.github.kydzombie.stapitest.item.tool.ElectricTool;
import com.github.kydzombie.stapitest.item.tool.ToolPart;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.event.mod.PreInitEvent;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.item.tool.ToolMaterialFactory;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.tags.TagRegistry;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;
import net.modificationstation.stationapi.api.template.item.TemplateSecondaryBlock;
import net.modificationstation.stationapi.api.template.item.tool.TemplateToolBase;
import net.modificationstation.stationapi.api.util.Null;

import java.awt.*;

public class StapiTest {

    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();
    public static BlockBase generator;
    public static BlockBase electricFurnace;
    public static BlockBase grinder;
    public static BlockBase press;
    public static BlockBase battery;
    public static BlockBase cable;
    public static BlockBase powerCable;
    public static BlockBase itemCable;
    public static TemplateItemBase ironDust;
    public static TemplateItemBase goldDust;
    public static TemplateItemBase ironPlate;
    public static TemplateItemBase goldPlate;
    public static TemplateItemBase ironGear;
    public static TemplateItemBase goldGear;
    public static TemplateItemBase powerToolHandle;
    public static TemplateItemBase drillHead;
    public static TemplateItemBase sawHead;
    public static TemplateItemBase diamondDrillHead;
    public static TemplateItemBase diamondSawHead;
    public static TemplateItemBase wrench;
    public static TemplateItemBase batteryItem;
    public static TemplateToolBase electricPickaxe;
    public static ToolMaterial basePowerTool;
    public static ToolMaterial diamondPowerTool;
    public static TemplateItemBase drill;
    public static TemplateItemBase diamondDrill;
    public static TemplateItemBase saw;
    public static TemplateItemBase diamondSaw;
    public static TemplateSecondaryBlock cableItem;
    public static TemplateSecondaryBlock powerCableItem;
    public static TemplateSecondaryBlock itemCableItem;

    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {

        generator = new Generator(Identifier.of(MOD_ID, "generator"));
        electricFurnace = new ElectricFurnace(Identifier.of(MOD_ID, "electricFurnace"));
        grinder = new Grinder(Identifier.of(MOD_ID, "grinder"));
        press = new Press(Identifier.of(MOD_ID, "press"));
        battery = new BatteryBlock(Identifier.of(MOD_ID, "batteryBlock"));

        cable = new Cable(Identifier.of(MOD_ID, "cable"));
        powerCable = new PowerCable(Identifier.of(MOD_ID, "powerCable"));
        itemCable = new ItemCable(Identifier.of(MOD_ID, "itemCable"));
    }

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        Color goldColor = new Color(255, 255, 11);

        wrench = new Wrench(Identifier.of(MOD_ID, "wrench"));

        electricPickaxe = new ElectricPickaxe(Identifier.of(MOD_ID, "electricPickaxe"), ToolMaterial.IRON, 800);

        basePowerTool = ToolMaterialFactory.create("drill", 2, 1200, 8.0F, 2);
        diamondPowerTool = ToolMaterialFactory.create("diamondDrill", 3, 2400, 14.0F, 3);

        drill = new ElectricTool(Identifier.of(MOD_ID, "drill"), basePowerTool);
        diamondDrill = new ElectricTool(Identifier.of(MOD_ID, "diamondDrill"), diamondPowerTool);

        saw = new ElectricTool(Identifier.of(MOD_ID, "saw"), basePowerTool);
        diamondSaw = new ElectricTool(Identifier.of(MOD_ID, "diamondSaw"), diamondPowerTool);

        TagRegistry.INSTANCE.register(Identifier.of("tools/pickaxes"), new ItemInstance(electricPickaxe), e -> e.itemId == electricPickaxe.id);

        TagRegistry.INSTANCE.register(Identifier.of("tools/pickaxes"), new ItemInstance(drill), e -> e.itemId == drill.id);
        TagRegistry.INSTANCE.register(Identifier.of("tools/shovels"), new ItemInstance(drill), e -> e.itemId == drill.id);
        TagRegistry.INSTANCE.register(Identifier.of("tools/pickaxes"), new ItemInstance(diamondDrill), e -> e.itemId == diamondDrill.id);
        TagRegistry.INSTANCE.register(Identifier.of("tools/shovels"), new ItemInstance(diamondDrill), e -> e.itemId == diamondDrill.id);

        TagRegistry.INSTANCE.register(Identifier.of("tools/axes"), new ItemInstance(saw), e -> e.itemId == saw.id);
        TagRegistry.INSTANCE.register(Identifier.of("tools/axes"), new ItemInstance(diamondSaw), e -> e.itemId == diamondSaw.id);

        batteryItem = new Battery(Identifier.of(MOD_ID, "battery"), 400);

        ironDust = new ColoredItem(Identifier.of(MOD_ID, "ironDust"), Color.WHITE);
        goldDust = new ColoredItem(Identifier.of(MOD_ID, "goldDust"), goldColor);

        ironPlate = new ColoredItem(Identifier.of(MOD_ID, "ironPlate"), Color.WHITE);
        goldPlate = new ColoredItem(Identifier.of(MOD_ID, "goldPlate"), goldColor);

        ironGear = new ColoredItem(Identifier.of(MOD_ID, "ironGear"), Color.WHITE);
        goldGear = new ColoredItem(Identifier.of(MOD_ID, "goldGear"), goldColor);

        powerToolHandle = new ToolPart(Identifier.of(MOD_ID, "powerToolHandle"));
        drillHead = new ToolPart(Identifier.of(MOD_ID, "drillHead"));
        sawHead = new ToolPart(Identifier.of(MOD_ID, "sawHead"));
        diamondDrillHead = new ToolPart(Identifier.of(MOD_ID, "diamondDrillHead"));
        diamondSawHead = new ToolPart(Identifier.of(MOD_ID, "diamondSawHead"));

        // Secondary Blocks (BlockItems)
        cableItem = new CableBlockItem(Identifier.of(MOD_ID, "cable"), StapiTest.cable, Color.WHITE);
        powerCableItem = new CableBlockItem(Identifier.of(MOD_ID, "powerCable"), StapiTest.powerCable, new Color(37, 33, 33, 255));
        itemCableItem = new CableBlockItem(Identifier.of(MOD_ID, "itemCable"), StapiTest.itemCable, Color.GREEN);
    }

    @EventListener
    public void preInit(PreInitEvent event) {
        StationAPI.EVENT_BUS.post(new RecipeRegisterEvent(Identifier.of(MOD_ID, "grinder")));
        StationAPI.EVENT_BUS.post(new RecipeRegisterEvent(Identifier.of(MOD_ID, "press")));
    }

}
