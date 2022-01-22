package com.github.kydzombie.stapitest.block.machine;

import com.github.kydzombie.stapitest.container.ContainerMacerator;
import com.github.kydzombie.stapitest.events.init.BlockListener;
import com.github.kydzombie.stapitest.tileentity.TileMacerator;
import com.github.kydzombie.stapitest.util.machine.power.PowerConnection;
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

public class Macerator extends MachineBlock {
    public Macerator(Identifier identifier) {
        super(identifier);
    }

    @Override
    protected TileEntityBase createTileEntity() {
        return new TileMacerator();
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

    @Environment(EnvType.CLIENT)
    public int getTextureForSide(int side, int meta) {
        if (side == 3) {
            return this.texture;
        }
        else {
            return BlockBase.IRON_BLOCK.getTextureForSide(0);
        }
    }

    @Environment(EnvType.CLIENT)
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

    @Override
    public boolean canUse(Level level, int x, int y, int z, PlayerBase player) {
        super.canUse(level, x, y, z, player);
        TileEntityBase tileMacerator = level.getTileEntity(x, y, z);
        GuiHelper.openGUI(player, Identifier.of(BlockListener.MOD_ID, "openMacerator"), (InventoryBase) tileMacerator, new ContainerMacerator(player.inventory, (TileMacerator) tileMacerator));
        return true;
    }
}
