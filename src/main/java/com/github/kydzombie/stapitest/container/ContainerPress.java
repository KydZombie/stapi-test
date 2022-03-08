package com.github.kydzombie.stapitest.container;

import com.github.kydzombie.stapitest.tileentity.TilePress;
import net.minecraft.entity.player.PlayerInventory;

public class ContainerPress extends ProcessingContainer {
    public ContainerPress(PlayerInventory playerInventory, TilePress press) {
        super(playerInventory, press);
    }
}
