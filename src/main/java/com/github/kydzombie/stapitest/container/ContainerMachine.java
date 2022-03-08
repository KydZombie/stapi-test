package com.github.kydzombie.stapitest.container;

import com.github.kydzombie.stapitest.events.init.StapiTest;
import com.github.kydzombie.stapitest.tileentity.TilePowered;
import net.minecraft.container.ContainerBase;
import net.minecraft.container.slot.Slot;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.player.PlayerInventory;

public abstract class ContainerMachine extends ContainerBase {
    final TilePowered tileEntity;

    public ContainerMachine(PlayerInventory playerInventory, TilePowered tileEntity) {
        this.tileEntity = tileEntity;

        int var3;
        for (var3 = 0; var3 < 3; ++var3) {
            for (int var4 = 0; var4 < 9; ++var4) {
                this.addSlot(new Slot(playerInventory, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3) {
            this.addSlot(new Slot(playerInventory, var3, 8 + var3 * 18, 142));
        }

    }

    @Override
    public boolean canUse(PlayerBase player) {
        if (player.getHeldItem() != null && player.getHeldItem().itemId == StapiTest.wrench.id) {
            return false;
        }
        return this.tileEntity.canPlayerUse(player);
    }
}
