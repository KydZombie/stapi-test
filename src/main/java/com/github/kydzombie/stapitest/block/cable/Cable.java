package com.github.kydzombie.stapitest.block.cable;

import com.github.kydzombie.stapitest.custom.util.WorldUtils;
import com.github.kydzombie.stapitest.custom.util.machine.Wrenchable;
import com.github.kydzombie.stapitest.custom.util.machine.power.Connection;
import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.level.BlockView;
import net.minecraft.level.Level;
import net.minecraft.util.maths.Box;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.level.BlockStateView;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.BooleanProperty;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;

import java.util.Random;

public class Cable extends TemplateBlockBase implements Connection, Wrenchable {
    public static final BooleanProperty NORTH = BooleanProperty.of("north");
    public static final BooleanProperty SOUTH = BooleanProperty.of("south");
    public static final BooleanProperty EAST = BooleanProperty.of("east");
    public static final BooleanProperty WEST = BooleanProperty.of("west");
    public static final BooleanProperty UP = BooleanProperty.of("up");
    public static final BooleanProperty DOWN = BooleanProperty.of("down");

    static final float MIN_SIZE = .25f;
    static final float MAX_SIZE = .75f;
    public Class<?> connectTo;

    public Cable(Identifier identifier) {
        super(identifier, Material.STONE);
        this.setHardness(0.8f);
        mineableBy(Identifier.of("tools/pickaxes"), 0);
        setTranslationKey(identifier.toString());
        setDefaultState(getStateManager().getDefaultState()
                .with(NORTH, false)
                .with(SOUTH, false)
                .with(EAST, false)
                .with(WEST, false)
                .with(UP, false)
                .with(DOWN, false));
    }

    @Override
    public void appendProperties(StateManager.Builder<BlockBase, BlockState> builder) {
        builder.add(NORTH, SOUTH, EAST, WEST, UP, DOWN);
    }

    @Override
    public void onBlockPlaced(Level level, int x, int y, int z) {
        super.onBlockPlaced(level, x, y, z);
        updateCable(level, x, y, z);
    }

    @Override
    public void onAdjacentBlockUpdate(Level level, int x, int y, int z, int id) {
        super.onAdjacentBlockUpdate(level, x, y, z, id);
        updateCable(level, x, y, z);
//        level.method_216(x, y, z, id, 1);
    }

    @Override
    public void onScheduledTick(Level level, int x, int y, int z, Random rand) {
        updateCable(level, x, y, z);
    }

    public void updateCable(Level level, int x, int y, int z) {
        ((BlockStateView)level).setBlockState(x, y, z, getDefaultState()
                .with(NORTH, checkConnection(level, x - 1, y, z, 5))
                .with(SOUTH, checkConnection(level, x + 1, y, z, 4))
                .with(EAST, checkConnection(level, x, y, z - 1, 3))
                .with(WEST, checkConnection(level, x, y, z + 1, 2))
                .with(UP, checkConnection(level, x, y + 1, z, 0))
                .with(DOWN, checkConnection(level, x, y - 1, z, 1)));

        updateBoundingBox(level, x, y, z);
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
    public boolean canConnect(BlockView tileView, int x, int y, int z, int side) {
        return true;
    }

    @Override
    public void updateBoundingBox(BlockView tileView, int x, int y, int z) {
        BlockState blockState = ((BlockStateView)tileView).getBlockState(x, y, z);

        float maxX = blockState.get(SOUTH) ? 1 : MAX_SIZE;
        float minX = blockState.get(NORTH) ? 0 : MIN_SIZE;
        float maxY = blockState.get(UP) ? 1 : MAX_SIZE;
        float minY = blockState.get(DOWN) ? 0 : MIN_SIZE;
        float maxZ = blockState.get(WEST) ? 1 : MAX_SIZE;
        float minZ = blockState.get(EAST) ? 0 : MIN_SIZE;

        this.setBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
    }


    boolean checkConnection(BlockView tileView, int x, int y, int z, int side) {
        BlockBase block = WorldUtils.getBlock(tileView, x, y, z);

        return block != null && ((connectTo != null && connectTo.isInstance(block)) || id == block.id);
    }
}