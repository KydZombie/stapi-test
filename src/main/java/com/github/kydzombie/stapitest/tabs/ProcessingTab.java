package com.github.kydzombie.stapitest.tabs;

import net.glasslauncher.hmifabric.HowManyItems;
import net.glasslauncher.hmifabric.Utils;
import net.glasslauncher.hmifabric.tabs.TabWithTexture;
import net.minecraft.block.BlockBase;
import net.minecraft.client.gui.screen.container.ContainerBase;
import net.minecraft.client.gui.screen.container.Crafting;
import net.minecraft.client.render.Tessellator;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.recipe.RecipeRegistry;
import net.minecraft.recipe.ShapelessRecipe;
import net.modificationstation.stationapi.api.recipe.StationRecipe;
import net.modificationstation.stationapi.api.registry.ItemRegistry;
import net.modificationstation.stationapi.api.registry.ModID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;

import com.github.kydzombie.stapitest.events.init.StapiTest;
import net.modificationstation.stationapi.api.tag.TagKey;

public abstract class ProcessingTab extends TabWithTexture {
    protected final List<ItemInstance[]> recipes;
    private final BlockBase tabBlock;
    private final List<ItemInstance[]> recipesComplete;
    public ArrayList<Class<? extends ContainerBase>> guiCraftingStations = new ArrayList<>();

    public ProcessingTab(ModID tabCreator) {
        this(tabCreator, new ArrayList<ItemInstance[]>(RecipeRegistry.getInstance().getRecipes()), BlockBase.SAND);
        guiCraftingStations.add(Crafting.class);
    }
    public ProcessingTab(ModID tabCreator, List<ItemInstance[]> recipesComplete, BlockBase tabBlock) {
        this(tabCreator, 2, recipesComplete, tabBlock, "/gui/crafting.png", 118, 56, 28, 15, 3);
    }

    public ProcessingTab(ModID tabCreator, int slotsPerRecipe, List<ItemInstance[]> recipesComplete, BlockBase tabBlock, String texturePath, int width, int height, int textureX, int textureY, int slotsWidth) {
        super(tabCreator, slotsPerRecipe, texturePath, width, height, 3, 4, textureX, textureY);
        this.recipesComplete = recipesComplete;
        this.tabBlock = tabBlock;
        recipes = recipesComplete;

        slots[0] = new Integer[]{62, 23};
        slots[1] = new Integer[]{2, 5};
//        equivalentCraftingStations.add(getTabItem());
    }

//    @Override
//    public void draw(int x, int y, int recipeOnThisPageIndex, int recipeIndex, int cursorX, int cursorY) {
//        super.draw(x, y, recipeOnThisPageIndex, recipeIndex, cursorX, cursorY);
//        if (recipeIndex < recipes.size() && recipes.get(recipeIndex) instanceof ItemInstance[]) {
//            Utils.bindTexture("/assets/hmifabric/textures/shapeless_icon.png");
//            double size = 8;
//            x += 80;
//            y += 16;
//            Tessellator tess = Tessellator.INSTANCE;
//            tess.start();
//            tess.vertex(x, y + size, 0, 0, 1);
//            tess.vertex(x + size, y + size, 0, 1, 1);
//            tess.vertex(x + size, y, 0, 1, 0);
//            tess.vertex(x, y, 0, 0, 0);
//            tess.draw();
//        }
//    }
//
    @Override
    public ItemInstance[][] getItems(int index, ItemInstance filter) {
        ItemInstance[][] items = new ItemInstance[recipesPerPage][];
        HowManyItems.logger.log(Level.FINE, "getItems");
        for (int j = 0; j < recipesPerPage; j++) {
            items[j] = new ItemInstance[slots.length];
            int k = index + j;
            if (k < recipes.size()) try {
                ItemInstance[] recipe = recipes.get(k);
                items[j][0] = recipe[1];
                items[j][1] = recipe[0];
                HowManyItems.logger.log(Level.FINE, recipe[0].getTranslationKey().concat(" | ").concat(recipe[1].getTranslationKey()));
//                for (int j1 = 0; j1 < input.length; j1++) {
//                    ItemInstance item = input[j1];
//                    items[j][j1 + 1] = item;
//                    if (item != null && item.getDamage() == -1) {
//                        if (item.usesMeta()) {
//                            if (filter != null && item.itemId == filter.itemId) {
//                                items[j][j1 + 1] = new ItemInstance(item.getType(), 0, filter.getDamage());
//                            } else {
//                                items[j][j1 + 1] = new ItemInstance(item.getType());
//                            }
//                        } else if (filter != null && item.itemId == filter.itemId) {
//                            items[j][j1 + 1] = new ItemInstance(item.getType(), 0, filter.getDamage());
//                        }
//                    }
//                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }


            if (items[j][0] == null && recipesOnThisPage > j) {
                recipesOnThisPage = j;
                redrawSlots = true;
                break;
            }
            if (items[j][0] != null && recipesOnThisPage == j) {
                recipesOnThisPage = j + 1;
                redrawSlots = true;
            }
        }
        return items;
    }

    @Override
    public void updateRecipes(ItemInstance filter, Boolean getUses) {
        HowManyItems.logger.log(Level.FINE, "updateRecipes");
        recipes.clear();
        updateRecipesWithoutClear(filter, getUses);
    }

    public void updateRecipesWithoutClear(ItemInstance filter, Boolean getUses) {
        lastIndex = 0;
        HowManyItems.logger.log(Level.FINE, "updateRecipesWithoutClear");
        for (ItemInstance[] recipe : recipesComplete) {
            int dmg = 0;
            if (filter != null) dmg = filter.getDamage();

            ItemInstance input = recipe[0];
            ItemInstance output = recipe[1];

            if (input != null &&
                    (filter == null ||
                            (!getUses && output.itemId == filter.itemId &&
                                    (
                                            output.getDamage() == filter.getDamage() ||
                                            output.getDamage() < 0 ||
                                            !output.usesMeta()
                                    )
                            )
                    )
            ) {
                recipes.add(recipe);
            } else if (filter == null) throw new ClassCastException("Invalid recipe item type " + input.getClass().getName() + "!");
        }
        size = recipes.size();
        super.updateRecipes(filter, getUses);
        size = recipes.size();
    }

    @Override
    public ItemInstance getTabItem() {
        HowManyItems.logger.log(Level.FINE, "getTabItem");
        return new ItemInstance(StapiTest.generator);
    }
}
