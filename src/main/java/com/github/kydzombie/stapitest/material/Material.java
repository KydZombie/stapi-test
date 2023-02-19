package com.github.kydzombie.stapitest.material;

import com.github.kydzombie.stapitest.custom.util.ColorConverter;
import com.github.kydzombie.stapitest.events.init.StapiTest;
import com.github.kydzombie.stapitest.item.DynamicItem;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.item.tool.ToolMaterialFactory;

import java.awt.*;
import java.util.HashMap;

public class Material {

    public String name;
    private final ToolMaterial toolMaterial;
    private final int color;
    private HashMap<String, ItemInstance> states;

    private Material(int color, ToolMaterial baseMaterial, String name) {
        this.color = color;
        this.toolMaterial = baseMaterial;
        this.name = name;
        this.states = new HashMap<>();
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
        new Color(0x228844);
        return color;
    }

    public static class Builder {
        private ToolMaterial material;
        private int color;
        private String name;
        private HashMap<String, ItemInstance> states;
        public Builder(String name) {
            this.name = name;
            this.states = new HashMap<>();
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
            return color(new Color(rgb));
        }
        public Builder color(int r, int g, int b) {
            return color(new Color(r,g,b));
        }
        public Builder color(Color rgba) {
            color = ColorConverter.colorToInt(rgba);
            return this;
        }
        public Builder ingot(ItemBase itemBase) {
            ingot(new ItemInstance(itemBase));
            return this;
        }
        public Builder ingot(ItemInstance itemInstance) {
            states.put("ingot", itemInstance);
            return this;
        }
        public Builder ingot() {
            states.put("ingot", DynamicItem.ingot(name));
            return this;
        }

        public Builder dust(ItemBase itemBase) {
            dust(new ItemInstance(itemBase));
            return this;
        }
        public Builder dust(ItemInstance itemInstance) {
            states.put("dust", itemInstance);
            return this;
        }
        public Builder dust() {
            states.put("ingot", DynamicItem.dust(name));
            return this;
        }
    }
}
