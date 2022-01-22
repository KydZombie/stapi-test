package com.github.kydzombie.stapitest.events.init;

import com.github.kydzombie.stapitest.item.CableBlockItem;
import com.github.kydzombie.stapitest.item.Dust;
import com.github.kydzombie.stapitest.item.Wrench;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;
import net.modificationstation.stationapi.api.template.item.TemplateSecondaryBlock;
import net.modificationstation.stationapi.api.util.Null;

import java.awt.*;

public class ItemListener {

    public static TemplateItemBase ironDust;
    public static TemplateItemBase goldDust;

    public static TemplateItemBase wrench;


    public static TemplateSecondaryBlock cable;
    public static TemplateSecondaryBlock powerCable;
    public static TemplateSecondaryBlock itemCable;

    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        ironDust = new Dust(Identifier.of(MOD_ID, "ironDust"), Color.WHITE).setTranslationKey(MOD_ID, "ironDust");
        goldDust = new Dust(Identifier.of(MOD_ID, "goldDust"), new Color(255, 255, 11)).setTranslationKey(MOD_ID, "goldDust");
        wrench = new Wrench(Identifier.of(MOD_ID, "wrench")).setTranslationKey(MOD_ID, "wrench");
        cable = new CableBlockItem(Identifier.of(MOD_ID, "cable"), BlockListener.cable, Color.WHITE).setTranslationKey(MOD_ID, "cable");
        powerCable = new CableBlockItem(Identifier.of(MOD_ID, "powerCable"), BlockListener.powerCable, new Color(37, 33, 33, 255)).setTranslationKey(MOD_ID, "powerCable");
        itemCable = new CableBlockItem(Identifier.of(MOD_ID, "itemCable"), BlockListener.itemCable, Color.GREEN).setTranslationKey(MOD_ID, "itemCable");
    }
}
