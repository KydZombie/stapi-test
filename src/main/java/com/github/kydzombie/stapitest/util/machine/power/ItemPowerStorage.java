package com.github.kydzombie.stapitest.util.machine.power;

import net.minecraft.item.ItemInstance;

public interface ItemPowerStorage {

    int getCurrentPower(ItemInstance item);

    int getMaxPower(ItemInstance item);

    int charge(ItemInstance item, int chargeAmount, boolean simulate);

    int consume(ItemInstance item, int consumeAmount, boolean simulate);

}
