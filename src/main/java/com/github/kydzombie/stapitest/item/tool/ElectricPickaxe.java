package com.github.kydzombie.stapitest.item.tool;

import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.item.tool.ToolLevel;
import net.modificationstation.stationapi.api.item.tool.ToolMaterialFactory;
import net.modificationstation.stationapi.api.registry.Identifier;

public class ElectricPickaxe extends PoweredTool implements ToolLevel {

    private static ToolMaterial empowered;

    public ElectricPickaxe(Identifier identifier, ToolMaterial toolMaterial, int maxPower) {
        super(identifier, toolMaterial, maxPower);
        empowered = ToolMaterialFactory.create("empoweredPickaxe", 2, 250, 10.0F, 2);
    }

    @Override
    public ToolMaterial getMaterial(ItemInstance item) {
        if (getCurrentPower(item) >= 5) {
            return empowered;
        } else {
            return toolMaterial;
        }
    }
}
