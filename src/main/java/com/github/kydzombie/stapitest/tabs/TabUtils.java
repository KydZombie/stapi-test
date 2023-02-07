package com.github.kydzombie.stapitest.tabs;

import com.github.kydzombie.stapitest.events.init.StapiTest;
import com.github.kydzombie.stapitest.gui.ProcessingGui;
import net.glasslauncher.hmifabric.tabs.Tab;
import net.glasslauncher.hmifabric.tabs.TabCrafting;
import net.glasslauncher.hmifabric.tabs.TabSmelting;
import net.minecraft.block.BlockBase;
import net.minecraft.client.gui.screen.container.ContainerBase;
import net.minecraft.client.gui.screen.container.Crafting;
import net.minecraft.client.gui.screen.container.Furnace;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.registry.ModID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TabUtils {
    private static GrindingTab grinderTab;
    private static Map<Class<? extends ContainerBase>, ItemInstance> guiToBlock = new HashMap<>();

    public static void loadTabs(ArrayList<Tab> tabList) {
        grinderTab = new GrindingTab();
        tabList.add(grinderTab);
        guiToBlock.put(ProcessingGui.class, new ItemInstance(StapiTest.grinder));
    }
}
