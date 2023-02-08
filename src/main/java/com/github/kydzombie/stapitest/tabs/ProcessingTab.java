package com.github.kydzombie.stapitest.tabs;

import net.glasslauncher.hmifabric.tabs.TabWithTexture;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.registry.ModID;

import java.util.ArrayList;
import java.util.List;

import com.github.kydzombie.stapitest.events.init.StapiTest;

import static com.github.kydzombie.stapitest.tabs.TabUtils.compare;

public class ProcessingTab extends TabWithTexture {
    protected List<ItemInstance[]> recipes;
    private final BlockBase tabBlock;
    private final List<ItemInstance[]> recipesReady;
//    public ArrayList<Class<? extends ContainerBase>> guiCraftingStations = new ArrayList<>();

//    public ProcessingTab(ModID tabCreator) {
//        this(tabCreator, new ArrayList<ItemInstance[]>(RecipeRegistry.getInstance().getRecipes()), BlockBase.SAND);
//        guiCraftingStations.add(Crafting.class);
//    }
    public ProcessingTab(List<ItemInstance[]> recipesReady, BlockBase tabBlock) {
        this(StapiTest.MOD_ID, 2, recipesReady, tabBlock, "/assets/stapitest/stationapi/gui/basic_machine.png", 118, 56, 28, 15, 3);
    }

    public ProcessingTab(ModID tabCreator, int slotsPerRecipe, List<ItemInstance[]> recipesReady, BlockBase tabBlock, String texturePath, int width, int height, int textureX, int textureY, int slotsWidth) {
        super(tabCreator, slotsPerRecipe, texturePath, width, height, 3, 4, textureX, textureY);
        this.recipesReady = recipesReady;
        this.tabBlock = tabBlock;
        recipes = new ArrayList<>();
//        System.out.print("recipes: ");
//        System.out.println(this.recipesReady.size());

        slots[0] = new Integer[]{88, 23};
        slots[1] = new Integer[]{28, 23};
//        equivalentCraftingStations.add(getTabItem());
    }

    @Override
    public void draw(int x, int y, int recipeOnThisPageIndex, int recipeIndex, int cursorX, int cursorY) {
        super.draw(x, y, recipeOnThisPageIndex, recipeIndex, cursorX, cursorY);
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
    }
//
    @Override
    public ItemInstance[][] getItems(int index, ItemInstance filter) {
        ItemInstance[][] items = new ItemInstance[recipesPerPage][];
        for (int j = 0; j < recipesPerPage; j++) {
            items[j] = new ItemInstance[slots.length];
            int k = index + j;
            if (k < recipes.size()) try {
                ItemInstance[] recipe = recipes.get(k);
                items[j][0] = recipe[1];
                items[j][1] = recipe[0];
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
        recipes.clear();
        updateRecipesWithoutClear(filter, getUses);
    }

    public void updateRecipesWithoutClear(ItemInstance filter, Boolean getUses) {
        lastIndex = 0;
        System.out.println(recipesReady.size());
        recipesReady.forEach(recipe -> {
            ItemInstance input = recipe[0];
            ItemInstance output = recipe[1];

            if (filter == null ||
               (!getUses && compare(filter, output)) ||
               (getUses && compare(filter, input))
            ) {
                System.out.println("gut");
                recipes.add(recipe);
            }
        });
        size = recipes.size();
    }

    @Override
    public ItemInstance getTabItem() {
        return new ItemInstance(tabBlock);
    }
}
