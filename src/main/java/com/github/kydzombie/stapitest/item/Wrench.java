package com.github.kydzombie.stapitest.item;

import com.github.kydzombie.stapitest.block.cable.Cable;
import com.github.kydzombie.stapitest.block.machine.MachineBlock;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

public class Wrench extends TemplateItemBase {
    public Wrench(Identifier identifier) {
        super(identifier);
    }

    @Override
    public boolean useOnTile(ItemInstance item, PlayerBase player, Level level, int x, int y, int z, int facing) {
        BlockBase block = TemplateBlockBase.BY_ID[level.getTileId(x, y, z)];
        int meta = level.getTileMeta(x, y, z);
        if (block instanceof MachineBlock) {
            level.setTile(x, y, z, 0);
            block.drop(level, x, y, z, meta);
            return true;
        }
        else if (block instanceof Cable) {
            level.setTile(x, y, z, 0);
            block.drop(level, x, y, z, meta);
            return true;
        }
        return false;
    }
}
