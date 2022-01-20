package com.github.kydzombie.stapitest.util.power;

import net.minecraft.level.BlockView;
import net.minecraft.util.maths.Vec3i;

public interface Connection {
    boolean canConnect(BlockView tileView, Vec3i pos, int side);
}
