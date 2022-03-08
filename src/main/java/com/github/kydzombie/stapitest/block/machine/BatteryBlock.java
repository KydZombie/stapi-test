package com.github.kydzombie.stapitest.block.machine;

import com.github.kydzombie.stapitest.container.ContainerBattery;
import com.github.kydzombie.stapitest.container.PowerStorageContainer;
import com.github.kydzombie.stapitest.events.init.StapiTest;
import com.github.kydzombie.stapitest.tileentity.TileBattery;
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
import net.minecraft.util.maths.Vec3i;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.registry.Identifier;

import java.awt.*;

public class BatteryBlock extends MachineBlock {

    public BatteryBlock(Identifier identifier) {
        super(identifier);
    }

    @Override
    protected TileEntityBase createTileEntity() {
        return new TileBattery();
    }

    @Override
    public boolean canUse(Level level, int x, int y, int z, PlayerBase player) {
        super.canUse(level, x, y, z, player);
        TileEntityBase tileEntityBattery = level.getTileEntity(x, y, z);
        GuiHelper.openGUI(player, Identifier.of(StapiTest.MOD_ID, "openBattery"), (InventoryBase) tileEntityBattery, new PowerStorageContainer(player.inventory, (TileBattery) tileEntityBattery));
        return true;
    }

    @Override
    public int getBaseColour(int i) {
        return ColorConverter.colorToInt(Color.WHITE);
    }

    @Override
    public boolean canConnect(BlockView tileView, Vec3i pos, int side) {
        return true;
    }

    @Override
    public void afterPlaced(Level level, int x, int y, int z, Living living) {
    }

    @Environment(EnvType.CLIENT)
    public int getTextureForSide(int side, int meta) {
        if (side == 0 || side == 1) {
            return BlockBase.WOOD.texture;
        }
        else {
            return this.texture;
        }
    }
}
