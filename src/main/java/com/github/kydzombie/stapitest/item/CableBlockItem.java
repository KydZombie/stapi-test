package com.github.kydzombie.stapitest.item;

import com.github.kydzombie.stapitest.custom.util.ColorConverter;
import net.minecraft.block.BlockBase;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateSecondaryBlock;

import java.awt.*;

public class CableBlockItem extends TemplateSecondaryBlock {
    private final int color;

    public CableBlockItem(Identifier identifier, BlockBase tile, Color color) {
        super(identifier, tile);
        this.color = ColorConverter.colorToInt(color);
        setTranslationKey(identifier.toString());
    }

    @Override
    public int getColourMultiplier(int i) {
        return color;
    }
}
