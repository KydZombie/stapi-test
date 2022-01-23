package com.github.kydzombie.stapitest.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;

public class TextureListener {

    public static int machineTop;
    public static int machineSide;
    public static int machineBottom;

    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    @EventListener
    public void registerTextures(TextureRegisterEvent event) {
        machineTop = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "machine_top")).index;
        machineSide = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "machine_side")).index;
        machineBottom = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "machine_bottom")).index;

        BlockListener.electricFurnace.texture = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "electric_furnace")).index;

        BlockListener.grinder.texture = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "grinder_top")).index;
        Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "grinder_side"));

        BlockListener.generator.texture = BlockListener.electricFurnace.texture;

        ItemListener.cable.setTexture(Identifier.of(MOD_ID, "cable"));
        ItemListener.powerCable.setTexture(Identifier.of(MOD_ID, "cable"));
        ItemListener.itemCable.setTexture(Identifier.of(MOD_ID, "cable"));

        ItemListener.ironDust.setTexture(Identifier.of(MOD_ID, "ore_dust"));
        ItemListener.goldDust.setTexture(Identifier.of(MOD_ID, "ore_dust"));

        ItemListener.wrench.setTexture(Identifier.of(MOD_ID, "wrench"));
        ItemListener.battery.setTexture(Identifier.of(MOD_ID, "battery"));

        ItemListener.electricPickaxe.setTexture(Identifier.of(MOD_ID, "electric_pickaxe"));

        ItemListener.drill.setTexture(Identifier.of(MOD_ID, "drill"));
        ItemListener.diamondDrill.setTexture(Identifier.of(MOD_ID, "diamond_drill"));
        ItemListener.saw.setTexture(Identifier.of(MOD_ID, "saw"));
        ItemListener.diamondSaw.setTexture(Identifier.of(MOD_ID, "diamond_saw"));
    }
}
