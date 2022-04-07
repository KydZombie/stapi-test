package com.github.kydzombie.stapitest.tileentity;

import com.github.kydzombie.stapitest.custom.util.machine.power.PowerStorage;
import com.github.kydzombie.stapitest.custom.util.machine.power.PowerUtils;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.tileentity.TileEntityBase;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.Vec3i;

import java.util.List;

public abstract class TilePowered extends TileEntityBase implements PowerStorage {

    private static final int DEFAULT_MAX = 800;

    int maxPower;
    List<TilePowered> connectedMachines;
    boolean dirty;
    int power;

    public TilePowered() {
        super();
        this.maxPower = DEFAULT_MAX;
        power = 0;
        dirty = true;
    }

    public TilePowered setMaxPower(int maxPower) {
        this.maxPower = maxPower;
        return this;
    }

    public TilePowered setStartingPower(int startingPower) {
        this.power = startingPower;
        return this;
    }

    public void updateAllConnections() {
        PowerUtils.updateConnectedMachines(level, new Vec3i(x, y, z));
        markDirty();
    }

    @Override
    public void tick() {
        super.tick();
        if (connectedMachines == null || dirty) {
            connectedMachines = PowerUtils.findMachineConnections(level, new Vec3i(x, y, z));
            dirty = false;
        }
    }

    public void markDirty() {
        dirty = true;
    }

    @Override
    public int getCurrentPower() {
        return power;
    }

    @Override
    public int getMaxPower() {
        return maxPower;
    }

    @Override
    public int charge(int chargeAmount, int side, boolean simulate) {
        chargeAmount = Math.min(chargeAmount, maxPower - power);
        if (!simulate) {
            power += chargeAmount;
        }

        return chargeAmount;

    }

    @Override
    public int consume(int consumeAmount, int side, boolean simulate) {
        consumeAmount = Math.max(power - consumeAmount, 0);
        if (!simulate) {
            power -= consumeAmount;
        }

        return consumeAmount;
    }

    public boolean canPlayerUse(PlayerBase player) {
        if (level.getTileEntity(x, y, z) != this) {
            return false;
        } else {
            return !(player.squaredDistanceTo((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D) > 64.0D);
        }
    }

    @Override
    public void readIdentifyingData(CompoundTag tag) {
        super.readIdentifyingData(tag);
        power = tag.getInt("power");
    }

    @Override
    public void writeIdentifyingData(CompoundTag tag) {
        super.writeIdentifyingData(tag);
        tag.put("power", power);
    }

}
