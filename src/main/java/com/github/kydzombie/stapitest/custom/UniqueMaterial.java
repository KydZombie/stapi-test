package com.github.kydzombie.stapitest.custom;

import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.item.tool.ToolMaterialFactory;

import java.util.ArrayList;
import java.util.List;

public class UniqueMaterial {

    private final ToolMaterial baseMaterial;
    private final String name;
    private final int color;

    public static List<UniqueMaterial> materials = new ArrayList<>();

    public UniqueMaterial(ToolMaterial baseMaterial, String name, int color) {
        this.baseMaterial = baseMaterial;
        this.name = name;
        this.color = color;
    }

    public static void registerNewToolMaterial(ToolMaterial baseMaterial, String name, int color) {
        materials.add(new UniqueMaterial(baseMaterial, name, color));
        materials.add(new UniqueMaterial(ToolMaterialFactory.create("dead_" + baseMaterial, 0, baseMaterial.getDurability(), 0, 0), "dead_" + name, color));
    }

    public static UniqueMaterial findToolMaterial(String materialName) {
        for (UniqueMaterial eMaterial: materials) {
            if (eMaterial.name.equals(materialName)) {
                return eMaterial;
            }
        }
        return findToolMaterial("missingMaterial");
    }

    public ToolMaterial getToolMaterial() {
        return baseMaterial;
    }

    public int getMaterialColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
