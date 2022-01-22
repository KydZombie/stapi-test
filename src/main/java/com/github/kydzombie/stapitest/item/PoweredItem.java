package com.github.kydzombie.stapitest.item;

import com.github.kydzombie.stapitest.util.machine.power.ItemPowerStorage;
import jdk.internal.org.jline.utils.Colors;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.client.gui.CustomTooltipProvider;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;
import net.modificationstation.stationapi.api.util.Colours;

public abstract class PoweredItem extends TemplateItemBase implements ItemPowerStorage, CustomTooltipProvider {
    public PoweredItem(Identifier identifier, int maxPower) {
        super(identifier);
        this.setMaxStackSize(1);
        setDurability(maxPower);
    }

    @Override
    public int getCurrentPower(ItemInstance item) {
        return item.getDurability() - item.getDamage();
    }

    @Override
    public int getMaxPower(ItemInstance item) {
        return item.getDurability();
    }

    @Override
    public int charge(ItemInstance item, int chargeAmount, boolean simulate) {
        chargeAmount = Math.min(chargeAmount, item.getDamage());
        if (!simulate) {
            item.setDamage(item.getDamage() - chargeAmount);
        }

        return chargeAmount;
    }

    @Override
    public int consume(ItemInstance item, int consumeAmount, boolean simulate) {
        consumeAmount = Math.min(((PoweredItem)item.getType()).getCurrentPower(item), consumeAmount);
        if (!simulate) {
            item.setDamage(item.getDamage() + consumeAmount);
        }

        return consumeAmount;
    }

    @Override
    public String[] getTooltip(ItemInstance itemInstance, String originalTooltip) {
        return new String[]{
                originalTooltip,
                "" + Colours.RED + ((ItemPowerStorage) itemInstance.getType()).getCurrentPower(itemInstance) + Colours.WHITE + "/" + Colours.DARK_AQUA + ((ItemPowerStorage) itemInstance.getType()).getMaxPower(itemInstance) + Colours.WHITE + " power stored"
        };
    }
}
