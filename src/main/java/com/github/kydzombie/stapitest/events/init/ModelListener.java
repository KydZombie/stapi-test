package com.github.kydzombie.stapitest.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.model.Model;
import net.modificationstation.stationapi.api.client.model.json.JsonModel;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;

public class ModelListener {
    public static Model grinder;

    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    @EventListener
    public void registerModels(TextureRegisterEvent event) {
        grinder = JsonModel.get(Identifier.of(MOD_ID, "grinder"));
    }
}
