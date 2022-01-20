package com.github.kydzombie.stapitest.util.math;

import net.minecraft.util.maths.Vec3i;

public class Vec3Facing {

    public Vec3i pos;
    public int side;

    public Vec3Facing(Vec3i pos, int side) {
        this.pos = pos;
        this.side = side;
    }

    public Vec3Facing(int x, int y, int z, int side) {
        this.pos = new Vec3i(x, y, z);
        this.side = side;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vec3Facing) {
            return this.pos.equals(((Vec3Facing) obj).pos);
        }
        return false;
    }
}
