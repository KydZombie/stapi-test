package com.github.kydzombie.stapitest.events.init;

import com.github.kydzombie.stapitest.block.cable.Cable;
import com.github.kydzombie.stapitest.block.cable.ItemCable;
import com.github.kydzombie.stapitest.block.cable.PowerCable;
import com.github.kydzombie.stapitest.block.machine.ElectricFurnace;
import com.github.kydzombie.stapitest.block.machine.Generator;
import com.github.kydzombie.stapitest.block.machine.Grinder;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;

public class BlockListener {

    public static BlockBase cable;
    public static BlockBase powerCable;
    public static BlockBase itemCable;
    public static BlockBase generator;
    public static BlockBase electricFurnace;
    public static BlockBase grinder;

    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {

        generator = new Generator(Identifier.of(MOD_ID, "generator")).setTranslationKey(MOD_ID, "generator");
        electricFurnace = new ElectricFurnace(Identifier.of(MOD_ID, "electricFurnace")).setTranslationKey(MOD_ID, "electricFurnace");
        grinder = new Grinder(Identifier.of(MOD_ID, "macerator")).setTranslationKey(MOD_ID, "grinder");

        cable = new Cable(Identifier.of(MOD_ID, "cable"));
        powerCable = new PowerCable(Identifier.of(MOD_ID, "powerCable"));
        itemCable = new ItemCable(Identifier.of(MOD_ID, "itemCable"));
    }
}
