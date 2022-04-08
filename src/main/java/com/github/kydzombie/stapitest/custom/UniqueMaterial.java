package com.github.kydzombie.stapitest.custom;

import net.minecraft.item.ItemBase;
import net.minecraft.item.tool.ToolMaterial;

import java.util.HashMap;

public class UniqueMaterial {

    private final ToolMaterial baseMaterial;
    private final int color;
    private final ItemBase craftingMaterial;

    public static HashMap<String, UniqueMaterial> materials = new HashMap<>();

    public UniqueMaterial(ToolMaterial baseMaterial, int color, ItemBase craftingMaterial) {
        this.baseMaterial = baseMaterial;
        this.color = color;
        this.craftingMaterial = craftingMaterial;
    }

    public static void registerNewUniqueMaterial(ToolMaterial baseMaterial, int color, ItemBase craftingMaterial) {
        materials.put(baseMaterial.name(), new UniqueMaterial(baseMaterial, color, craftingMaterial));
    }

    public static void registerNewUniqueMaterial(ToolMaterial baseMaterial, String name, int color, ItemBase craftingMaterial) {
        materials.put(name, new UniqueMaterial(baseMaterial, color, craftingMaterial));
    }

    public ToolMaterial getToolMaterial() {
        return baseMaterial;
    }

    public int getMaterialColor() {
        return color;
    }

    public ItemBase getCraftingMaterial() {
        System.out.println(craftingMaterial);
        return craftingMaterial;
    }
}
