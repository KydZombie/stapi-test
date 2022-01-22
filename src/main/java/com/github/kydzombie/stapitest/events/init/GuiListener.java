package com.github.kydzombie.stapitest.events.init;

import com.github.kydzombie.stapitest.gui.GuiElectricFurnace;
import com.github.kydzombie.stapitest.gui.GuiGenerator;
import com.github.kydzombie.stapitest.tileentity.TileElectricFurnace;
import com.github.kydzombie.stapitest.tileentity.TileGenerator;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.gui.screen.ScreenBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.modificationstation.stationapi.api.event.registry.GuiHandlerRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.GuiHandlerRegistry;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;
import uk.co.benjiweber.expressions.tuple.BiTuple;

public class GuiListener {

    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    @Environment(EnvType.CLIENT)
    @EventListener
    public void registerGuiHandler(GuiHandlerRegistryEvent event) {
        GuiHandlerRegistry registry = event.registry;
        registry.registerValueNoMessage(Identifier.of(MOD_ID, "openFurnace"), BiTuple.of(this::openFurnace, TileElectricFurnace::new));
        registry.registerValueNoMessage(Identifier.of(MOD_ID, "openGenerator"), BiTuple.of(this::openGenerator, TileGenerator::new));
    }

    @Environment(EnvType.CLIENT)
    public ScreenBase openFurnace(PlayerBase player, InventoryBase inventoryBase) {
        return new GuiElectricFurnace(player.inventory, (TileElectricFurnace) inventoryBase);
    }

    @Environment(EnvType.CLIENT)
    public ScreenBase openGenerator(PlayerBase player, InventoryBase inventoryBase) {
        return new GuiGenerator(player.inventory, (TileGenerator) inventoryBase);
    }

}
