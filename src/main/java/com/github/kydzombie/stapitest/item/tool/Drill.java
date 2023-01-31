package com.github.kydzombie.stapitest.item.tool;

import net.modificationstation.stationapi.api.registry.Identifier;

public class Drill extends ElectricTool {
    public Drill(Identifier identifier) {
        super(identifier, "mineable/pickaxe");
    }
}
