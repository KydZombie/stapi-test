package com.github.kydzombie.stapitest.block.cable;

import com.github.kydzombie.stapitest.events.init.ItemListener;
import com.github.kydzombie.stapitest.util.WorldUtils;
import com.github.kydzombie.stapitest.util.machine.Wrenchable;
import com.github.kydzombie.stapitest.util.machine.power.Connection;
import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.render.block.BlockRenderer;
import net.minecraft.level.BlockView;
import net.minecraft.level.Level;
import net.minecraft.util.maths.Box;
import net.minecraft.util.maths.Vec3i;
import net.modificationstation.stationapi.api.client.model.block.BlockWithWorldRenderer;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("SuspiciousNameCombination")
public class Cable extends TemplateBlockBase implements BlockWithWorldRenderer, Connection, Wrenchable {
    
    private static final float MIN_WIDTH = .3f;
    private static final float MAX_WIDTH = .7f;

    public Class<?> connectTo = null;
    
    public Cable(Identifier identifier) {
        super(identifier, Material.WOOL);
        this.texture = BlockBase.WOOL.texture;
        this.setSounds(WOOL_SOUNDS);
        this.setHardness(0.8f);
    }

    @Override
    public int getDropId(int meta, Random rand) {
        return ItemListener.cable.id;
    }

    @Override
    public boolean isFullOpaque() {
        return false;
    }

    @Override
    public Box getCollisionShape(Level level, int x, int y, int z) {
        return Box.create(x, y, z, x + 1, y + 1, z + 1);
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public boolean canConnect(BlockView tileView, Vec3i pos, int side) {
        return true;
    }

    @SuppressWarnings("SuspiciousNameCombination")
    @Override
    public boolean renderWorld(BlockRenderer tileRenderer, BlockView tileView, int x, int y, int z) {
        int var5 = this.getColourMultiplier(tileView, x, y, z);
        float var6 = (float)(var5 >> 16 & 255) / 255.0F;
        float var7 = (float)(var5 >> 8 & 255) / 255.0F;
        float var8 = (float)(var5 & 255) / 255.0F;

        this.setBoundingBox(MIN_WIDTH, MIN_WIDTH, MIN_WIDTH, MAX_WIDTH, MAX_WIDTH, MAX_WIDTH);
        tileRenderer.renderFast(this, x, y, z, var6, var7, var8);

        SafeBox currentBox = new SafeBox(MIN_WIDTH, MIN_WIDTH, MIN_WIDTH, MAX_WIDTH, MAX_WIDTH, MAX_WIDTH);

        List<CableConnection> connections = getConnections(tileView, x, y, z);

        for (CableConnection connection: connections) {
            SafeBox box = connection.boundingBox;
            this.setBoundingBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
            tileRenderer.renderFast(this, x, y, z, var6, var7, var8);
            currentBox.combine(box);
        }

        this.setBoundingBox(currentBox.minX, currentBox.minY, currentBox.minZ, currentBox.maxX, currentBox.maxY, currentBox.maxZ);

        return true;
    }

    @SuppressWarnings("SuspiciousNameCombination")
    @Override
    public void updateBoundingBox(BlockView tileView, int x, int y, int z) {

        SafeBox currentBox = new SafeBox(MIN_WIDTH, MIN_WIDTH, MIN_WIDTH, MAX_WIDTH, MAX_WIDTH, MAX_WIDTH);

        List<CableConnection> connections = getConnections(tileView, x, y, z);

        for (CableConnection connection: connections) {
            SafeBox box = connection.boundingBox;
            currentBox.combine(box);
        }

        this.setBoundingBox(currentBox.minX, currentBox.minY, currentBox.minZ, currentBox.maxX, currentBox.maxY, currentBox.maxZ);
    }

    private List<CableConnection> getConnections(BlockView tileView, int x, int y, int z) {
        List<CableConnection> connections = new ArrayList<>();

        Vec3i check;
        BlockBase block;

        // x
        check = new Vec3i(x + 1, y, z);
        block = WorldUtils.getBlock(tileView, check);
        if (block != null && ((connectTo != null && connectTo.isInstance(block)) || id == block.id)) {
            connections.add(new CableConnection(check, new SafeBox(MAX_WIDTH, MIN_WIDTH, MIN_WIDTH, 1f, MAX_WIDTH, MAX_WIDTH)));
        }
        check = new Vec3i(x - 1, y, z);
        block = WorldUtils.getBlock(tileView, check);
        if (block != null && ((connectTo != null && connectTo.isInstance(block)) || id == block.id)) {
            connections.add(new CableConnection(check, new SafeBox(0f, MIN_WIDTH, MIN_WIDTH, MIN_WIDTH, MAX_WIDTH, MAX_WIDTH)));
        }
        // y
        check = new Vec3i(x, y + 1, z);
        block = WorldUtils.getBlock(tileView, check);
        if (block != null && ((connectTo != null && connectTo.isInstance(block)) || id == block.id)) {
            connections.add(new CableConnection(check, new SafeBox(MIN_WIDTH, MAX_WIDTH, MIN_WIDTH, MAX_WIDTH, 1f, MAX_WIDTH)));
        }
        check = new Vec3i(x, y - 1, z);
        block = WorldUtils.getBlock(tileView, check);
        if (block != null && ((connectTo != null && connectTo.isInstance(block)) || id == block.id)) {
            connections.add(new CableConnection(check, new SafeBox(MIN_WIDTH, 0f, MIN_WIDTH, MAX_WIDTH, MIN_WIDTH, MAX_WIDTH)));
        }
        // z
        check = new Vec3i(x, y, z + 1);
        block = WorldUtils.getBlock(tileView, check);
        if (block != null && ((connectTo != null && connectTo.isInstance(block)) || id == block.id)) {
            connections.add(new CableConnection(check, new SafeBox(MIN_WIDTH, MIN_WIDTH, MAX_WIDTH, MAX_WIDTH, MAX_WIDTH, 1f)));
        }
        check = new Vec3i(x, y, z - 1);
        block = WorldUtils.getBlock(tileView, check);
        if (block != null && ((connectTo != null && connectTo.isInstance(block)) || id == block.id)) {
            connections.add(new CableConnection(check, new SafeBox(MIN_WIDTH, MIN_WIDTH, 0f, MAX_WIDTH, MAX_WIDTH, MIN_WIDTH)));
        }
        return connections;
    }
}

class CableConnection {
    Vec3i pos;
    SafeBox boundingBox;

    CableConnection(Vec3i pos, SafeBox boundingBox) {
        this.pos = pos;
        this.boundingBox = boundingBox;
    }
}

class SafeBox {
    float minX;
    float minY;
    float minZ;
    float maxX;
    float maxY;
    float maxZ;

    SafeBox(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
    }

    void combine(SafeBox box) {
        if (box.minX < this.minX) {
            this.minX = box.minX;
        }
        if (box.minY < this.minY) {
            this.minY = box.minY;
        }
        if (box.minZ < this.minZ) {
            this.minZ = box.minZ;
        }
        if (box.maxX > this.maxX) {
            this.maxX = box.maxX;
        }
        if (box.maxY > this.maxY) {
            this.maxY = box.maxY;
        }
        if (box.maxZ > this.maxZ) {
            this.maxZ = box.maxZ;
        }
    }

}