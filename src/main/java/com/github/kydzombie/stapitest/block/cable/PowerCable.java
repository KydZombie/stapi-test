package com.github.kydzombie.stapitest.block.cable;

import com.github.kydzombie.stapitest.events.init.ItemListener;
import com.github.kydzombie.stapitest.util.power.PowerConnection;
import net.modificationstation.stationapi.api.registry.Identifier;

import java.util.Random;

public class PowerCable extends Cable implements PowerConnection {
    public PowerCable(Identifier identifier) {
        super(identifier);
        this.connectTo = PowerConnection.class;
    }

    @Override
    public int getDropId(int meta, Random rand) {
        return ItemListener.powerCable.id;
    }
}
