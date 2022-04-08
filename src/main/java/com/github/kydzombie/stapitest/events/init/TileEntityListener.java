package com.github.kydzombie.stapitest.events.init;

import com.github.kydzombie.stapitest.tileentity.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.tileentity.TileEntityRegisterEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;

public class TileEntityListener {

    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    @EventListener
    public void registerTileEntities(TileEntityRegisterEvent event) {
        event.register(TileElectricFurnace.class, Identifier.of(MOD_ID, "electric_furnace_entity").toString());
        event.register(TileGenerator.class, Identifier.of(MOD_ID, "generator_entity").toString());
        event.register(TileGrinder.class, Identifier.of(MOD_ID, "grinder_entity").toString());
        event.register(TilePress.class, Identifier.of(MOD_ID, "press_entity").toString());
        event.register(TileCentrifuge.class, Identifier.of(MOD_ID, "centrifuge_entity").toString());
        event.register(TileBattery.class, Identifier.of(MOD_ID, "battery_entity").toString());
    }

}
