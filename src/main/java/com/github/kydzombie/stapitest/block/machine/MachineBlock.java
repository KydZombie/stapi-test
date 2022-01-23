package com.github.kydzombie.stapitest.block.machine;

import com.github.kydzombie.stapitest.events.init.ItemListener;
import com.github.kydzombie.stapitest.events.init.TextureListener;
import com.github.kydzombie.stapitest.tileentity.TileMachine;
import com.github.kydzombie.stapitest.util.ColorConverter;
import com.github.kydzombie.stapitest.util.machine.Wrenchable;
import com.github.kydzombie.stapitest.util.machine.power.PowerConnection;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Item;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.BlockView;
import net.minecraft.level.Level;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;

import java.awt.*;
import java.util.Random;

public abstract class MachineBlock extends TemplateBlockWithEntity implements Wrenchable, PowerConnection {
    private final Random rand = new Random();

    public MachineBlock(Identifier identifier) {
        super(identifier, Material.METAL);
        this.hardness = 3.5f;
    }

    @Override
    public void onBlockPlaced(Level level, int x, int y, int z) {
        super.onBlockPlaced(level, x, y, z);
        ((TileMachine)level.getTileEntity(x, y, z)).updateAllConnections();
    }

    @Override
    public void afterPlaced(Level level, int x, int y, int z, Living living) {
        int var6 = MathHelper.floor((double)(living.yaw * 4.0F / 360.0F) + 0.5D) & 3;
        if (var6 == 0) {
            level.setTileMeta(x, y, z, 2);
        }

        if (var6 == 1) {
            level.setTileMeta(x, y, z, 5);
        }

        if (var6 == 2) {
            level.setTileMeta(x, y, z, 3);
        }

        if (var6 == 3) {
            level.setTileMeta(x, y, z, 4);
        }
    }

    @Override
    public void onBlockRemoved(Level level, int x, int y, int z) {
        TileMachine machineEntity = ((TileMachine)level.getTileEntity(x, y, z));
        machineEntity.updateAllConnections();

        for(int var6 = 0; var6 < machineEntity.getInventorySize(); ++var6) {
            ItemInstance var7 = machineEntity.getInventoryItem(var6);
            if (var7 != null) {
                float var8 = this.rand.nextFloat() * 0.8F + 0.1F;
                float var9 = this.rand.nextFloat() * 0.8F + 0.1F;
                float var10 = this.rand.nextFloat() * 0.8F + 0.1F;

                while(var7.count > 0) {
                    int var11 = this.rand.nextInt(21) + 10;
                    if (var11 > var7.count) {
                        var11 = var7.count;
                    }

                    var7.count -= var11;
                    Item var12 = new Item(level, ((float)x + var8), ((float)y + var9), ((float)z + var10), new ItemInstance(var7.itemId, var11, var7.getDamage()));
                    float var13 = 0.05F;
                    var12.velocityX = ((float)this.rand.nextGaussian() * var13);
                    var12.velocityY = ((float)this.rand.nextGaussian() * var13 + 0.2F);
                    var12.velocityZ = ((float)this.rand.nextGaussian() * var13);
                    level.spawnEntity(var12);
                }
            }
        }

        super.onBlockRemoved(level, x, y, z);
    }

    @Environment(EnvType.CLIENT)
    public int getTextureForSide(int side, int meta) {
        if (meta != 0) {
            if (side == meta) {
                return texture;
            }
            switch (side) {
                case 0:
                    return TextureListener.machineBottom;
                case 1:
                    return TextureListener.machineTop;
                default:
                    return TextureListener.machineSide;
            }
        }
        else {
            switch (side) {
                case 0:
                    return TextureListener.machineBottom;
                case 1:
                    return TextureListener.machineTop;
                case 3:
                    return texture;
                default:
                    return TextureListener.machineSide;

            }
        }
    }

    @Override
    public boolean canUse(Level level, int x, int y, int z, PlayerBase player) {
        if (player.getHeldItem() != null && player.getHeldItem().itemId == ItemListener.wrench.id) {
            level.setTile(x, y, z, 0);
            this.drop(level, x, y, z, level.getTileMeta(x, y, z));
            return true;
        }
        return super.canUse(level, x, y, z, player);
    }

    @Override
    public int getColourMultiplier(BlockView tileView, int x, int y, int z) {
        return ColorConverter.colorToInt(new Color(0xD8FFED));
    }
}
