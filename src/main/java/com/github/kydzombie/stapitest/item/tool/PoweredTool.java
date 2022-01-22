package com.github.kydzombie.stapitest.item.tool;

import com.github.kydzombie.stapitest.util.machine.power.ItemPowerStorage;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.Living;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.tool.TemplateToolBase;

public class PoweredTool extends TemplateToolBase implements ItemPowerStorage {

    int maxPower;

    public PoweredTool(Identifier identifier, int j, ToolMaterial arg, BlockBase[] effectiveBlocks, int maxPower) {
        super(identifier, j, arg, effectiveBlocks);
        this.maxPower = maxPower;
    }

    @Override
    public int getMaxPower(ItemInstance item) {
        return maxPower;
    }

    @Override
    public boolean postMine(ItemInstance item, int i, int j, int k, int i1, Living damageTarget) {
        if (getCurrentPower(item) >= 5) {
            consume(item, 5, false);
        }
        else {
            item.applyDamage(1, damageTarget);
        }
        return true;
    }
}
