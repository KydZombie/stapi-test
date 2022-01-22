package com.github.kydzombie.stapitest.block.cable;

import com.github.kydzombie.stapitest.events.init.ItemListener;
import com.github.kydzombie.stapitest.util.WorldUtils;
import net.minecraft.block.BlockBase;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.level.BlockView;
import net.minecraft.util.maths.Vec3i;
import net.modificationstation.stationapi.api.registry.Identifier;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemCable extends Cable {

    public ItemCable(Identifier identifier) {
        super(identifier, Color.GREEN);
    }

    @Override
    public boolean canConnect(BlockView tileView, Vec3i pos, int side) {
        BlockBase block = WorldUtils.getBlock(tileView, pos);
        return tileView.getTileEntity(pos.x, pos.y, pos.z) instanceof InventoryBase || block != null && id == block.id;
    }

    @Override
    public int getDropId(int meta, Random rand) {
        return ItemListener.itemCable.id;
    }
}
