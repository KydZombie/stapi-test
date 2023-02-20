package com.github.kydzombie.stapitest.tabs;

import com.github.kydzombie.stapitest.events.init.StapiTest;
import net.glasslauncher.hmifabric.HowManyItems;

public class TabUtils {
    public static ProcessingTab grinderTab;
    public static ProcessingTab pressTab;
    public static ProcessingTab centrifugeTab;
    public static ProcessingTab eFurnaceTab;

    public static void loadTabs() {
        grinderTab = new ProcessingTab(StapiTest.grinderRegistry.recipes, StapiTest.grinder);
        HowManyItems.addTab(grinderTab);
        pressTab = new ProcessingTab(StapiTest.pressRegistry.recipes, StapiTest.press);
        HowManyItems.addTab(pressTab);
        centrifugeTab = new ProcessingTab(StapiTest.centrifugeRegistry.recipes, StapiTest.centrifuge);
        HowManyItems.addTab(centrifugeTab);
        eFurnaceTab = new ProcessingTab(StapiTest.eFurnaceRegistry.recipes, StapiTest.electricFurnace);
        HowManyItems.addTab(eFurnaceTab);
    }
}
