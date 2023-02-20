package com.github.kydzombie.stapitest.block.machine;

import com.github.kydzombie.stapitest.custom.util.ColorConverter;
import com.github.kydzombie.stapitest.custom.util.machine.Wrenchable;
import com.github.kydzombie.stapitest.custom.util.machine.power.PowerConnection;
import com.github.kydzombie.stapitest.events.init.StapiTest;
import com.github.kydzombie.stapitest.tileentity.TileMachine;
import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Item;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.BlockView;
import net.minecraft.level.Level;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.EnumProperty;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.modificationstation.stationapi.api.util.math.Direction;
import net.modificationstation.stationapi.api.world.BlockStateView;

import java.awt.*;
import java.util.Random;

public abstract class MachineBlock extends TemplateBlockWithEntity implements Wrenchable, PowerConnection {
    public static final EnumProperty<Direction> FACING_PROPERTY = EnumProperty.of("facing", Direction.class);
    private final Random rand = new Random();

    public MachineBlock(Identifier identifier) {
        super(identifier, Material.METAL);
        this.hardness = 3.5f;
        setTranslationKey(identifier.toString());
        setDefaultState(getStateManager().getDefaultState().with(FACING_PROPERTY, Direction.NORTH));
//        mineableBy("tools/pickaxes"), 0);
    }

    @Override
    public void onBlockPlaced(Level level, int x, int y, int z) {
        super.onBlockPlaced(level, x, y, z);
        ((TileMachine) level.getTileEntity(x, y, z)).updateAllConnections();
    }

    @Override
    public void appendProperties(StateManager.Builder<BlockBase, BlockState> builder) {
        builder.add(FACING_PROPERTY);
    }

    @Override
    public void afterPlaced(Level level, int x, int y, int z, Living living) {
        int direction = MathHelper.floor((double) (living.yaw * 4.0F / 360.0F) + 0.5D) & 3;
        level.setBlockState(x, y, z, getDefaultState().with(FACING_PROPERTY,
                switch(direction) {
                    case 0 -> Direction.EAST;
                    case 1 -> Direction.SOUTH;
                    case 2 -> Direction.WEST;
                    default -> Direction.NORTH;
        }));
    }

    @Override
    public void onBlockRemoved(Level level, int x, int y, int z) {
        TileMachine machineEntity = ((TileMachine) level.getTileEntity(x, y, z));
        machineEntity.updateAllConnections();

        for (int var6 = 0; var6 < machineEntity.getInventorySize(); ++var6) {
            ItemInstance var7 = machineEntity.getInventoryItem(var6);
            if (var7 != null) {
                float var8 = this.rand.nextFloat() * 0.8F + 0.1F;
                float var9 = this.rand.nextFloat() * 0.8F + 0.1F;
                float var10 = this.rand.nextFloat() * 0.8F + 0.1F;

                while (var7.count > 0) {
                    int var11 = this.rand.nextInt(21) + 10;
                    if (var11 > var7.count) {
                        var11 = var7.count;
                    }

                    var7.count -= var11;
                    Item var12 = new Item(level, ((float) x + var8), ((float) y + var9), ((float) z + var10), new ItemInstance(var7.itemId, var11, var7.getDamage()));
                    float var13 = 0.05F;
                    var12.velocityX = ((float) this.rand.nextGaussian() * var13);
                    var12.velocityY = ((float) this.rand.nextGaussian() * var13 + 0.2F);
                    var12.velocityZ = ((float) this.rand.nextGaussian() * var13);
                    level.spawnEntity(var12);
                }
            }
        }

        super.onBlockRemoved(level, x, y, z);
    }

    @Override
    public boolean canConnect(BlockView tileView, int x, int y, int z, int side) {
        BlockState blockState = ((BlockStateView)tileView).getBlockState(x, y, z);
        return side != blockState.get(FACING_PROPERTY).ordinal();
    }

    @Override
    public boolean canUse(Level level, int x, int y, int z, PlayerBase player) {
        if (player.getHeldItem() != null && player.getHeldItem().itemId == StapiTest.wrench.id) {
            level.setTile(x, y, z, 0);
            this.drop(level, x, y, z, level.getTileMeta(x, y, z));
            return false;
        }
        return super.canUse(level, x, y, z, player);
    }

    public int getMachineColor() {
        return ColorConverter.colorToInt(new Color(0xB7FFDC));
    }

//    @Override
//    public int getColor(BlockState state, @Nullable BlockView world, @Nullable TilePos pos, int tintIndex) {
//        return getMachineColor();
//    }
}
