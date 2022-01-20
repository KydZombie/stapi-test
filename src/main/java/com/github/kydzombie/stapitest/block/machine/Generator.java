package com.github.kydzombie.stapitest.block.machine;

import com.github.kydzombie.stapitest.util.power.PowerConnection;
import com.github.kydzombie.stapitest.tileentity.TileGenerator;
import net.minecraft.level.BlockView;
import net.minecraft.tileentity.TileEntityBase;
import net.minecraft.util.maths.Vec3i;
import net.modificationstation.stationapi.api.registry.Identifier;

public class Generator extends MachineBlock implements PowerConnection {

    public Generator(Identifier identifier) {
        super(identifier);
    }

    @Override
    protected TileEntityBase createTileEntity() {
        return new TileGenerator();
    }

    @Override
    public boolean canConnect(BlockView tileView, Vec3i pos, int side) {
        return true;
    }
}
