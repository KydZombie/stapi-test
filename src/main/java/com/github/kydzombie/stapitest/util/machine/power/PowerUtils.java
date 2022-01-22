package com.github.kydzombie.stapitest.util.machine.power;

import com.github.kydzombie.stapitest.events.init.BlockListener;
import com.github.kydzombie.stapitest.tileentity.TilePowered;
import com.github.kydzombie.stapitest.util.WorldUtils;
import com.github.kydzombie.stapitest.util.math.Vec3Facing;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.tileentity.TileEntityBase;
import net.minecraft.util.maths.Vec3i;

import java.util.ArrayList;
import java.util.List;

public class PowerUtils {

    public static int attemptChargeItem(ItemInstance item, int availablePower, int drainPower) {
        if (!(item.getType() instanceof ItemPowerStorage)) {
            return 0;
        }
        ItemPowerStorage itemPowerStorage = (ItemPowerStorage) item.getType();
        if (itemPowerStorage.charge(item, Math.min(availablePower, drainPower), true) > 0) {
            return itemPowerStorage.charge(item, Math.min(availablePower, drainPower), false);
        }
        else {
            return 0;
        }
    }

    public static int attemptConsumeItemPower(ItemInstance item, int consumeAmount) {
        if (!(item.getType() instanceof ItemPowerStorage)) {
            return 0;
        }
        ItemPowerStorage itemPowerStorage = (ItemPowerStorage) item.getType();
        if (itemPowerStorage.consume(item, consumeAmount, true) > 0) {
            return itemPowerStorage.consume(item, consumeAmount, false);
        }
        else {
            return 0;
        }
    }

    public static void updateConnectedMachines(Level level, Vec3i pos) {
        System.out.println("Machine updates pushed from " + pos.x + ", " + pos.y + ", " + pos.z + ".");
        List<TilePowered> connectedMachines = PowerUtils.findMachineConnections(level, pos);
        for (TilePowered machine : connectedMachines) {
            machine.updateConnections();
        }
    }

    public static int sendPowerToConnections(List<TilePowered> machines, int availablePower, int drainPower) {
        int powerDrained = 0;

        for (TilePowered machine : machines) {
            if (availablePower - powerDrained > 0) {
                if (machine.charge(Math.min(availablePower, drainPower), 6, true) > 0) {
                    powerDrained += machine.charge(Math.min(availablePower, drainPower), 6, false);
                }
            }
        }

        return powerDrained;
    }

    public static List<TilePowered> findMachineConnections(Level level, Vec3i pos) {
        List<Vec3Facing> allBlockConnections = findPowerConnections(level, pos);
        allBlockConnections.removeIf(connection -> {TileEntityBase tileEntity = level.getTileEntity(connection.pos.x, connection.pos.y, connection.pos.z);
            if (tileEntity instanceof TilePowered) {
                return !((PowerConnection) tileEntity.getTile()).canConnect(level, connection.pos, connection.side);
            }
            return true;
        });

        List<TilePowered> allConnections = new ArrayList<>();

        for (Vec3Facing block : allBlockConnections) {
            allConnections.add((TilePowered) level.getTileEntity(block.pos.x, block.pos.y, block.pos.z));
        }

        return allConnections;
    }

    public static List<Vec3Facing> findPowerConnections(Level level, Vec3i pos) {
        List<Vec3Facing> blocksChecked = new ArrayList<>();
        List<Vec3Facing> blocksToCheck = new ArrayList<>(checkSurroundingPowerConnection(level, pos));
        blocksChecked.add(new Vec3Facing(pos.x, pos.y, pos.z, 0));

        List<Vec3Facing> check;
        while (blocksToCheck.size() > 0) {
            if (WorldUtils.getBlock(level, blocksToCheck.get(0).pos) != BlockListener.powerCable){
                blocksChecked.add(blocksToCheck.get(0));
                blocksToCheck.remove(0);
                continue;
            }

            check = checkSurroundingPowerConnection(level, blocksToCheck.get(0).pos);

            blocksChecked.add(blocksToCheck.get(0));
            blocksToCheck.removeAll(blocksChecked);
            check.removeAll(blocksChecked);

            blocksToCheck.addAll(check);
        }

        blocksChecked.remove(0);

        return blocksChecked;
    }

    public static List<Vec3Facing> checkSurroundingPowerConnection(Level level, Vec3i pos) {
        List<Vec3Facing> foundBlocks = new ArrayList<>();
        Vec3i check;

        check = new Vec3i(pos.x + 1, pos.y, pos.z);
        if (WorldUtils.getBlock(level, check) instanceof PowerConnection) {
            if (((Connection)WorldUtils.getBlock(level, check)).canConnect(level, check, 4)) {
                foundBlocks.add(new Vec3Facing(check, 4));
            }
        }
        check = new Vec3i(pos.x - 1, pos.y, pos.z);
        if (WorldUtils.getBlock(level, check) instanceof PowerConnection) {
            if (((Connection)WorldUtils.getBlock(level, check)).canConnect(level, check, 5)) {
                foundBlocks.add(new Vec3Facing(check, 5));
            }
        }
        check = new Vec3i(pos.x, pos.y + 1, pos.z);
        if (WorldUtils.getBlock(level, check) instanceof PowerConnection) {
            if (((Connection)WorldUtils.getBlock(level, check)).canConnect(level, check, 0)) {
                foundBlocks.add(new Vec3Facing(check, 0));
            }
        }
        check = new Vec3i(pos.x, pos.y - 1, pos.z);
        if (WorldUtils.getBlock(level, check) instanceof PowerConnection) {
            if (((Connection)WorldUtils.getBlock(level, check)).canConnect(level, check, 1)) {
                foundBlocks.add(new Vec3Facing(check, 1));
            }
        }
        check = new Vec3i(pos.x, pos.y, pos.z + 1);
        if (WorldUtils.getBlock(level, check) instanceof PowerConnection) {
            if (((Connection)WorldUtils.getBlock(level, check)).canConnect(level, check, 2)) {
                foundBlocks.add(new Vec3Facing(check, 2));
            }
        }
        check = new Vec3i(pos.x, pos.y, pos.z - 1);
        if (WorldUtils.getBlock(level, check) instanceof PowerConnection) {
            if (((Connection)WorldUtils.getBlock(level, check)).canConnect(level, check, 3)) {
                foundBlocks.add(new Vec3Facing(check, 3));
            }
        }

        return foundBlocks;
    }
}
