package com.github.kydzombie.stapitest.events.init;

import com.github.kydzombie.stapitest.item.Wrench;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.SecondaryBlock;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;
import net.modificationstation.stationapi.api.template.item.TemplateSecondaryBlock;
import net.modificationstation.stationapi.api.util.Null;

public class ItemListener {

    public static TemplateItemBase wrench;
    public static TemplateSecondaryBlock cable;
    public static TemplateSecondaryBlock powerCable;

    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        wrench = new Wrench(Identifier.of(MOD_ID, "wrench")).setTranslationKey(MOD_ID, "wrench");
        cable = new TemplateSecondaryBlock(Identifier.of(MOD_ID, "cable"), BlockListener.cable).setTranslationKey(MOD_ID, "cable");
        powerCable = new TemplateSecondaryBlock(Identifier.of(MOD_ID, "powerCable"), BlockListener.powerCable).setTranslationKey(MOD_ID, "powerCable");
    }
}
