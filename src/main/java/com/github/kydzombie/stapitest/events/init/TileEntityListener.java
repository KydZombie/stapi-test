package com.github.kydzombie.stapitest.events.init;

import com.github.kydzombie.stapitest.tileentity.TileElectricFurnace;
import com.github.kydzombie.stapitest.tileentity.TileGenerator;
import com.github.kydzombie.stapitest.tileentity.TileMacerator;
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
        event.register(TileMacerator.class, Identifier.of(MOD_ID, "macerator_entity").toString());
    }

}
