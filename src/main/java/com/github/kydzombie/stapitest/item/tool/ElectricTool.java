package com.github.kydzombie.stapitest.item.tool;

import com.github.kydzombie.stapitest.item.PoweredItem;
import net.minecraft.entity.Living;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.item.tool.ToolLevel;
import net.modificationstation.stationapi.api.item.tool.ToolMaterialFactory;
import net.modificationstation.stationapi.api.registry.Identifier;

public class ElectricTool extends PoweredItem implements ToolLevel {
    private ToolMaterial toolMaterial;
    private ToolMaterial dead;

    public ElectricTool(Identifier identifier, ToolMaterial mat) {
        super(identifier, mat.getDurability());
        toolMaterial = mat;
        dead = ToolMaterialFactory.create("dead_" + mat.name(), 0, mat.getDurability(), 0f, 0);
    }

    @Override
    public ToolMaterial getMaterial(ItemInstance item) {
        if (getCurrentPower(item) >= 5) {
            return toolMaterial;
        }
        else {
            return dead;
        }
    }

    @Override
    public boolean postMine(ItemInstance item, int i, int j, int k, int i1, Living damageTarget) {
        if (getCurrentPower(item) >= 5) {
            consume(item, 5, false);
        }
        return true;
    }
}
