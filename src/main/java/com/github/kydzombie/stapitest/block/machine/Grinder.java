package com.github.kydzombie.stapitest.block.machine;

import com.github.kydzombie.stapitest.container.ContainerGrinder;
import com.github.kydzombie.stapitest.events.init.BlockListener;
import com.github.kydzombie.stapitest.events.init.TextureListener;
import com.github.kydzombie.stapitest.tileentity.TileMacerator;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.level.BlockView;
import net.minecraft.level.Level;
import net.minecraft.tileentity.TileEntityBase;
import net.minecraft.util.maths.Vec3i;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.registry.Identifier;

public class Grinder extends MachineBlock {
    public Grinder(Identifier identifier) {
        super(identifier);
    }

    @Override
    protected TileEntityBase createTileEntity() {
        return new TileMacerator();
    }

    @Environment(EnvType.CLIENT)
    public int getTextureForSide(BlockView tileView, int x, int y, int z, int meta) {
        return getTextureForSide(meta);
    }

    @Environment(EnvType.CLIENT)
    public int getTextureForSide(int side, int meta) {
        return getTextureForSide(side);
    }

    @Environment(EnvType.CLIENT)
    public int getTextureForSide(int side) {
        if (side == 1) {
            return texture;
        }
        else if (side == 0) {
            return TextureListener.machineBottom;
        }
        else {
            return texture + 1;
        }
    }

    @Override
    public boolean canConnect(BlockView tileView, Vec3i pos, int side) {
        return side != 1;
    }

    @Override
    public boolean canUse(Level level, int x, int y, int z, PlayerBase player) {
        super.canUse(level, x, y, z, player);
        TileEntityBase tileMacerator = level.getTileEntity(x, y, z);
        GuiHelper.openGUI(player, Identifier.of(BlockListener.MOD_ID, "openGrinder"), (InventoryBase) tileMacerator, new ContainerGrinder(player.inventory, (TileMacerator) tileMacerator));
        return true;
    }
}
