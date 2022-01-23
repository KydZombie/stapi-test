package com.github.kydzombie.stapitest.block.machine;

import com.github.kydzombie.stapitest.container.ContainerGenerator;
import com.github.kydzombie.stapitest.events.init.BlockListener;
import com.github.kydzombie.stapitest.events.init.TextureListener;
import com.github.kydzombie.stapitest.tileentity.TileGenerator;
import com.github.kydzombie.stapitest.util.ColorConverter;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.level.BlockView;
import net.minecraft.level.Level;
import net.minecraft.tileentity.TileEntityBase;
import net.minecraft.util.maths.MathHelper;
import net.minecraft.util.maths.Vec3i;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.registry.Identifier;

import java.awt.*;

public class Generator extends MachineBlock {

    public Generator(Identifier identifier) {
        super(identifier);
    }

    @Override
    protected TileEntityBase createTileEntity() {
        return new TileGenerator();
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

    @Environment(EnvType.CLIENT)
    public int getTextureForSide(BlockView tileView, int x, int y, int z, int meta) {
        return getTextureForSide(meta, tileView.getTileMeta(x, y, z));
    }

    @Environment(EnvType.CLIENT)
    public int getTextureForSide(int side, int meta) {
        if (meta == 0 && side == 3) {
            return texture;
        }
        if (meta == side) {
            return texture;
        }
        else if (side == 1) {
            return TextureListener.machineTop;
        }
        else if (side == 0) {
            return TextureListener.machineBottom;
        }
        else {
            return TextureListener.machineSide;
        }
    }

    @Environment(EnvType.CLIENT)
    public int getTextureForSide(int side) {
        return getTextureForSide(side, 0);
    }

    @Override
    public boolean canUse(Level level, int x, int y, int z, PlayerBase player) {
        super.canUse(level, x, y, z, player);
        TileEntityBase tileEntityGenerator = level.getTileEntity(x, y, z);
        GuiHelper.openGUI(player, Identifier.of(BlockListener.MOD_ID, "openGenerator"), (InventoryBase) tileEntityGenerator, new ContainerGenerator(player.inventory, (TileGenerator) tileEntityGenerator));
        return true;
    }

    @Override
    public boolean canConnect(BlockView tileView, Vec3i pos, int side) {
        return side != tileView.getTileMeta(pos.x, pos.y, pos.z);
    }

    @Override
    public int getColourMultiplier(BlockView tileView, int x, int y, int z) {
        return ColorConverter.colorToInt(new Color(0xFF7B60));
    }
}
