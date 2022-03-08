package com.github.kydzombie.stapitest.block.machine;

import com.github.kydzombie.stapitest.container.ProcessingContainer;
import com.github.kydzombie.stapitest.events.init.StapiTest;
import com.github.kydzombie.stapitest.tileentity.TileElectricFurnace;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.level.Level;
import net.minecraft.tileentity.TileEntityBase;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.registry.Identifier;

public class ElectricFurnace extends MachineBlock {
    public ElectricFurnace(Identifier identifier) {
        super(identifier);
    }

    @Override
    protected TileEntityBase createTileEntity() {
        return new TileElectricFurnace();
    }

    @Override
    public boolean canUse(Level level, int x, int y, int z, PlayerBase player) {
        super.canUse(level, x, y, z, player);
        TileEntityBase tileFurnace = level.getTileEntity(x, y, z);
        GuiHelper.openGUI(player, Identifier.of(StapiTest.MOD_ID, "openFurnace"), (InventoryBase) tileFurnace, new ProcessingContainer(player.inventory, (TileElectricFurnace) tileFurnace));
        return true;
    }
}
