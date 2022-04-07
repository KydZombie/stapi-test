package com.github.kydzombie.stapitest.custom.util;

import net.minecraft.block.BlockBase;
import net.minecraft.level.BlockView;
import net.minecraft.tileentity.TileEntityBase;
import net.minecraft.util.maths.Vec3i;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;


public class WorldUtils {

    public static BlockBase getBlock(BlockView tileView, Vec3i pos) {
        return getBlock(tileView, pos.x, pos.y, pos.z);
    }

    public static BlockBase getBlock(BlockView tileView, int x, int y, int z) {
        return TemplateBlockBase.BY_ID[tileView.getTileId(x, y, z)];
    }

    public static TileEntityBase getTileEntity(BlockView tileView, Vec3i pos) {
        return getTileEntity(tileView, pos.x, pos.y, pos.z);
    }

    public static TileEntityBase getTileEntity(BlockView tileView, int x, int y, int z) {
        return tileView.getTileEntity(x, y, z);
    }
}
