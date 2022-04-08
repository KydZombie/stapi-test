package com.github.kydzombie.stapitest.tileentity;

import com.github.kydzombie.stapitest.events.init.StapiTest;

public class TileCentrifuge extends ProcessingMachine {
    public TileCentrifuge() {
        super("Centrifuge");
        setPowerUsage(3);
        setRecipeHandler(StapiTest.centrifugeRegistry);
    }
}
