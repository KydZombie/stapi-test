package com.github.kydzombie.stapitest.block.cable;

import com.github.kydzombie.stapitest.events.init.StapiTest;
import com.github.kydzombie.stapitest.custom.util.WorldUtils;
import net.minecraft.block.BlockBase;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.level.BlockView;
import net.minecraft.util.maths.Vec3i;
import net.modificationstation.stationapi.api.registry.Identifier;

import java.awt.*;
import java.util.Random;

public class ItemCable extends Cable {

    public ItemCable(Identifier identifier) {
        super(identifier, Color.GREEN);
    }

    @Override
    public boolean checkConnection(BlockView tileView, Vec3i pos, int side) {
        BlockBase block = WorldUtils.getBlock(tileView, pos);
        return WorldUtils.getTileEntity(tileView, pos) instanceof InventoryBase || block != null && id == block.id;
    }
}
