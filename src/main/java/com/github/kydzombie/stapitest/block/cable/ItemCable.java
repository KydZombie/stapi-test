package com.github.kydzombie.stapitest.block.cable;

import com.github.kydzombie.stapitest.events.init.ItemListener;
import com.github.kydzombie.stapitest.util.WorldUtils;
import net.minecraft.block.BlockBase;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.level.BlockView;
import net.minecraft.util.maths.Vec3i;
import net.modificationstation.stationapi.api.registry.Identifier;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemCable extends Cable {

    public ItemCable(Identifier identifier) {
        super(identifier, Color.GREEN);
    }

    @SuppressWarnings("SuspiciousNameCombination")
    @Override
    List<CableConnection> getConnections(BlockView tileView, int x, int y, int z) {
        List<CableConnection> connections = new ArrayList<>();

        Vec3i check;
        BlockBase block;

        // x
        check = new Vec3i(x + 1, y, z);
        block = WorldUtils.getBlock(tileView, check);
        if (block != null && (tileView.getTileEntity(check.x, check.y, check.z) instanceof InventoryBase || id == block.id)) {
            connections.add(new CableConnection(check, new SafeBox(MAX_WIDTH, MIN_WIDTH, MIN_WIDTH, 1f, MAX_WIDTH, MAX_WIDTH)));
        }
        check = new Vec3i(x - 1, y, z);
        block = WorldUtils.getBlock(tileView, check);
        if (block != null && (tileView.getTileEntity(check.x, check.y, check.z) instanceof InventoryBase || id == block.id)) {
            connections.add(new CableConnection(check, new SafeBox(0f, MIN_WIDTH, MIN_WIDTH, MIN_WIDTH, MAX_WIDTH, MAX_WIDTH)));
        }
        // y
        check = new Vec3i(x, y + 1, z);
        block = WorldUtils.getBlock(tileView, check);
        if (block != null && (tileView.getTileEntity(check.x, check.y, check.z) instanceof InventoryBase || id == block.id)) {
            connections.add(new CableConnection(check, new SafeBox(MIN_WIDTH, MAX_WIDTH, MIN_WIDTH, MAX_WIDTH, 1f, MAX_WIDTH)));
        }
        check = new Vec3i(x, y - 1, z);
        block = WorldUtils.getBlock(tileView, check);
        if (block != null && (tileView.getTileEntity(check.x, check.y, check.z) instanceof InventoryBase || id == block.id)) {
            connections.add(new CableConnection(check, new SafeBox(MIN_WIDTH, 0f, MIN_WIDTH, MAX_WIDTH, MIN_WIDTH, MAX_WIDTH)));
        }
        // z
        check = new Vec3i(x, y, z + 1);
        block = WorldUtils.getBlock(tileView, check);
        if (block != null && (tileView.getTileEntity(check.x, check.y, check.z) instanceof InventoryBase || id == block.id)) {
            connections.add(new CableConnection(check, new SafeBox(MIN_WIDTH, MIN_WIDTH, MAX_WIDTH, MAX_WIDTH, MAX_WIDTH, 1f)));
        }
        check = new Vec3i(x, y, z - 1);
        block = WorldUtils.getBlock(tileView, check);
        if (block != null && (tileView.getTileEntity(check.x, check.y, check.z) instanceof InventoryBase || id == block.id)) {
            connections.add(new CableConnection(check, new SafeBox(MIN_WIDTH, MIN_WIDTH, 0f, MAX_WIDTH, MAX_WIDTH, MIN_WIDTH)));
        }
        return connections;
    }

    @Override
    public int getDropId(int meta, Random rand) {
        return ItemListener.itemCable.id;
    }
}
