package com.github.kydzombie.stapitest.block.machine;

import com.github.kydzombie.stapitest.util.power.PowerConnection;
import com.github.kydzombie.stapitest.tileentity.TileElectricFurnace;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.Living;
import net.minecraft.level.BlockView;
import net.minecraft.level.Level;
import net.minecraft.tileentity.TileEntityBase;
import net.minecraft.util.maths.MathHelper;
import net.minecraft.util.maths.Vec3i;
import net.modificationstation.stationapi.api.registry.Identifier;

public class ElectricFurnace extends MachineBlock implements PowerConnection {
    public ElectricFurnace(Identifier identifier) {
        super(identifier);
    }

    @Override
    public void onBlockPlaced(Level level, int x, int y, int z) {
        super.onBlockPlaced(level, x, y, z);
    }

    @Override
    protected TileEntityBase createTileEntity() {
        return new TileElectricFurnace();
    }

    @Environment(EnvType.CLIENT)
    public int getTextureForSide(BlockView tileView, int x, int y, int z, int meta) {
        if (meta == 1) {
            return BlockBase.IRON_BLOCK.getTextureForSide(0);
        } else if (meta == 0) {
            return BlockBase.IRON_BLOCK.getTextureForSide(0);
        } else {
            int var6 = tileView.getTileMeta(x, y, z);
            if (meta != var6) {
                return BlockBase.IRON_BLOCK.getTextureForSide(0);
            } else {
                return this.texture;
            }
        }
    }

    @Override
    public void afterPlaced(Level level, int x, int y, int z, Living living) {
        int var6 = MathHelper.floor((double)(living.yaw * 4.0F / 360.0F) + 0.5D) & 3;
        if (var6 == 0) {
            level.setTileMeta(x, y, z, 2);
        }

        if (var6 == 1) {
            level.setTileMeta(x, y, z, 5);
        }

        if (var6 == 2) {
            level.setTileMeta(x, y, z, 3);
        }

        if (var6 == 3) {
            level.setTileMeta(x, y, z, 4);
        }

    }

    @Override
    public int getTextureForSide(int side, int meta) {
        if (side == 3) {
            return this.texture;
        }
        else {
            return BlockBase.IRON_BLOCK.getTextureForSide(0);
        }
    }

    @Override
    public int getTextureForSide(int side) {
        if (side == 3) {
            return this.texture;
        }
        else {
            return BlockBase.IRON_BLOCK.getTextureForSide(0);
        }
    }

    @Override
    public boolean canConnect(BlockView tileView, Vec3i pos, int side) {
        return side != tileView.getTileMeta(pos.x, pos.y, pos.z);
    }
}
