package com.github.kydzombie.stapitest.block.cable;

import com.github.kydzombie.stapitest.events.init.ItemListener;
import com.github.kydzombie.stapitest.util.WorldUtils;
import com.github.kydzombie.stapitest.util.machine.Wrenchable;
import com.github.kydzombie.stapitest.util.machine.power.Connection;
import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.render.block.BlockRenderer;
import net.minecraft.level.BlockView;
import net.minecraft.util.maths.Vec3i;
import net.modificationstation.stationapi.api.client.model.block.BlockWithWorldRenderer;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;

import java.util.Random;

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
    public boolean isFullCube() {
        return false;
    }

    @Override
    public boolean canConnect(BlockView tileView, Vec3i pos, int side) {
        return true;
    }

    @Override
    public int getRenderType() {
        return -1;
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

        Vec3i check;

        if (connectTo == null) {
            int tileId = tileView.getTileId(x, y, z);

            // x
            check = new Vec3i(x + 1, y, z);
            if (tileView.getTileId(check.x, check.y, check.z) == tileId) {
                this.setBoundingBox(MAX_WIDTH, MIN_WIDTH, MIN_WIDTH, 1, MAX_WIDTH, MAX_WIDTH);
                tileRenderer.renderFast(this, x, y, z, var6, var7, var8);
            }
            check = new Vec3i(x - 1, y, z);
            if (tileView.getTileId(check.x, check.y, check.z) == tileId) {
                this.setBoundingBox(0f, MIN_WIDTH, MIN_WIDTH, MIN_WIDTH, MAX_WIDTH, MAX_WIDTH);
                tileRenderer.renderFast(this, x, y, z, var6, var7, var8);
            }
            // y
            check = new Vec3i(x, y + 1, z);
            if (tileView.getTileId(check.x, check.y, check.z) == tileId) {
                this.setBoundingBox(MIN_WIDTH, MAX_WIDTH, MIN_WIDTH, MAX_WIDTH, 1f, MAX_WIDTH);
                tileRenderer.renderFast(this, x, y, z, var6, var7, var8);
            }
            check = new Vec3i(x, y - 1, z);
            if (tileView.getTileId(check.x, check.y, check.z) == tileId) {
                this.setBoundingBox(MIN_WIDTH, 0f, MIN_WIDTH, MAX_WIDTH, MIN_WIDTH, MAX_WIDTH);
                tileRenderer.renderFast(this, x, y, z, var6, var7, var8);
            }
            // z
            check = new Vec3i(x, y, z + 1);
            if (tileView.getTileId(check.x, check.y, check.z) == tileId) {
                this.setBoundingBox(MIN_WIDTH, MIN_WIDTH, MAX_WIDTH, MAX_WIDTH, MAX_WIDTH, 1f);
                tileRenderer.renderFast(this, x, y, z, var6, var7, var8);
            }
            check = new Vec3i(x, y, z - 1);
            if (tileView.getTileId(check.x, check.y, check.z) == tileId) {
                this.setBoundingBox(MIN_WIDTH, MIN_WIDTH, 0f, MAX_WIDTH, MAX_WIDTH, MIN_WIDTH);
                tileRenderer.renderFast(this, x, y, z, var6, var7, var8);
            }
        }
        else {
            // y
            check = new Vec3i(x, y + 1, z);
            if (connectTo.isInstance(WorldUtils.getBlock(tileView, check))) {
                if (((Connection) WorldUtils.getBlock(tileView, check)).canConnect(tileView, check, 0)) {
                    this.setBoundingBox(MIN_WIDTH, MAX_WIDTH, MIN_WIDTH, MAX_WIDTH, 1f, MAX_WIDTH);
                    tileRenderer.renderFast(this, x, y, z, var6, var7, var8);
                }
            }
            check = new Vec3i(x, y - 1, z);
            if (connectTo.isInstance(WorldUtils.getBlock(tileView, check))) {
                if (((Connection) WorldUtils.getBlock(tileView, check)).canConnect(tileView, check, 1)) {
                    this.setBoundingBox(MIN_WIDTH, 0f, MIN_WIDTH, MAX_WIDTH, MIN_WIDTH, MAX_WIDTH);
                    tileRenderer.renderFast(this, x, y, z, var6, var7, var8);
                }
            }
            // x
            check = new Vec3i(x + 1, y, z);
            if (connectTo.isInstance(WorldUtils.getBlock(tileView, check))) {
                if (((Connection) WorldUtils.getBlock(tileView, check)).canConnect(tileView, check, 4)) {
                    this.setBoundingBox(MAX_WIDTH, MIN_WIDTH, MIN_WIDTH, 1, MAX_WIDTH, MAX_WIDTH);
                    tileRenderer.renderFast(this, x, y, z, var6, var7, var8);
                }
            }
            check = new Vec3i(x - 1, y, z);
            if (connectTo.isInstance(WorldUtils.getBlock(tileView, check))) {
                if (((Connection) WorldUtils.getBlock(tileView, check)).canConnect(tileView, check, 5)) {
                    this.setBoundingBox(0f, MIN_WIDTH, MIN_WIDTH, MIN_WIDTH, MAX_WIDTH, MAX_WIDTH);
                    tileRenderer.renderFast(this, x, y, z, var6, var7, var8);
                }
            }
            // z
            check = new Vec3i(x, y, z + 1);
            if (connectTo.isInstance(WorldUtils.getBlock(tileView, check))) {
                if (((Connection) WorldUtils.getBlock(tileView, check)).canConnect(tileView, check, 2)) {
                    this.setBoundingBox(MIN_WIDTH, MIN_WIDTH, MAX_WIDTH, MAX_WIDTH, MAX_WIDTH, 1f);
                    tileRenderer.renderFast(this, x, y, z, var6, var7, var8);
                }
            }
            check = new Vec3i(x, y, z - 1);
            if (connectTo.isInstance(WorldUtils.getBlock(tileView, check))) {
                if (((Connection) WorldUtils.getBlock(tileView, check)).canConnect(tileView, check, 3)) {
                    this.setBoundingBox(MIN_WIDTH, MIN_WIDTH, 0f, MAX_WIDTH, MAX_WIDTH, MIN_WIDTH);
                    tileRenderer.renderFast(this, x, y, z, var6, var7, var8);
                }
            }
        }

        this.setBoundingBox(0, 0, 0, 1, 1, 1);

        return true;
    }
}