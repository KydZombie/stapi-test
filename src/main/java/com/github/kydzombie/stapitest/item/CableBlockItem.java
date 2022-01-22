package com.github.kydzombie.stapitest.item;

import com.github.kydzombie.stapitest.util.ColorConverter;
import net.minecraft.block.BlockBase;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateSecondaryBlock;

import java.awt.*;

public class CableBlockItem extends TemplateSecondaryBlock {
    private int color;
    public CableBlockItem(Identifier identifier, BlockBase tile, Color color) {
        super(identifier, tile);
        this.color = ColorConverter.colorToInt(color);
    }

    @Override
    public int getNameColour(int i) {
        return color;
    }
}