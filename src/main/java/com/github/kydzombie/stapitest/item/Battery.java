package com.github.kydzombie.stapitest.item;

import net.modificationstation.stationapi.api.registry.Identifier;

public class Battery extends PoweredItem {
    public Battery(Identifier identifier, int maxPower) {
        super(identifier, maxPower);
    }
}
