package com.github.kydzombie.stapitest.block;

import com.github.kydzombie.stapitest.events.init.ItemListener;
import net.minecraft.block.material.Material;
import net.minecraft.client.render.block.BlockRenderer;
import net.minecraft.item.Block;
import net.minecraft.level.BlockView;
import net.modificationstation.stationapi.api.block.CustomBlockItemFactoryProvider;
import net.modificationstation.stationapi.api.client.model.block.BlockWithWorldRenderer;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;

import java.util.function.IntFunction;

public class Cable extends TemplateBlockBase implements BlockWithWorldRenderer {
    
    private static final float MIN_WIDTH = .25f;
    private static final float MAX_WIDTH = .75f;
    
    public Cable(Identifier identifier) {
        super(identifier, Material.WOOL);
        this.texture = 113;
        this.setSounds(WOOL_SOUNDS);
        this.setHardness(0.8f);
    }

    @Override
    public boolean isFullOpaque() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
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

        int tileId = tileView.getTileId(x, y, z);

        // y
        if (tileView.getTileId(x, y + 1, z) == tileId) {
            this.setBoundingBox(MIN_WIDTH, MAX_WIDTH, MIN_WIDTH, MAX_WIDTH, 1f, MAX_WIDTH);
            tileRenderer.renderFast(this, x, y, z, var6, var7, var8);
        }
        if (tileView.getTileId(x, y - 1, z) == tileId) {
            this.setBoundingBox(MIN_WIDTH, 0f, MIN_WIDTH, MAX_WIDTH, MIN_WIDTH, MAX_WIDTH);
            tileRenderer.renderFast(this, x, y, z, var6, var7, var8);
        }
        // x
        if (tileView.getTileId(x + 1, y, z) == tileId) {
            this.setBoundingBox(MAX_WIDTH, MIN_WIDTH, MIN_WIDTH, 1, MAX_WIDTH, MAX_WIDTH);
            tileRenderer.renderFast(this, x, y, z, var6, var7, var8);
        }
        if (tileView.getTileId(x - 1, y, z) == tileId) {
            this.setBoundingBox(0f, MIN_WIDTH, MIN_WIDTH, MIN_WIDTH, MAX_WIDTH, MAX_WIDTH);
            tileRenderer.renderFast(this, x, y, z, var6, var7, var8);
        }
        // z
        if (tileView.getTileId(x, y, z + 1) == tileId) {
            this.setBoundingBox(MIN_WIDTH, MIN_WIDTH, MAX_WIDTH, MAX_WIDTH, MAX_WIDTH, 1f);
            tileRenderer.renderFast(this, x, y, z, var6, var7, var8);
        }
        if (tileView.getTileId(x, y, z - 1) == tileId) {
            this.setBoundingBox(MIN_WIDTH, MIN_WIDTH, 0f, MAX_WIDTH, MAX_WIDTH, MIN_WIDTH);
            tileRenderer.renderFast(this, x, y, z, var6, var7, var8);
        }

        this.setBoundingBox(0, 0, 0, 1, 1, 1);

        return true;
    }
}
