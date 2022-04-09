package com.github.kydzombie.stapitest.custom;

import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;

import java.util.HashMap;

public class UniqueMaterial {

    private final ToolMaterial toolMaterial;
    private final int color;
    private ItemInstance craftingMaterial;

    public static HashMap<String, UniqueMaterial> materials = new HashMap<>();

    public UniqueMaterial(int color) {
        this.color = color;
        this.toolMaterial = null;
    }

    public UniqueMaterial(int color, ToolMaterial baseMaterial) {
        this.color = color;
        this.toolMaterial = baseMaterial;
    }

    public UniqueMaterial setCraftingMaterial(ItemInstance craftingMaterial) {
        this.craftingMaterial = craftingMaterial;
        return this;
    }

    public UniqueMaterial setCraftingMaterial(ItemBase craftingMaterial) {
        return setCraftingMaterial(new ItemInstance(craftingMaterial));
    }

    public static UniqueMaterial registerNewUniqueMaterial(int color, String name) {
        UniqueMaterial mat = new UniqueMaterial(color);
        materials.put(name, mat);
        return mat;
    }

    public static UniqueMaterial registerNewUniqueMaterial(int color, ToolMaterial baseMaterial) {
        return registerNewUniqueMaterial(color, baseMaterial, baseMaterial.name());
    }

    public static UniqueMaterial registerNewUniqueMaterial(int color, ToolMaterial baseMaterial, String name) {
        UniqueMaterial mat = new UniqueMaterial(color, baseMaterial);
        materials.put(name, mat);
        return mat;
    }

    public static UniqueMaterial getUniqueMaterial(String materialName) {
        return materials.getOrDefault(materialName, UniqueMaterial.materials.get("missingMaterial"));
    }

    public ToolMaterial getToolMaterial() {
        return toolMaterial;
    }

    public int getMaterialColor() {
        return color;
    }

    public ItemInstance getCraftingMaterial() {
        System.out.println(craftingMaterial);
        return craftingMaterial;
    }
}
