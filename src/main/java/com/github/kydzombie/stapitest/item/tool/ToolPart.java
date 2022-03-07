package com.github.kydzombie.stapitest.item.tool;

import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

public class ToolPart extends TemplateItemBase {
    public ToolPart(Identifier identifier) {
        super(identifier);
        setMaxStackSize(1);
        setTranslationKey(identifier.toString());
    }
}
