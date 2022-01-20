package com.github.kydzombie.stapitest.block.machine;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntityBase;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;

public class MachineBlock extends TemplateBlockWithEntity {
    public MachineBlock(Identifier identifier) {
        super(identifier, Material.WOOD);
    }

    @Override
    protected TileEntityBase createTileEntity() {
        return null;
    }
}
