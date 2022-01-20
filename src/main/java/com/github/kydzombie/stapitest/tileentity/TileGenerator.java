package com.github.kydzombie.stapitest.tileentity;

import com.github.kydzombie.stapitest.util.math.Vec3Facing;
import com.github.kydzombie.stapitest.util.power.Connection;
import com.github.kydzombie.stapitest.util.power.PowerConnection;
import com.github.kydzombie.stapitest.util.power.PowerStorage;
import net.minecraft.block.BlockBase;
import net.minecraft.level.Level;
import net.minecraft.tileentity.TileEntityBase;
import net.minecraft.util.maths.Vec3i;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;

import java.util.ArrayList;

public class TileGenerator extends TileEntityBase implements PowerStorage {

    private final int TICK_TIMER = 10;
    private int tick_timer = TICK_TIMER;

    private static int MAX_POWER = 80;
    private int power = MAX_POWER;

    @Override
    public void tick() {
        if (tick_timer > 0) {
            tick_timer--;
            return;
        }
        else {
            tick_timer = TICK_TIMER;
        }
        int range = 16;
        ArrayList<Vec3Facing> blocksChecked = new ArrayList<>();
        ArrayList<Vec3Facing> blocksToCheck = new ArrayList<>(checkNearbyBlocks(new Vec3i(x, y, z)));
        blocksChecked.add(new Vec3Facing(x, y, z, 0));

        ArrayList<Vec3Facing> check;
        while (blocksToCheck.size() > 0 && blocksChecked.size() < range) {

            check = checkNearbyBlocks(blocksToCheck.get(0).pos);

            blocksChecked.add(blocksToCheck.get(0));
            blocksToCheck.removeAll(blocksChecked);
            check.removeAll(blocksChecked);

            blocksToCheck.addAll(check);
        }

        int size = blocksChecked.size();

        if (size > range) {
            blocksChecked.subList(range, size).clear();
        }

        blocksChecked.remove(0);

        blocksChecked.forEach((found) -> {
            TileEntityBase tileEntity = level.getTileEntity(found.pos.x, found.pos.y, found.pos.z);

            if (tileEntity instanceof PowerStorage) {
                if (((PowerConnection)tileEntity.getTile()).canConnect(level, found.pos, found.side)) {
                    if (power != 0) {
                        if (((PowerStorage) tileEntity).charge(Math.min(4, power), 6, true) > 0) {
                            power -= ((PowerStorage) tileEntity).charge(4, 6, false);
                        }
                    }
                }

            }
        });
    }

    private ArrayList<Vec3Facing> checkNearbyBlocks(Vec3i pos) {
        ArrayList<Vec3Facing> foundBlocks = new ArrayList<>();
        Vec3i check;

        check = new Vec3i(pos.x + 1, pos.y, pos.z);
        if (getBlock(level, check) instanceof PowerConnection) {
            if (((Connection)getBlock(level, check)).canConnect(level, check, 4)) {
                foundBlocks.add(new Vec3Facing(check, 4));
            }
        }
        check = new Vec3i(pos.x - 1, pos.y, pos.z);
        if (getBlock(level, check) instanceof PowerConnection) {
            if (((Connection)getBlock(level, check)).canConnect(level, check, 5)) {
                foundBlocks.add(new Vec3Facing(check, 5));
            }
        }
        check = new Vec3i(pos.x, pos.y + 1, pos.z);
        if (getBlock(level, check) instanceof PowerConnection) {
            if (((Connection)getBlock(level, check)).canConnect(level, check, 0)) {
                foundBlocks.add(new Vec3Facing(check, 0));
            }
        }
        check = new Vec3i(pos.x, pos.y - 1, pos.z);
        if (getBlock(level, check) instanceof PowerConnection) {
            if (((Connection)getBlock(level, check)).canConnect(level, check, 1)) {
                foundBlocks.add(new Vec3Facing(check, 1));
            }
        }
        check = new Vec3i(pos.x, pos.y, pos.z + 1);
        if (getBlock(level, check) instanceof PowerConnection) {
            if (((Connection)getBlock(level, check)).canConnect(level, check, 2)) {
                foundBlocks.add(new Vec3Facing(check, 2));
            }
        }
        check = new Vec3i(pos.x, pos.y, pos.z - 1);
        if (getBlock(level, check) instanceof PowerConnection) {
            if (((Connection)getBlock(level, check)).canConnect(level, check, 3)) {
                foundBlocks.add(new Vec3Facing(check, 3));
            }
        }

        return foundBlocks;
    }

    private BlockBase getBlock(Level level, Vec3i pos) {
        return TemplateBlockBase.BY_ID[level.getTileId(pos.x, pos.y, pos.z)];
    }

    private Vec3Facing checkBlock() {
        return null;
    }

    @Override
    public int getCurrentPower() {
        return power;
    }

    @Override
    public int getMaxPower() {
        return MAX_POWER;
    }

    @Override
    public int charge(int chargeAmount, int side, boolean simulate) {
        return 0;
    }

    @Override
    public int consume(int consumeAmount, int side, boolean simulate) {
        return 0;
    }
}
