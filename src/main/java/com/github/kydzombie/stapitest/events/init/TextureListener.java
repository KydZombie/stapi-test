package com.github.kydzombie.stapitest.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;

public class TextureListener {
    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    @EventListener
    public void registerTextures(TextureRegisterEvent event) {
        BlockListener.electricFurnace.texture = Atlases.getStationTerrain().addTexture(Identifier.of(MOD_ID, "electric_furnace")).index;
        BlockListener.generator.texture = BlockListener.electricFurnace.texture;

        ItemListener.cable.setTexture(Identifier.of(MOD_ID, "cable"));
        ItemListener.powerCable.setTexture(Identifier.of(MOD_ID, "cable_power"));

        ItemListener.ironDust.setTexture(Identifier.of(MOD_ID, "iron_dust"));
        ItemListener.goldDust.setTexture(Identifier.of(MOD_ID, "gold_dust"));

        ItemListener.wrench.setTexture(Identifier.of(MOD_ID, "wrench"));
    }
}
