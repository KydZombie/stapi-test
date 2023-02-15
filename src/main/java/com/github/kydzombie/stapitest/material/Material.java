package com.github.kydzombie.stapitest.material;

import com.github.kydzombie.stapitest.events.init.StapiTest;
import com.github.kydzombie.stapitest.item.DynamicItem;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.item.tool.ToolMaterialFactory;

import java.util.HashMap;

import static com.github.kydzombie.stapitest.events.init.StapiTest.ingot;

public class Material {

    public String name;
    private final ToolMaterial toolMaterial;
    private final int color;
    private ItemInstance craftingMaterial;

    private Material(int color, ToolMaterial baseMaterial, String name) {
        this.color = color;
        this.toolMaterial = baseMaterial;
        this.name = name;
    }

    public Material setCraftingMaterial(ItemInstance craftingMaterial) {
        this.craftingMaterial = craftingMaterial;
        return this;
    }

    public Material setCraftingMaterial(ItemBase craftingMaterial) {
        return setCraftingMaterial(new ItemInstance(craftingMaterial));
    }


    public static Material registerNewUniqueMaterial(int color, ToolMaterial baseMaterial, String name) {
        Material mat = new Material(color, baseMaterial, name);
        Materials.put(name, mat);
        return mat;
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

    public static class Builder {
        private ToolMaterial material;
        private int color;
        private String name;
        public ItemInstance item;
        public Builder(String name) {
            this.name = name;
        }
        public Material build() {
            return Material.registerNewUniqueMaterial(color, material, name);
        }
        public Builder toolProperties(ToolMaterial material){
            toolProperties(
                    material.getMiningLevel(), material.getDurability(),
                    material.getMiningSpeed(), material.getAttackDamage()
            );
            return this;
        }
        public Builder toolProperties(int miningLevel, int durability, float miningSpeed, int attackDamage) {
            // default for diamond is 3, 1561, 8.0, 3
            material = ToolMaterialFactory
                    .create(name, miningLevel, durability, miningSpeed, attackDamage);
            return this;
        }
        public Builder color(int rgb) {
            color = rgb;
            return this;
        }
        public Builder color(int r, int g, int b) {
            color = ((r & 0xFF) << 16)|((g & 0xFF) << 8)|((b & 0xFF) << 0);
            return this;
        }
        public Builder ingot(ItemBase itemBase) {
            ingot(new ItemInstance(itemBase));
            return this;
        }
        public Builder ingot(ItemInstance itemInstance) {
            item = DynamicItem.convert(itemInstance, name);
            return this;
        }
        public Builder ingot() {
            item = DynamicItem.convert(ingot, name);
            return this;
        }
    }
}
