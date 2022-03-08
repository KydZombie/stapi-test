package com.github.kydzombie.stapitest.tileentity;

import com.github.kydzombie.stapitest.events.init.StapiTest;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;

public class TileGrinder extends ProcessingMachine {


    public TileGrinder() {
        super(1200, "Grinder");
    }

    public ItemInstance getOutput(ItemInstance input) {
        int itemId = input.itemId;
        if (itemId == BlockBase.IRON_ORE.id || itemId == ItemBase.ironIngot.id) {
            return new ItemInstance(StapiTest.ironDust, 2);
        } else if (itemId == BlockBase.GOLD_ORE.id || itemId == ItemBase.goldIngot.id) {
            return new ItemInstance(StapiTest.goldDust, 2);
        }
        return null;
    }
}
