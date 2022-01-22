package com.github.kydzombie.stapitest.container;

import com.github.kydzombie.stapitest.events.init.ItemListener;
import com.github.kydzombie.stapitest.tileentity.TileElectricFurnace;
import net.minecraft.container.ContainerBase;
import net.minecraft.container.slot.FurnaceOutput;
import net.minecraft.container.slot.Slot;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.player.PlayerInventory;

public class ContainerElectricFurnace extends ContainerBase {
    private TileElectricFurnace furnace;
    private int cookTime = 0;

    public ContainerElectricFurnace(PlayerInventory playerInventory, TileElectricFurnace furnace) {
        this.furnace = furnace;
        this.addSlot(new Slot(furnace, 0, 56, 17));
        this.addSlot(new FurnaceOutput(playerInventory.player, furnace, 1, 116, 35));

        int var3;
        for(var3 = 0; var3 < 3; ++var3) {
            for(int var4 = 0; var4 < 9; ++var4) {
                this.addSlot(new Slot(playerInventory, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for(var3 = 0; var3 < 9; ++var3) {
            this.addSlot(new Slot(playerInventory, var3, 8 + var3 * 18, 142));
        }

    }

    public boolean canUse(PlayerBase player) {
        if (player.getHeldItem() != null && player.getHeldItem().itemId == ItemListener.wrench.id) {
            return false;
        }
        return this.furnace.canPlayerUse(player);
    }
}
