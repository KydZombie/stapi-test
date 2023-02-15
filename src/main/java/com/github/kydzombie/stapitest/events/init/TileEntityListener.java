package com.github.kydzombie.stapitest.events.init;

import com.github.kydzombie.stapitest.tileentity.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.tileentity.TileEntityRegisterEvent;
import net.modificationstation.stationapi.api.registry.Identifier;

import static com.github.kydzombie.stapitest.events.init.StapiTest.MOD_ID;

public class TileEntityListener {

    @EventListener
    public void registerTileEntities(TileEntityRegisterEvent event) {
        event.register(TileElectricFurnace.class, MOD_ID.id("electric_furnace_entity").toString());
        event.register(TileGenerator.class, MOD_ID.id("generator_entity").toString());
        event.register(TileGrinder.class, MOD_ID.id("grinder_entity").toString());
        event.register(TilePress.class, MOD_ID.id("press_entity").toString());
        event.register(TileCentrifuge.class, MOD_ID.id("centrifuge_entity").toString());
        event.register(TileBattery.class, MOD_ID.id("battery_entity").toString());
    }

}
