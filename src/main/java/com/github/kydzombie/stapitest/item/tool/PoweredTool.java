package com.github.kydzombie.stapitest.item.tool;

import com.github.kydzombie.stapitest.util.machine.power.ItemPowerStorage;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.Living;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.item.tool.ToolLevel;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.tool.TemplateToolBase;

public abstract class PoweredTool extends TemplateToolBase implements ItemPowerStorage, ToolLevel {

    final int maxPower;

    public PoweredTool(Identifier identifier, ToolMaterial arg, int maxPower) {
        super(identifier, 0, arg, new BlockBase[]{});
        this.maxPower = maxPower;
        setTranslationKey(identifier.toString());
    }

    @Override
    public int getMaxPower(ItemInstance item) {
        return maxPower;
    }

    @Override
    public boolean postMine(ItemInstance item, int i, int j, int k, int i1, Living damageTarget) {
        if (getCurrentPower(item) >= 5) {
            consume(item, 5, false);
        } else {
            item.applyDamage(1, damageTarget);
        }
        return true;
    }
}
