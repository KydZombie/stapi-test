package com.github.kydzombie.stapitest.container;

import com.github.kydzombie.stapitest.tileentity.TileGrinder;
import net.minecraft.entity.player.PlayerInventory;

public class ContainerGrinder extends ProcessingContainer {
    public ContainerGrinder(PlayerInventory playerInventory, TileGrinder grinder) {
        super(playerInventory, grinder);

    }
}
