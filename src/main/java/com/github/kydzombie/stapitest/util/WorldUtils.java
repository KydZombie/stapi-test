package com.github.kydzombie.stapitest.util;

import net.minecraft.block.BlockBase;
import net.minecraft.level.BlockView;
import net.minecraft.tileentity.TileEntityBase;
import net.minecraft.util.maths.Vec3i;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;


public class WorldUtils {

    public static BlockBase getBlock(BlockView tileView, Vec3i pos) {
        return TemplateBlockBase.BY_ID[tileView.getTileId(pos.x, pos.y, pos.z)];
    }

    public static TileEntityBase getTileEntity(BlockView tileView, Vec3i pos) {
        return tileView.getTileEntity(pos.x, pos.y, pos.z);
    }
}
