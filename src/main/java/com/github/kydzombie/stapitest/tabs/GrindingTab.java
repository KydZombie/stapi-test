package com.github.kydzombie.stapitest.tabs;

import com.github.kydzombie.stapitest.events.init.StapiTest;
import net.glasslauncher.hmifabric.HowManyItems;
import net.minecraft.item.ItemInstance;
import java.util.logging.Level;

public class GrindingTab extends ProcessingTab {
    public GrindingTab() {
        super(StapiTest.MOD_ID, StapiTest.grinderRegistry.recipes, StapiTest.grinder);
        for (int i = 0; i < StapiTest.grinderRegistry.recipes.size(); i++) {
            HowManyItems.logger.log(Level.INFO, String.valueOf(i));
            ItemInstance[] recipe = StapiTest.grinderRegistry.recipes.get(i);
            HowManyItems.logger.log(Level.INFO, recipe[0].getTranslationKey().concat(recipe[1].getTranslationKey()));
        }

    }

    @Override
    public ItemInstance getTabItem() {
        return new ItemInstance(StapiTest.grinder);
    }

}
