package com.github.kydzombie.stapitest.custom.util.machine.power;

import net.minecraft.level.BlockView;
import net.minecraft.util.maths.Vec3i;

public interface Connection {
    default boolean canConnect(BlockView tileView, Vec3i pos, int side) {
        return canConnect(tileView, pos.x, pos.y, pos.z, side);
    }

    boolean canConnect(BlockView tileView, int x, int y, int z, int side);
}
