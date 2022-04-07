package com.github.kydzombie.stapitest.block.cable;

import com.github.kydzombie.stapitest.custom.util.WorldUtils;
import com.github.kydzombie.stapitest.custom.util.machine.power.PowerConnection;
import com.github.kydzombie.stapitest.custom.util.machine.power.PowerUtils;
import net.minecraft.block.BlockBase;
import net.minecraft.level.BlockView;
import net.minecraft.level.Level;
import net.minecraft.util.maths.Vec3i;
import net.modificationstation.stationapi.api.registry.Identifier;

public class PowerCable extends Cable implements PowerConnection {
    public PowerCable(Identifier identifier) {
        super(identifier);
        this.connectTo = PowerConnection.class;
    }

    @Override
    public void onBlockPlaced(Level level, int x, int y, int z) {
        super.onBlockPlaced(level, x, y, z);
        PowerUtils.updateConnectedMachines(level, new Vec3i(x, y, z));
    }

    @Override
    public void onBlockRemoved(Level level, int x, int y, int z) {
        super.onBlockRemoved(level, x, y, z);
        PowerUtils.updateConnectedMachines(level, new Vec3i(x, y, z));
    }

    @Override
    boolean checkConnection(BlockView tileView, int x, int y, int z, int side) {
        BlockBase block = WorldUtils.getBlock(tileView, x, y, z);

        return ((block instanceof PowerConnection && ((PowerConnection) block).canConnect(tileView, x, y, z, side)) || block != null && id == block.id);
    }
}
