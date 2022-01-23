package com.github.kydzombie.stapitest.block.machine;

import com.github.kydzombie.stapitest.container.ContainerGenerator;
import com.github.kydzombie.stapitest.events.init.BlockListener;
import com.github.kydzombie.stapitest.tileentity.TileGenerator;
import com.github.kydzombie.stapitest.util.ColorConverter;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.level.BlockView;
import net.minecraft.level.Level;
import net.minecraft.tileentity.TileEntityBase;
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
