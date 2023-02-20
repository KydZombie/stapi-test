package com.github.kydzombie.stapitest.block.machine;

import com.github.kydzombie.stapitest.container.PowerStorageContainer;
import com.github.kydzombie.stapitest.events.init.StapiTest;
import com.github.kydzombie.stapitest.tileentity.TileGenerator;
import com.github.kydzombie.stapitest.custom.util.ColorConverter;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.level.Level;
import net.minecraft.tileentity.TileEntityBase;
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
        GuiHelper.openGUI(player, StapiTest.MOD_ID.id("openGenerator"), (InventoryBase) tileEntityGenerator, new PowerStorageContainer(player.inventory, (TileGenerator) tileEntityGenerator));
        return true;
    }

    @Override
    public int getMachineColor() {
        return ColorConverter.colorToInt(new Color(0xFF7B60));
    }
}
