package com.github.kydzombie.stapitest.tileentity;

import com.github.kydzombie.stapitest.util.machine.power.PowerStorage;
import com.github.kydzombie.stapitest.util.machine.power.PowerUtils;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.tileentity.TileEntityBase;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.Vec3i;

import java.util.List;

public abstract class TilePowered extends TileEntityBase implements PowerStorage {

    List<TilePowered> connectedMachines;

    final int maxPower;
    int power;

    public TilePowered(int maxPower) {
        super();
        this.maxPower = maxPower;
        this.power = 0;
    }

    public TilePowered(int maxPower, int power) {
        super();
        this.maxPower = maxPower;
        this.power = power;
    }

    public void updateAllConnections() {
        PowerUtils.updateConnectedMachines(level, new Vec3i(x, y, z));
        updateConnections();
    }

    public void updateConnections() {
        connectedMachines = PowerUtils.findMachineConnections(level, new Vec3i(x, y, z));
    }

    @Override
    public void tick() {
        super.tick();
        if (connectedMachines == null) {
            connectedMachines = PowerUtils.findMachineConnections(level, new Vec3i(x, y, z));
        }
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
        if (this.level.getTileEntity(this.x, this.y, this.z) != this) {
            return false;
        } else {
            return !(player.squaredDistanceTo((double)this.x + 0.5D, (double)this.y + 0.5D, (double)this.z + 0.5D) > 64.0D);
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
