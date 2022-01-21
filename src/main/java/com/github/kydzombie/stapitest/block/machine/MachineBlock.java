package com.github.kydzombie.stapitest.block.machine;

import com.github.kydzombie.stapitest.tileentity.TileEntityMachine;
import com.github.kydzombie.stapitest.util.machine.Wrenchable;
import net.minecraft.block.material.Material;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;

public abstract class MachineBlock extends TemplateBlockWithEntity implements Wrenchable {
    public MachineBlock(Identifier identifier) {
        super(identifier, Material.METAL);
    }

    @Override
    public void onBlockPlaced(Level level, int x, int y, int z) {
        super.onBlockPlaced(level, x, y, z);
        ((TileEntityMachine)level.getTileEntity(x, y, z)).updateAllConnections();
    }

    @Override
    public void onBlockRemoved(Level level, int x, int y, int z) {
        ((TileEntityMachine)level.getTileEntity(x, y, z)).updateAllConnections();
        super.onBlockRemoved(level, x, y, z);
    }
}
