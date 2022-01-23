package com.github.kydzombie.stapitest.events.init;

import com.github.kydzombie.stapitest.item.Battery;
import com.github.kydzombie.stapitest.item.CableBlockItem;
import com.github.kydzombie.stapitest.item.Dust;
import com.github.kydzombie.stapitest.item.Wrench;
import com.github.kydzombie.stapitest.item.tool.ElectricTool;
import com.github.kydzombie.stapitest.item.tool.ElectricPickaxe;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.tool.ToolMaterial;
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

public class ItemListener {

    public static TemplateItemBase ironDust;
    public static TemplateItemBase goldDust;

    public static TemplateItemBase wrench;

    public static TemplateItemBase battery;

    public static TemplateToolBase electricPickaxe;

    public static TemplateItemBase drill;
    public static TemplateItemBase diamondDrill;
    public static TemplateItemBase saw;
    public static TemplateItemBase diamondSaw;


    public static TemplateSecondaryBlock cable;
    public static TemplateSecondaryBlock powerCable;
    public static TemplateSecondaryBlock itemCable;

    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        wrench = new Wrench(Identifier.of(MOD_ID, "wrench")).setTranslationKey(MOD_ID, "wrench");

        electricPickaxe = new ElectricPickaxe(Identifier.of(MOD_ID, "electricPickaxe"), ToolMaterial.IRON, 800).setTranslationKey(MOD_ID, "electricPickaxe");

        drill = new ElectricTool(Identifier.of(MOD_ID, "drill"), ToolMaterialFactory.create("drill", 2, 1200, 8.0F, 2)).setTranslationKey(MOD_ID, "drill");
        diamondDrill = new ElectricTool(Identifier.of(MOD_ID, "diamondDrill"), ToolMaterialFactory.create("diamond_drill", 3, 2400, 14.0F, 3)).setTranslationKey(MOD_ID, "diamondDrill");

        saw = new ElectricTool(Identifier.of(MOD_ID, "saw"), ToolMaterialFactory.create("saw", 2, 1200, 8.0F, 2)).setTranslationKey(MOD_ID, "saw");
        diamondSaw = new ElectricTool(Identifier.of(MOD_ID, "diamondSaw"), ToolMaterialFactory.create("diamond_saw", 3, 2400, 14.0F, 3)).setTranslationKey(MOD_ID, "diamondSaw");

        TagRegistry.INSTANCE.register(Identifier.of("items/tools/pickaxes"), e -> e.itemId == electricPickaxe.id);

        TagRegistry.INSTANCE.register(Identifier.of("items/tools/pickaxes"), e -> e.itemId == drill.id);
        TagRegistry.INSTANCE.register(Identifier.of("items/tools/shovels"), e -> e.itemId == drill.id);
        TagRegistry.INSTANCE.register(Identifier.of("items/tools/pickaxes"), e -> e.itemId == diamondDrill.id);
        TagRegistry.INSTANCE.register(Identifier.of("items/tools/shovels"), e -> e.itemId == diamondDrill.id);

        TagRegistry.INSTANCE.register(Identifier.of("items/tools/axes"), e -> e.itemId == saw.id);
        TagRegistry.INSTANCE.register(Identifier.of("items/tools/axes"), e -> e.itemId == diamondSaw.id);

        battery = new Battery(Identifier.of(MOD_ID, "battery"), 400).setTranslationKey(MOD_ID, "battery");

        ironDust = new Dust(Identifier.of(MOD_ID, "ironDust"), Color.WHITE).setTranslationKey(MOD_ID, "ironDust");
        goldDust = new Dust(Identifier.of(MOD_ID, "goldDust"), new Color(255, 255, 11)).setTranslationKey(MOD_ID, "goldDust");

        // Secondary Blocks (BlockItems)
        cable = new CableBlockItem(Identifier.of(MOD_ID, "cable"), BlockListener.cable, Color.WHITE).setTranslationKey(MOD_ID, "cable");
        powerCable = new CableBlockItem(Identifier.of(MOD_ID, "powerCable"), BlockListener.powerCable, new Color(37, 33, 33, 255)).setTranslationKey(MOD_ID, "powerCable");
        itemCable = new CableBlockItem(Identifier.of(MOD_ID, "itemCable"), BlockListener.itemCable, Color.GREEN).setTranslationKey(MOD_ID, "itemCable");
    }
}
