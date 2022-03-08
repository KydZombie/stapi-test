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
    public static int machineTop;
    public static int machineSide;
    public static int machineBottom;

    @EventListener
    public void registerTextures(TextureRegisterEvent event) {
        machineTop = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "machine_top")).index;
        machineSide = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "machine_side")).index;
        machineBottom = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "machine_bottom")).index;

        StapiTest.electricFurnace.texture = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "electric_furnace")).index;

        StapiTest.generator.texture = StapiTest.electricFurnace.texture;

        StapiTest.grinder.texture = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "grinder_top")).index;
        Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "grinder_side"));

        StapiTest.press.texture = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "press")).index;

        StapiTest.battery.texture = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "battery_side")).index;

        StapiTest.cableItem.setTexture(Identifier.of(MOD_ID, "cable"));
        StapiTest.powerCableItem.setTexture(Identifier.of(MOD_ID, "cable"));
        StapiTest.itemCableItem.setTexture(Identifier.of(MOD_ID, "cable"));

        StapiTest.ironDust.setTexture(Identifier.of(MOD_ID, "ore_dust"));
        StapiTest.goldDust.setTexture(Identifier.of(MOD_ID, "ore_dust"));

        StapiTest.ironPlate.setTexture(Identifier.of(MOD_ID, "plate"));
        StapiTest.goldPlate.setTexture(Identifier.of(MOD_ID, "plate"));

        StapiTest.ironGear.setTexture(Identifier.of(MOD_ID, "gear"));
        StapiTest.goldGear.setTexture(Identifier.of(MOD_ID, "gear"));

        StapiTest.powerToolHandle.setTexture(Identifier.of(MOD_ID, "power_tool_handle"));
        StapiTest.drillHead.setTexture(Identifier.of(MOD_ID, "drill_head"));
        StapiTest.sawHead.setTexture(Identifier.of(MOD_ID, "saw_head"));
        StapiTest.diamondDrillHead.setTexture(Identifier.of(MOD_ID, "diamond_drill_head"));
        StapiTest.diamondSawHead.setTexture(Identifier.of(MOD_ID, "diamond_saw_head"));

        StapiTest.wrench.setTexture(Identifier.of(MOD_ID, "wrench"));
        StapiTest.batteryItem.setTexture(Identifier.of(MOD_ID, "battery"));

        StapiTest.electricPickaxe.setTexture(Identifier.of(MOD_ID, "electric_pickaxe"));

        StapiTest.drill.setTexture(Identifier.of(MOD_ID, "drill"));
        StapiTest.diamondDrill.setTexture(Identifier.of(MOD_ID, "diamond_drill"));
        StapiTest.saw.setTexture(Identifier.of(MOD_ID, "saw"));
        StapiTest.diamondSaw.setTexture(Identifier.of(MOD_ID, "diamond_saw"));
    }
}
