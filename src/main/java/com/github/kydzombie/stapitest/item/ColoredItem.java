package com.github.kydzombie.stapitest.item;

import com.github.kydzombie.stapitest.util.ColorConverter;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

import java.awt.*;

public class ColoredItem extends TemplateItemBase {

    private final int color;

    public ColoredItem(Identifier identifier, Color color) {
        super(identifier);
        this.color = ColorConverter.colorToInt(color);
    }

    @Override
    public int getNameColour(int i) {
        return color;
    }
}
