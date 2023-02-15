package com.github.kydzombie.stapitest.material.materials;

import com.github.kydzombie.stapitest.material.Material;

public class ElementMaterials {
    public static void register() {
        new Material.Builder("actinium")
                .color(0xC3D1FF)
//            .iconSet(METALLIC)
//            .element(Elements.Ac)
                .build();

        new Material.Builder("aluminium")
                .ingot()
//                .fluid()
//                .ore()
                .color(0x80C8F0)
//            .flags(EXT2_METAL, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_RING, GENERATE_FRAME, GENERATE_SPRING, GENERATE_SPRING_SMALL, GENERATE_FINE_WIRE)
//            .element(Elements.Al)
                .toolProperties(2, 768, 6, 7)
//                .rotorStats(10.0f, 2.0f, 128)
//            .cableProperties(GTValues.V[4], 1, 1)
//            .fluidPipeProperties(1166, 100, true)
//            .blastTemp(1700, GasTier.LOW)
//            .fluidTemp(933)
                .build();

        new Material.Builder("americium")
                .ingot() //miningLevel: 3
//                .fluid()
                .color(0x287869)
//                .iconSet(METALLIC)
//                .flags(EXT_METAL, GENERATE_FOIL, GENERATE_FINE_WIRE)
//                .element(Elements.Am)
//                .itemPipeProperties(64, 64)
//                .fluidTemp(1449)
                .build();

        new Material.Builder("antimony")
                .ingot()
//                .fluid()
                .color(0xDCDCF0)
//                .iconSet(SHINY)
//                .flags(MORTAR_GRINDABLE)
//                .element(Elements.Sb)
//                .fluidTemp(904)
                .build();

        new Material.Builder("argon")
//                .fluid(FluidTypes.GAS).plasma()
                .color(0x00FF00)
//                .iconSet(GAS)
//                .element(Elements.Ar)
                .build();

        new Material.Builder("arsenic")
//                .dust().fluid()
                .color(0x676756)
//                .element(Elements.As)
//                .fluidTemp(887)
                .build();

        new Material.Builder("astatine")
                .color(0x241A24)
//                .element(Elements.At)
                .build();

        new Material.Builder("barium")
//                .dust()
                .color(0x83824C)
//                .iconSet(METALLIC)
//                .element(Elements.Ba)
                .build();

        new Material.Builder("berkelium")
                .color(0x645A88)
//                .iconSet(METALLIC)
//                .element(Elements.Bk)
                .build();

        new Material.Builder("beryllium")
                .ingot()
//                .fluid().ore()
                .color(0x64B464)
//                .iconSet(METALLIC)
//                .flags(STD_METAL)
//                .element(Elements.Be)
//                .fluidTemp(1560)
                .build();

        new Material.Builder("bismuth")
                .ingot() // miningLevel: 1
//                .fluid()
                .color(0x64A0A0)
//                .iconSet(METALLIC)
//                .element(Elements.Bi)
//                .fluidTemp(545)
                .build();

        new Material.Builder("bohrium")
                .color(0xDC57FF)
//                .iconSet(SHINY)
//                .element(Elements.Bh)
                .build();

        new Material.Builder("boron")
//                .dust()
                .color(0xD2FAD2)
//                .element(Elements.B)
                .build();

        new Material.Builder("bromine")
                .color(0x500A0A)
//                .iconSet(SHINY)
//                .element(Elements.Br)
                .build();

        new Material.Builder("caesium")
//                .dust()
                .color(0x80620B)
//                .iconSet(METALLIC)
//                .element(Elements.Cs)
                .build();

        new Material.Builder("calcium")
//                .dust()
                .color(0xFFF5DE)
//                .iconSet(METALLIC)
//                .element(Elements.Ca)
                .build();

        new Material.Builder("californium")
                .color(0xA85A12)
//                .iconSet(METALLIC)
//                .element(Elements.Cf)
                .build();

        new Material.Builder("carbon")
//                .dust().fluid()
                .color(0x141414)
//                .element(Elements.C)
//                .fluidTemp(4600)
                .build();

        new Material.Builder("cadmium")
//                .dust()
                .color(0x32323C)
//                .iconSet(SHINY)
//                .element(Elements.Cd)
                .build();

        new Material.Builder("cerium")
//                .dust().fluid()
                .color(0x87917D)
//                .iconSet(METALLIC)
//                .element(Elements.Ce)
//                .fluidTemp(1068)
                .build();

        new Material.Builder("chlorine")
//                .fluid(FluidTypes.GAS)
//                .element(Elements.Cl)
                .build();

        new Material.Builder("chrome")
                .ingot() // miningLevel: 3
//                .fluid()
                .color(0xEAC4D8)
//                .iconSet(SHINY)
//                .flags(EXT_METAL, GENERATE_ROTOR)
//                .element(Elements.Cr)
//                .rotorStats(12.0f, 3.0f, 512)
//                .fluidPipeProperties(2180, 35, true, true, false, false)
//                .blastTemp(1700, GasTier.LOW)
//                .fluidTemp(2180)
                .build();

        new Material.Builder("cobalt")
                .ingot()
//                .fluid().ore() // leave for TiCon ore processing
                .color(0x5050FA)
//                .iconSet(METALLIC)
//                .flags(EXT_METAL)
//                .element(Elements.Co)
//                .cableProperties(GTValues.V[1], 2, 2)
//                .itemPipeProperties(2560, 2.0f)
//                .fluidTemp(1768)
                .build();

        new Material.Builder("copernicium")
                .color(0xFFFEFF)
//                .element(Elements.Cn)
                .build();

        new Material.Builder("copper")
                .ingot() // miningLevel: 1
//                .fluid().ore()
                .color(0xFF6400)
//                .iconSet(SHINY)
//                .flags(EXT_METAL, MORTAR_GRINDABLE, GENERATE_SPRING, GENERATE_SPRING_SMALL, GENERATE_FINE_WIRE)
//                .element(Elements.Cu)
//                .cableProperties(GTValues.V[2], 1, 2)
//                .fluidPipeProperties(1696, 6, true)
//                .fluidTemp(1358)
                .build();

        new Material.Builder("curium")
                .color(0x7B544E)
//                .iconSet(METALLIC)
//                .element(Elements.Cm)
                .build();

        new Material.Builder("darmstadtium")
                .ingot()
//                .fluid()
                .color(0x578062)
//                .flags(EXT2_METAL, GENERATE_ROTOR, GENERATE_DENSE, GENERATE_SMALL_GEAR)
//                .element(Elements.Ds)
                .build();

        new Material.Builder("deuterium")
//                .fluid(FluidTypes.GAS)
//                .element(Elements.D)
                .build();

        new Material.Builder("dubnium")
                .color(0xD3FDFF)
//                .iconSet(SHINY)
//                .element(Elements.Db)
                .build();

        new Material.Builder("dysprosium")
//                .iconSet(METALLIC)
//                .element(Elements.Dy)
                .build();

        new Material.Builder("einsteinium")
                .color(0xCE9F00)
//                .iconSet(METALLIC)
//                .element(Elements.Es)
                .build();

        new Material.Builder("erbium")
//                .iconSet(METALLIC)
//                .element(Elements.Er)
                .build();

        new Material.Builder("europium")
                .ingot()
//                .fluid()
                .color(0x20FFFF)
//                .iconSet(METALLIC)
//                .flags(STD_METAL, GENERATE_LONG_ROD, GENERATE_FINE_WIRE, GENERATE_SPRING, GENERATE_FOIL, GENERATE_FRAME)
//                .element(Elements.Eu)
//                .cableProperties(GTValues.V[GTValues.UHV], 2, 32)
//                .fluidPipeProperties(7750, 300, true)
//                .blastTemp(6000, GasTier.MID, VA[IV], 180)
//                .fluidTemp(1099)
                .build();

        new Material.Builder("fermium")
                .color(0x984ACF)
//                .iconSet(METALLIC)
//                .element(Elements.Fm)
                .build();

        new Material.Builder("flerovium")
//                .iconSet(SHINY)
//                .element(Elements.Fl)
                .build();

        new Material.Builder("fluorine")
//                .fluid(FluidTypes.GAS)
//                .element(Elements.F)
                .build();

        new Material.Builder("francium")
                .color(0xAAAAAA)
//                .iconSet(SHINY)
//                .element(Elements.Fr)
                .build();

        new Material.Builder("gadolinium")
                .color(0xDDDDFF)
//                .iconSet(METALLIC)
//                .element(Elements.Gd)
                .build();

        new Material.Builder("gallium")
                .ingot()
//                .fluid()
                .color(0xDCDCFF)
//                .iconSet(SHINY)
//                .flags(STD_METAL, GENERATE_FOIL)
//                .element(Elements.Ga)
//                .fluidTemp(303)
                .build();

        new Material.Builder("germanium")
                .color(0x434343)
//                .iconSet(SHINY)
//                .element(Elements.Ge)
                .build();

        new Material.Builder("gold")
                .ingot()
//                .fluid().ore()
                .color(0xFFE650)
//                .iconSet(SHINY)
//                .flags(EXT2_METAL, GENERATE_RING, MORTAR_GRINDABLE, EXCLUDE_BLOCK_CRAFTING_BY_HAND_RECIPES, GENERATE_SPRING, GENERATE_SPRING_SMALL, GENERATE_FINE_WIRE, GENERATE_FOIL)
//                .element(Elements.Au)
//                .cableProperties(GTValues.V[3], 3, 2)
//                .fluidPipeProperties(1671, 25, true, true, false, false)
//                .fluidTemp(1337)
                .build();

        new Material.Builder("hafnium")
                .color(0x99999A)
//                .iconSet(SHINY)
//                .element(Elements.Hf)
                .build();

        new Material.Builder("hassium")
                .color(0xDDDDDD)
//                .element(Elements.Hs)
                .build();

        new Material.Builder("holmium")
//                .iconSet(METALLIC)
//                .element(Elements.Ho)
                .build();

        new Material.Builder("hydrogen")
//                .fluid(FluidTypes.GAS)
                .color(0x0000B5)
//                .element(Elements.H)
                .build();

        new Material.Builder("helium")
//                .fluid(FluidTypes.GAS).plasma()
//                .element(Elements.He)
                .build();

        new Material.Builder("helium_3")
//                .fluid(FluidTypes.GAS)
//                .element(Elements.He3)
                .build();

        new Material.Builder("indium")
                .ingot()
//                .fluid()
                .color(0x400080)
//                .iconSet(SHINY)
//                .element(Elements.In)
//                .fluidTemp(430)
                .build();

        new Material.Builder("iodine")
                .color(0x2C344F)
//                .iconSet(SHINY)
//                .element(Elements.I)
                .build();

        new Material.Builder("iridium")
                .ingot() // miningLevel: 3
//                .fluid()
                .color(0xA1E4E4)
//                .iconSet(METALLIC)
//                .flags(EXT2_METAL, GENERATE_FINE_WIRE, GENERATE_GEAR)
//                .element(Elements.Ir)
//                .rotorStats(7.0f, 3.0f, 2560)
//                .fluidPipeProperties(3398, 250, true, false, true, false)
//                .blastTemp(4500, GasTier.HIGH, VA[IV], 1100)
//                .fluidTemp(2719)
                .build();

        new Material.Builder("iron")
                .ingot()
//                .fluid().plasma().ore()
                .color(0xC8C8C8)
//                .iconSet(METALLIC)
//                .flags(EXT2_METAL, MORTAR_GRINDABLE, GENERATE_ROTOR, GENERATE_SMALL_GEAR, GENERATE_GEAR, GENERATE_SPRING_SMALL, GENERATE_SPRING, EXCLUDE_BLOCK_CRAFTING_BY_HAND_RECIPES, BLAST_FURNACE_CALCITE_TRIPLE)
//                .element(Elements.Fe)
                .toolProperties(2, 256, 2, 2)
//                .toolStats(ToolProperty.Builder.of(2.0F, 2.0F, 256, 2)
//                        .enchantability(14).build())
//                .rotorStats(7.0f, 2.5f, 256)
//                .cableProperties(GTValues.V[2], 2, 3)
//                .fluidTemp(1811)
                .build();

        new Material.Builder("krypton")
//                .fluid(FluidTypes.GAS)
                .color(0x80FF80)
//                .iconSet(GAS)
//                .element(Elements.Kr)
                .build();

        new Material.Builder("lanthanum")
//                .dust().fluid()
                .color(0x5D7575)
//                .iconSet(METALLIC)
//                .element(Elements.La)
//                .fluidTemp(1193)
                .build();

        new Material.Builder("lawrencium")
//                .iconSet(METALLIC)
//                .element(Elements.Lr)
                .build();

        new Material.Builder("lead")
                .ingot() // miningLevel: 1
//                .fluid().ore()
                .color(0x8C648C)
//                .flags(EXT2_METAL, MORTAR_GRINDABLE, GENERATE_ROTOR, GENERATE_SPRING, GENERATE_SPRING_SMALL, GENERATE_FINE_WIRE)
//                .element(Elements.Pb)
//                .cableProperties(GTValues.V[0], 2, 2)
//                .fluidPipeProperties(1200, 8, true)
//                .fluidTemp(600)
                .build();

        new Material.Builder("lithium")
//                .dust().fluid().ore()
                .color(0xBDC7DB)
//                .element(Elements.Li)
//                .fluidTemp(454)
                .build();

        new Material.Builder("livermorium")
                .color(0xAAAAAA)
//                .iconSet(SHINY)
//                .element(Elements.Lv)
                .build();

        new Material.Builder("lutetium")
//                .dust().fluid()
                .color(0x00AAFF)
//                .iconSet(METALLIC)
//                .element(Elements.Lu)
//                .fluidTemp(1925)
                .build();

        new Material.Builder("magnesium")
//                .dust().fluid()
                .color(0xFFC8C8)
//                .iconSet(METALLIC)
//                .element(Elements.Mg)
//                .fluidTemp(923)
                .build();

        new Material.Builder("mendelevium")
                .color(0x1D4ACF)
//                .iconSet(METALLIC)
//                .element(Elements.Md)
                .build();

        new Material.Builder("manganese")
                .ingot()
//                .fluid()
                .color(0xCDE1B9)
//                .flags(STD_METAL, GENERATE_FOIL, GENERATE_BOLT_SCREW)
//                .element(Elements.Mn)
//                .rotorStats(7.0f, 2.0f, 512)
//                .fluidTemp(1519)
                .build();

        new Material.Builder("meitnerium")
                .color(0x2246BE)
//                .iconSet(SHINY)
//                .element(Elements.Mt)
                .build();

        new Material.Builder("mercury")
//                .fluid()
                .color(0xE6DCDC)
//                .iconSet(DULL)
//                .element(Elements.Hg)
                .build();

        new Material.Builder("molybdenum")
                .ingot()
//                .fluid().ore()
                .color(0xB4B4DC)
//                .iconSet(SHINY)
//                .element(Elements.Mo)
//                .flags(GENERATE_FOIL, GENERATE_BOLT_SCREW)
//                .rotorStats(7.0f, 2.0f, 512)
//                .fluidTemp(2896)
                .build();

        new Material.Builder("moscovium")
                .color(0x7854AD)
//                .iconSet(SHINY)
//                .element(Elements.Mc)
                .build();

        new Material.Builder("neodymium")
                .ingot()
//                .fluid().ore()
                .color(0x646464)
//                .iconSet(METALLIC)
//                .flags(STD_METAL, GENERATE_ROD, GENERATE_BOLT_SCREW)
//                .element(Elements.Nd)
//                .rotorStats(7.0f, 2.0f, 512)
//                .blastTemp(1297, GasTier.MID)
                .build();

        new Material.Builder("neon")
//                .fluid(FluidTypes.GAS)
                .color(0xFAB4B4)
//                .iconSet(GAS)
//                .element(Elements.Ne)
                .build();

        new Material.Builder("neptunium")
                .color(0x284D7B)
//                .iconSet(METALLIC)
//                .element(Elements.Np)
                .build();

        new Material.Builder("nickel")
                .ingot()
//                .fluid().plasma().ore()
                .color(0xC8C8FA)
//                .iconSet(METALLIC)
//                .flags(STD_METAL, MORTAR_GRINDABLE)
//                .element(Elements.Ni)
//                .cableProperties(GTValues.V[GTValues.LV], 3, 3)
//                .itemPipeProperties(2048, 1.0f)
//                .fluidTemp(1728)
                .build();

        new Material.Builder("nihonium")
                .color(0x08269E)
//                .iconSet(SHINY)
//                .element(Elements.Nh)
                .build();

        new Material.Builder("niobium")
                .ingot()
//                .fluid()
                .color(0xBEB4C8)
//                .iconSet(METALLIC)
//                .element(Elements.Nb)
//                .blastTemp(2750, GasTier.MID, VA[HV], 900)
                .build();

        new Material.Builder("nitrogen")
//                .fluid(FluidTypes.GAS).plasma()
                .color(0x00BFC1)
//                .iconSet(GAS)
//                .element(Elements.N)
                .build();

        new Material.Builder("nobelium")
//                .iconSet(SHINY)
//                .element(Elements.No)
                .build();

        new Material.Builder("oganesson")
                .color(0x142D64)
//                .iconSet(METALLIC)
//                .element(Elements.Og)
                .build();

        new Material.Builder("osmium")
                .ingot() // miningLevel: 4
//                .fluid()
                .color(0x3232FF)
//                .iconSet(METALLIC)
//                .flags(EXT2_METAL, GENERATE_FOIL)
//                .element(Elements.Os)
//                .rotorStats(16.0f, 4.0f, 1280)
//                .cableProperties(GTValues.V[6], 4, 2)
//                .itemPipeProperties(256, 8.0f)
//                .blastTemp(4500, GasTier.HIGH, VA[LuV], 1000)
//                .fluidTemp(3306)
                .build();

        new Material.Builder("oxygen")
//                .fluid(FluidTypes.GAS).plasma()
                .color(0x4CC3FF)
//                .element(Elements.O)
                .build();

        new Material.Builder("palladium")
                .ingot()
//                .fluid().ore()
                .color(0x808080)
//                .iconSet(SHINY)
//                .flags(EXT_METAL, GENERATE_FOIL, GENERATE_FINE_WIRE)
//                .element(Elements.Pd)
//                .blastTemp(1828, GasTier.LOW, VA[HV], 900)
                .build();

        new Material.Builder("phosphorus")
//                .dust()
                .color(0xFFFF00)
//                .element(Elements.P)
                .build();

        new Material.Builder("polonium")
                .color(0xC9D47E)
//                .element(Elements.Po)
                .build();

        new Material.Builder("platinum")
                .ingot()
//                .fluid().ore()
                .color(0xFFFFC8)
//                .iconSet(SHINY)
//                .flags(EXT2_METAL, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_RING)
//                .element(Elements.Pt)
//                .cableProperties(GTValues.V[5], 2, 1)
//                .itemPipeProperties(512, 4.0f)
//                .fluidTemp(2041)
                .build();

        new Material.Builder("plutonium")
                .ingot() // miningLevel: 3
//                .fluid().ore(true)
                .color(0xF03232)
//                .iconSet(METALLIC)
//                .element(Elements.Pu239)
//                .fluidTemp(913)
                .build();

        new Material.Builder("plutonium_241")
                .ingot() // miningLevel: 3
//                .fluid()
                .color(0xFA4646)
//                .iconSet(SHINY)
//                .flags(EXT_METAL)
//                .element(Elements.Pu241)
//                .fluidTemp(913)
                .build();

        new Material.Builder("potassium")
//                .dust(1).fluid()
                .color(0xBEDCFF)
//                .iconSet(METALLIC)
//                .element(Elements.K)
//                .fluidTemp(337)
                .build();

        new Material.Builder("praseodymium")
                .color(0xCECECE)
//                .iconSet(METALLIC)
//                .element(Elements.Pr)
                .build();

        new Material.Builder("promethium")
//                .iconSet(METALLIC)
//                .element(Elements.Pm)
                .build();

        new Material.Builder("protactinium")
                .color(0xA78B6D)
//                .iconSet(METALLIC)
//                .element(Elements.Pa)
                .build();

        new Material.Builder("radon")
//                .fluid(FluidTypes.GAS)
                .color(0xFF39FF)
//                .element(Elements.Rn)
                .build();

        new Material.Builder("radium")
                .color(0xFFFFCD)
//                .iconSet(SHINY)
//                .element(Elements.Ra)
                .build();

        new Material.Builder("rhenium")
                .color(0xB6BAC3)
//                .iconSet(SHINY)
//                .element(Elements.Re)
                .build();

        new Material.Builder("rhodium")
                .ingot()
//                .fluid()
                .color(0xDC0C58)
//                .iconSet(BRIGHT)
//                .flags(EXT2_METAL, GENERATE_GEAR, GENERATE_FINE_WIRE)
//                .element(Elements.Rh)
//                .blastTemp(2237, GasTier.MID, VA[EV], 1200)
                .build();

        new Material.Builder("roentgenium")
                .color(0xE3FDEC)
//                .iconSet(SHINY)
//                .element(Elements.Rg)
                .build();

        new Material.Builder("rubidium")
                .color(0xF01E1E)
//                .iconSet(SHINY)
//                .element(Elements.Rb)
                .build();

        new Material.Builder("ruthenium")
                .ingot()
//                .fluid()
                .color(0x50ACCD)
//                .iconSet(SHINY)
//                .flags(GENERATE_FOIL, GENERATE_GEAR)
//                .element(Elements.Ru)
//                .blastTemp(2607, GasTier.MID, VA[EV], 900)
                .build();

        new Material.Builder("rutherfordium")
                .color(0xFFF6A1)
//                .iconSet(SHINY)
//                .element(Elements.Rf)
                .build();

        new Material.Builder("samarium")
                .ingot()
//                .fluid()
                .color(0xFFFFCC)
//                .iconSet(METALLIC)
//                .flags(GENERATE_LONG_ROD)
//                .element(Elements.Sm)
//                .blastTemp(5400, GasTier.HIGH, VA[EV], 1500)
//                .fluidTemp(1345)
                .build();

        new Material.Builder("scandium")
//                .iconSet(METALLIC)
//                .element(Elements.Sc)
                .build();

        new Material.Builder("seaborgium")
                .color(0x19C5FF)
//                .iconSet(SHINY)
//                .element(Elements.Sg)
                .build();

        new Material.Builder("selenium")
                .color(0xB6BA6B)
//                .iconSet(SHINY)
//                .element(Elements.Se)
                .build();

        new Material.Builder("silicon")
                .ingot()
//                .fluid()
                .color(0x3C3C50)
//                .iconSet(METALLIC)
//                .flags(GENERATE_FOIL)
//                .element(Elements.Si)
//                .blastTemp(1687) // no gas tier for silicon
                .build();

        new Material.Builder("silver")
                .ingot()
//                .fluid().ore()
                .color(0xDCDCFF)
//                .iconSet(SHINY)
//                .flags(EXT2_METAL, MORTAR_GRINDABLE, GENERATE_FINE_WIRE, GENERATE_RING)
//                .element(Elements.Ag)
//                .cableProperties(GTValues.V[3], 1, 1)
//                .fluidTemp(1235)
                .build();

        new Material.Builder("sodium")
//                .dust()
                .color(0x000096)
//                .iconSet(METALLIC)
//                .element(Elements.Na)
                .build();

        new Material.Builder("strontium")
                .color(0xC8C8C8)
//                .iconSet(METALLIC)
//                .element(Elements.Sr)
                .build();

        new Material.Builder("sulfur")
//                .dust().ore()
                .color(0xC8C800)
//                .flags(FLAMMABLE)
//                .element(Elements.S)
                .build();

        new Material.Builder("tantalum")
                .ingot()
//                .fluid()
                .color(0x78788c)
//                .iconSet(METALLIC)
//                .flags(STD_METAL, GENERATE_FOIL)
//                .element(Elements.Ta)
//                .fluidTemp(3290)
                .build();

        new Material.Builder("technetium")
                .color(0x545455)
//                .iconSet(SHINY)
//                .element(Elements.Tc)
                .build();

        new Material.Builder("tellurium")
//                .iconSet(METALLIC)
//                .element(Elements.Te)
                .build();

        new Material.Builder("tennessine")
                .color(0x977FD6)
//                .iconSet(SHINY)
//                .element(Elements.Ts)
                .build();

        new Material.Builder("terbium")
//                .iconSet(METALLIC)
//                .element(Elements.Tb)
                .build();

        new Material.Builder("thorium")
                .ingot()
//                .fluid().ore()
                .color(0x001E00)
//                .iconSet(SHINY)
//                .flags(STD_METAL, GENERATE_ROD)
//                .element(Elements.Th)
//                .fluidTemp(2023)
                .build();

        new Material.Builder("thallium")
                .color(0xC1C1DE)
//                .iconSet(SHINY)
//                .element(Elements.Tl)
                .build();

        new Material.Builder("thulium")
//                .iconSet(METALLIC)
//                .element(Elements.Tm)
                .build();

        new Material.Builder("tin")
                .ingot() // miningLevel: 1
//                .fluid(FluidTypes.LIQUID, true).ore()
                .color(0xDCDCDC)
//                .flags(EXT2_METAL, MORTAR_GRINDABLE, GENERATE_ROTOR, GENERATE_SPRING, GENERATE_SPRING_SMALL, GENERATE_FINE_WIRE)
//                .element(Elements.Sn)
//                .cableProperties(GTValues.V[1], 1, 1)
//                .itemPipeProperties(4096, 0.5f)
//                .fluidTemp(505)
                .build();

        new Material.Builder("titanium") // todo Ore? Look at EBF recipe here if we do Ti ores
                .ingot() // miningLevel: 3
//                .fluid()
                .color(0xDCA0F0)
//                .iconSet(METALLIC)
//                .flags(EXT2_METAL, GENERATE_ROTOR, GENERATE_SMALL_GEAR, GENERATE_GEAR, GENERATE_FRAME)
//                .element(Elements.Ti)
                .toolProperties(3, 1536, 8, 6)
//                .rotorStats(7.0f, 3.0f, 1600)
//                .fluidPipeProperties(2426, 150, true)
//                .blastTemp(1941, GasTier.MID, VA[HV], 1500)
                .build();

        new Material.Builder("tritium")
//                .fluid(FluidTypes.GAS)
//                .iconSet(METALLIC)
//                .element(Elements.T)
                .build();

        new Material.Builder("tungsten")
                .ingot() // miningLevel: 3
//                .fluid()
                .color(0x323232)
//                .iconSet(METALLIC)
//                .flags(EXT2_METAL, GENERATE_SPRING, GENERATE_SPRING_SMALL, GENERATE_FOIL, GENERATE_GEAR)
//                .element(Elements.W)
//                .rotorStats(7.0f, 3.0f, 2560)
//                .cableProperties(GTValues.V[5], 2, 2)
//                .fluidPipeProperties(4618, 50, true, true, false, true)
//                .blastTemp(3600, GasTier.MID, VA[EV], 1800)
//                .fluidTemp(3695)
                .build();

        new Material.Builder("uranium")
                .ingot() // miningLevel: 3
//                .fluid()
                .color(0x32F032)
//                .iconSet(METALLIC)
//                .flags(EXT_METAL)
//                .element(Elements.U238)
//                .fluidTemp(1405)
                .build();

        new Material.Builder("uranium_235")
                .ingot() // miningLevel: 3
//                .fluid()
                .color(0x46FA46)
//                .iconSet(SHINY)
//                .flags(EXT_METAL)
//                .element(Elements.U235)
//                .fluidTemp(1405)
                .build();

        new Material.Builder("vanadium")
                .ingot()
//                .fluid()
                .color(0x323232)
//                .iconSet(METALLIC)
//                .element(Elements.V)
//                .blastTemp(2183, GasTier.MID)
                .build();

        new Material.Builder("xenon")
//                .fluid(FluidTypes.GAS)
                .color(0x00FFFF)
//                .iconSet(GAS)
//                .element(Elements.Xe)
                .build();

        new Material.Builder("ytterbium")
                .color(0xA7A7A7)
//                .iconSet(METALLIC)
//                .element(Elements.Yb)
                .build();

        new Material.Builder("yttrium")
                .ingot()
//                .fluid()
                .color(0x76524C)
//                .iconSet(METALLIC)
//                .element(Elements.Y)
//                .blastTemp(1799)
                .build();

        new Material.Builder("zinc")
                .ingot() // miningLevel: 1
//                .fluid()
                .color(0xEBEBFA)
//                .iconSet(METALLIC)
//                .flags(STD_METAL, MORTAR_GRINDABLE, GENERATE_FOIL, GENERATE_RING, GENERATE_FINE_WIRE)
//                .element(Elements.Zn)
//                .fluidTemp(693)
                .build();

        new Material.Builder("zirconium")
                .color(0xC8FFFF)
//                .iconSet(METALLIC)
//                .element(Elements.Zr)
                .build();
    }
}
