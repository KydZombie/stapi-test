package com.github.kydzombie.stapitest.events.init;

import com.github.kydzombie.stapitest.gui.GuiBattery;
import com.github.kydzombie.stapitest.gui.GuiGenerator;
import com.github.kydzombie.stapitest.gui.ProcessingGui;
import com.github.kydzombie.stapitest.tileentity.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.gui.screen.ScreenBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.modificationstation.stationapi.api.event.registry.GuiHandlerRegistryEvent;
import net.modificationstation.stationapi.api.registry.GuiHandlerRegistry;
import net.modificationstation.stationapi.api.registry.Identifier;
import uk.co.benjiweber.expressions.tuple.BiTuple;

import static com.github.kydzombie.stapitest.events.init.StapiTest.MOD_ID;

public class GuiListener {

    @Environment(EnvType.CLIENT)
    @EventListener
    public void registerGuiHandler(GuiHandlerRegistryEvent event) {
        GuiHandlerRegistry registry = event.registry;
        registry.registerValueNoMessage(MOD_ID.id("openFurnace"), BiTuple.of(this::openFurnace, TileElectricFurnace::new));
        registry.registerValueNoMessage(MOD_ID.id("openGenerator"), BiTuple.of(this::openGenerator, TileGenerator::new));
        registry.registerValueNoMessage(MOD_ID.id("openGrinder"), BiTuple.of(this::openGrinder, TileGrinder::new));
        registry.registerValueNoMessage(MOD_ID.id("openPress"), BiTuple.of(this::openPress, TilePress::new));
        registry.registerValueNoMessage(MOD_ID.id("openCentrifuge"), BiTuple.of(this::openCentrifuge, TileCentrifuge::new));
        registry.registerValueNoMessage(MOD_ID.id("openBattery"), BiTuple.of(this::openBattery, TileBattery::new));
    }

    @Environment(EnvType.CLIENT)
    public ScreenBase openFurnace(PlayerBase player, InventoryBase inventoryBase) {
        return new ProcessingGui(player.inventory, (TileElectricFurnace) inventoryBase, "Electric Furnace");
    }

    @Environment(EnvType.CLIENT)
    public ScreenBase openGenerator(PlayerBase player, InventoryBase inventoryBase) {
        return new GuiGenerator(player.inventory, (TileGenerator) inventoryBase);
    }

    @Environment(EnvType.CLIENT)
    public ScreenBase openGrinder(PlayerBase player, InventoryBase inventoryBase) {
        return new ProcessingGui(player.inventory, (TileGrinder) inventoryBase, "Grinder");
    }

    @Environment(EnvType.CLIENT)
    public ScreenBase openPress(PlayerBase player, InventoryBase inventoryBase) {
        return new ProcessingGui(player.inventory, (TilePress) inventoryBase, "Press");
    }

    @Environment(EnvType.CLIENT)
    public ScreenBase openCentrifuge(PlayerBase player, InventoryBase inventoryBase) {
        return new ProcessingGui(player.inventory, (TileCentrifuge) inventoryBase, "Centrifuge");
    }

    @Environment(EnvType.CLIENT)
    public ScreenBase openBattery(PlayerBase player, InventoryBase inventoryBase) {
        return new GuiBattery(player.inventory, (TileBattery) inventoryBase);
    }

}
