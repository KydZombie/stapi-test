package com.github.kydzombie.stapitest.block.cable;

import com.github.kydzombie.stapitest.events.init.ItemListener;
import com.github.kydzombie.stapitest.util.machine.power.PowerConnection;
import com.github.kydzombie.stapitest.util.machine.power.PowerUtils;
import net.minecraft.level.Level;
import net.minecraft.util.maths.Vec3i;
import net.modificationstation.stationapi.api.registry.Identifier;

import java.awt.*;
import java.util.Random;

public class PowerCable extends Cable implements PowerConnection {
    public PowerCable(Identifier identifier) {
        super(identifier, new Color(37, 33, 33, 255));
        this.connectTo = PowerConnection.class;
    }

    @Override
    public void onBlockPlaced(Level level, int x, int y, int z) {
        super.onBlockPlaced(level, x, y, z);
        PowerUtils.updateConnectedMachines(level, new Vec3i(x, y, z));
    }

    @Override
    public void onBlockRemoved(Level level, int x, int y, int z) {
        super.onBlockPlaced(level, x, y, z);
        PowerUtils.updateConnectedMachines(level, new Vec3i(x, y, z));
    }

    @Override
    public int getDropId(int meta, Random rand) {
        return ItemListener.powerCable.id;
    }
}
