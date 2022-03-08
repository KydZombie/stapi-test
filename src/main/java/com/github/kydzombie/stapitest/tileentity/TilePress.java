package com.github.kydzombie.stapitest.tileentity;

import com.github.kydzombie.stapitest.events.init.StapiTest;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;

public class TilePress extends ProcessingMachine {

    public TilePress() {
        super(1800, "Press", 120, 4);
    }

    @Override
    public ItemInstance getOutput(ItemInstance input) {
        int itemId = input.itemId;
        if (itemId == ItemBase.ironIngot.id) {
            return new ItemInstance(StapiTest.ironPlate);
        } else if (itemId == ItemBase.goldIngot.id) {
            return new ItemInstance(StapiTest.goldPlate);
        } else if (itemId == StapiTest.ironPlate.id) {
            return new ItemInstance(StapiTest.ironGear);
        } else if (itemId == StapiTest.goldPlate.id) {
            return new ItemInstance(StapiTest.goldGear);
        }
        return null;
    }
}
