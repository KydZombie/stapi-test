package com.github.kydzombie.stapitest.block.cable;

import com.github.kydzombie.stapitest.custom.util.WorldUtils;
import net.minecraft.block.BlockBase;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.level.BlockView;
import net.modificationstation.stationapi.api.registry.Identifier;

public class ItemCable extends Cable {

    public ItemCable(Identifier identifier) {
        super(identifier);
    }

    @Override
    public boolean checkConnection(BlockView tileView, int x, int y, int z, int side) {
        BlockBase block = WorldUtils.getBlock(tileView, x, y, z);
        return WorldUtils.getTileEntity(tileView, x, y, z) instanceof InventoryBase || block != null && id == block.id;
    }
}
