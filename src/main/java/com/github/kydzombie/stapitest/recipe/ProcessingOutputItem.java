package com.github.kydzombie.stapitest.recipe;

import net.minecraft.item.ItemInstance;

public class ProcessingOutputItem {
    public ItemInstance item;
    public String material;

    public ProcessingOutputItem(ItemInstance item) {
        this.item = item;
    }

    public ProcessingOutputItem(ItemInstance item, String material) {
        this.item = item;
        this.material = material;
    }}
