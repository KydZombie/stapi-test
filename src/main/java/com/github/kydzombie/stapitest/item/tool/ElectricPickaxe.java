package com.github.kydzombie.stapitest.item.tool;

import net.minecraft.block.BlockBase;
import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.registry.Identifier;

public class ElectricPickaxe extends PoweredTool {
    public ElectricPickaxe(Identifier identifier, ToolMaterial toolMaterial, int maxPower) {
        super(identifier, 0, toolMaterial, new BlockBase[]{}, maxPower);
    }
}
