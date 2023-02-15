package com.github.kydzombie.stapitest.material;

import com.github.kydzombie.stapitest.material.materials.*;
import net.minecraft.item.ItemBase;
import net.minecraft.item.tool.ToolMaterial;

import java.util.HashMap;

public class Materials {
   private static HashMap<String, Material> materials = new HashMap<>();
    public static void register() {

//    MarkerMaterials.register();

       ElementMaterials.register();


       new Material.Builder("missingMaterial")
               .toolProperties(0,0,0,0)
               .color(0).build();
       new Material.Builder("iron")
               .toolProperties(ToolMaterial.field_1690)
               .ingot(ItemBase.ironIngot)
               .color(-1).build();
       new Material.Builder("diamond")
               .toolProperties(ToolMaterial.field_1691)
               .ingot(ItemBase.diamond)
               .color(0x4AEDD9).build();
       new Material.Builder("gold")
               .toolProperties(ToolMaterial.field_1692)
               .ingot(ItemBase.goldIngot)
               .color(0xFFFF0B).build();
       new Material.Builder("redstone")
               .color(0xFF6D6D).build();
       new Material.Builder("chromium")
               .color(0xCFDBC5).build();
       new Material.Builder("steel")
               .toolProperties(3, 1561, 8.0F, 3)
               .color(0x3B3B3B)
               .ingot().build();
       new Material.Builder("stainlessSteel")
               .toolProperties( 3, 1561, 8.0F, 3)
               .color(0x898383)
               .ingot().build();

//    FirstDegreeMaterials.register();
//
//    OrganicChemistryMaterials.register();
//
//    UnknownCompositionMaterials.register();
//
//    SecondDegreeMaterials.register();
//
//    HigherDegreeMaterials.register();
//
//    MaterialFlagAddition.register();
//
//    CHEMICAL_DYES = new Material[]{
//            Materials.DyeWhite, Materials.DyeOrange,
//            Materials.DyeMagenta, Materials.DyeLightBlue,
//            Materials.DyeYellow, Materials.DyeLime,
//            Materials.DyePink, Materials.DyeGray,
//            Materials.DyeLightGray, Materials.DyeCyan,
//            Materials.DyePurple, Materials.DyeBlue,
//            Materials.DyeBrown, Materials.DyeGreen,
//            Materials.DyeRed, Materials.DyeBlack
//    };
}

   public static void put(String name, Material material) {
       materials.put(name, material);
   }
   public static Material get(String name) {
        return materials.getOrDefault(name, materials.get("missingMaterial"));
   }
   public static String[] allNames() {
       return materials.keySet().toArray(new String[0]);
   }
   public static Material[] allMaterials() {
       return materials.values().toArray(new Material[0]);
   }
   // all the latter is for example
    /**
     * Fantasy Elements
     */
    // public static Material Naquadah;
    // public static Material NaquadahEnriched;
    // public static Material Naquadria;
    // public static Material Neutronium;
    // public static Material Tritanium;
    // public static Material Duranium;
    // public static Material Trinium;

    /**
     * First Degree Compounds
     */
    // public static Material Almandine;
    // public static Material Andradite;
    // public static Material AnnealedCopper;
    // public static Material Asbestos;
    // public static Material Ash;
    // public static Material BandedIron;
    // public static Material BatteryAlloy;
    // public static Material BlueTopaz;
    // public static Material Bone;
    // public static Material Brass;
    // public static Material Bronze;
    // public static Material BrownLimonite;
    // public static Material Calcite;
    // public static Material Cassiterite;
    // public static Material CassiteriteSand;
    // public static Material Chalcopyrite;
    // public static Material Charcoal;
    // public static Material Chromite;
    // public static Material Cinnabar;
    // public static Material Water;
    // public static Material LiquidOxygen;
    // public static Material Coal;
    // public static Material Cobaltite;
    // public static Material Cooperite;
    // public static Material Cupronickel;
    // public static Material DarkAsh;
    // public static Material Diamond;
    // public static Material Electrum;
    // public static Material Emerald;
    // public static Material Galena;
    // public static Material Garnierite;
    // public static Material GreenSapphire;
    // public static Material Grossular;
    // public static Material Ice;
    // public static Material Ilmenite;
    // public static Material Rutile;
    // public static Material Bauxite;
    // public static Material Invar;
    // public static Material Kanthal;
    // public static Material Lazurite;
    // public static Material LiquidHelium;
    // public static Material Magnalium;
    // public static Material Magnesite;
    // public static Material Magnetite;
    // public static Material Molybdenite;
    // public static Material Nichrome;
    // public static Material NiobiumNitride;
    // public static Material NiobiumTitanium;
    // public static Material Obsidian;
    // public static Material Phosphate;
    // public static Material SterlingSilver;
    // public static Material RoseGold;
    // public static Material BlackBronze;
    // public static Material BismuthBronze;
    // public static Material Biotite;
    // public static Material Powellite;
    // public static Material Pyrite;
    // public static Material Pyrolusite;
    // public static Material Pyrope;
    // public static Material RockSalt;
    // public static Material Ruridit;
    // public static Material Rubber;
    // public static Material Ruby;
    // public static Material Salt;
    // public static Material Saltpeter;
    // public static Material Sapphire;
    // public static Material Scheelite;
    // public static Material Sodalite;
    // public static Material AluminiumSulfite;
    // public static Material Tantalite;
    // public static Material Coke;


    // public static Material SolderingAlloy;
    // public static Material Spessartine;
    // public static Material Sphalerite;
    // public static Material StainlessSteel;
    // public static Material Steel;
    // public static Material Stibnite;
    // public static Material Tetrahedrite;
    // public static Material TinAlloy;
    // public static Material Topaz;
    // public static Material Tungstate;
    // public static Material Ultimet;
    // public static Material Uraninite;
    // public static Material Uvarovite;
    // public static Material VanadiumGallium;
    // public static Material WroughtIron;
    // public static Material Wulfenite;
    // public static Material YellowLimonite;
    // public static Material YttriumBariumCuprate;
    // public static Material NetherQuartz;
    // public static Material CertusQuartz;
    // public static Material Quartzite;
    // public static Material Graphite;
    // public static Material Graphene;
    // public static Material TungsticAcid;
    // public static Material Osmiridium;
    // public static Material LithiumChloride;
    // public static Material CalciumChloride;
    // public static Material Bornite;
    // public static Material Chalcocite;

    // public static Material GalliumArsenide;
    // public static Material Potash;
    // public static Material SodaAsh;
    // public static Material IndiumGalliumPhosphide;
    // public static Material NickelZincFerrite;
    // public static Material SiliconDioxide;
    // public static Material MagnesiumChloride;
    // public static Material SodiumSulfide;
    // public static Material PhosphorusPentoxide;
    // public static Material Quicklime;
    // public static Material SodiumBisulfate;
    // public static Material FerriteMixture;
    // public static Material Magnesia;
    // public static Material PlatinumGroupSludge;
    // public static Material Realgar;
    // public static Material SodiumBicarbonate;
    // public static Material PotassiumDichromate;
    // public static Material ChromiumTrioxide;
    // public static Material AntimonyTrioxide;
    // public static Material Zincite;
    // public static Material CupricOxide;
    // public static Material CobaltOxide;
    // public static Material ArsenicTrioxide;
    // public static Material Massicot;
    // public static Material Ferrosilite;
    // public static Material MetalMixture;
    // public static Material SodiumHydroxide;
    // public static Material SodiumPersulfate;
    // public static Material Bastnasite;
    // public static Material Pentlandite;
    // public static Material Spodumene;
    // public static Material Lepidolite;
    // public static Material GlauconiteSand;
    // public static Material Malachite;
    // public static Material Mica;
    // public static Material Barite;
    // public static Material Alunite;
    // public static Material Talc;
    // public static Material Soapstone;
    // public static Material Kyanite;
    // public static Material IronMagnetic;
    // public static Material TungstenCarbide;
    // public static Material CarbonDioxide;
    // public static Material TitaniumTetrachloride;
    // public static Material NitrogenDioxide;
    // public static Material HydrogenSulfide;
    // public static Material NitricAcid;
    // public static Material SulfuricAcid;
    // public static Material PhosphoricAcid;
    // public static Material SulfurTrioxide;
    // public static Material SulfurDioxide;
    // public static Material CarbonMonoxide;
    // public static Material HypochlorousAcid;
    // public static Material Ammonia;
    // public static Material HydrofluoricAcid;
    // public static Material NitricOxide;
    // public static Material Iron3Chloride;
    // public static Material UraniumHexafluoride;
    // public static Material EnrichedUraniumHexafluoride;
    // public static Material DepletedUraniumHexafluoride;
    // public static Material NitrousOxide;
    // public static Material EnderPearl;
    // public static Material PotassiumFeldspar;
    // public static Material NeodymiumMagnetic;
    // public static Material HydrochloricAcid;
    // public static Material Steam;
    // public static Material DistilledWater;
    // public static Material SodiumPotassium;
    // public static Material SamariumMagnetic;
    // public static Material ManganesePhosphide;
    // public static Material MagnesiumDiboride;
    // public static Material MercuryBariumCalciumCuprate;
    // public static Material UraniumTriplatinum;
    // public static Material SamariumIronArsenicOxide;
    // public static Material IndiumTinBariumTitaniumCuprate;
    // public static Material UraniumRhodiumDinaquadide;
    // public static Material EnrichedNaquadahTriniumEuropiumDuranide;
    // public static Material RutheniumTriniumAmericiumNeutronate;
    // public static Material PlatinumRaw;
    // public static Material InertMetalMixture;
    // public static Material RhodiumSulfate;
    // public static Material RutheniumTetroxide;
    // public static Material OsmiumTetroxide;
    // public static Material IridiumChloride;
    // public static Material FluoroantimonicAcid;
    // public static Material TitaniumTrifluoride;
    // public static Material CalciumPhosphide;
    // public static Material IndiumPhosphide;
    // public static Material BariumSulfide;
    // public static Material TriniumSulfide;
    // public static Material ZincSulfide;
    // public static Material GalliumSulfide;
    // public static Material AntimonyTrifluoride;
    // public static Material EnrichedNaquadahSulfate;
    // public static Material NaquadriaSulfate;
    // public static Material Pyrochlore;

    /**
     * Organic chemistry
     */
    // public static Material SiliconeRubber;
    // public static Material RawRubber;
    // public static Material RawStyreneButadieneRubber;
    // public static Material StyreneButadieneRubber;
    // public static Material PolyvinylAcetate;
    // public static Material ReinforcedEpoxyResin;
    // public static Material PolyvinylChloride;
    // public static Material PolyphenyleneSulfide;
    // public static Material GlycerylTrinitrate;
    // public static Material Polybenzimidazole;
    // public static Material Polydimethylsiloxane;
    // public static Material Polyethylene;
    // public static Material Epoxy;
    // public static Material Polycaprolactam;
    // public static Material Polytetrafluoroethylene;
    // public static Material Sugar;
    // public static Material Methane;
    // public static Material Epichlorohydrin;
    // public static Material Monochloramine;
    // public static Material Chloroform;
    // public static Material Cumene;
    // public static Material Tetrafluoroethylene;
    // public static Material Chloromethane;
    // public static Material AllylChloride;
    // public static Material Isoprene;
    // public static Material Propane;
    // public static Material Propene;
    // public static Material Ethane;
    // public static Material Butene;
    // public static Material Butane;
    // public static Material DissolvedCalciumAcetate;
    // public static Material VinylAcetate;
    // public static Material MethylAcetate;
    // public static Material Ethenone;
    // public static Material Tetranitromethane;
    // public static Material Dimethylamine;
    // public static Material Dimethylhydrazine;
    // public static Material DinitrogenTetroxide;
    // public static Material Dimethyldichlorosilane;
    // public static Material Styrene;
    // public static Material Butadiene;
    // public static Material Dichlorobenzene;
    // public static Material AceticAcid;
    // public static Material Phenol;
    // public static Material BisphenolA;
    // public static Material VinylChloride;
    // public static Material Ethylene;
    // public static Material Benzene;
    // public static Material Acetone;
    // public static Material Glycerol;
    // public static Material Methanol;
    // public static Material Ethanol;
    // public static Material Toluene;
    // public static Material DiphenylIsophtalate;
    // public static Material PhthalicAcid;
    // public static Material Dimethylbenzene;
    // public static Material Diaminobenzidine;
    // public static Material Dichlorobenzidine;
    // public static Material Nitrochlorobenzene;
    // public static Material Chlorobenzene;
    // public static Material Octane;
    // public static Material EthylTertButylEther;
    // public static Material Ethylbenzene;
    // public static Material Naphthalene;
    // public static Material Nitrobenzene;
    // public static Material Cyclohexane;
    // public static Material NitrosylChloride;
    // public static Material CyclohexanoneOxime;
    // public static Material Caprolactam;
    // public static Material PlatinumSludgeResidue;
    // public static Material PalladiumRaw;
    // public static Material RarestMetalMixture;
    // public static Material AmmoniumChloride;
    // public static Material AcidicOsmiumSolution;
    // public static Material RhodiumPlatedPalladium;
    // public static Material Butyraldehyde;
    // public static Material PolyvinylButyral;

    /**
     * Not possible to determine exact Components
     */
    // public static Material WoodGas;
    // public static Material WoodVinegar;
    // public static Material WoodTar;
    // public static Material CharcoalByproducts;
    // public static Material Biomass;
    // public static Material BioDiesel;
    // public static Material FermentedBiomass;
    // public static Material Creosote;
    // public static Material Diesel;
    // public static Material RocketFuel;
    // public static Material Glue;
    // public static Material Lubricant;
    // public static Material McGuffium239;
    // public static Material IndiumConcentrate;
    // public static Material SeedOil;
    // public static Material DrillingFluid;
    // public static Material ConstructionFoam;

    // public static Material Oil;
    // public static Material OilHeavy;
    // public static Material RawOil;
    // public static Material OilLight;
    // public static Material NaturalGas;
    // public static Material SulfuricHeavyFuel;
    // public static Material HeavyFuel;
    // public static Material LightlyHydroCrackedHeavyFuel;
    // public static Material SeverelyHydroCrackedHeavyFuel;
    // public static Material LightlySteamCrackedHeavyFuel;
    // public static Material SeverelySteamCrackedHeavyFuel;
    // public static Material SulfuricLightFuel;
    // public static Material LightFuel;
    // public static Material LightlyHydroCrackedLightFuel;
    // public static Material SeverelyHydroCrackedLightFuel;
    // public static Material LightlySteamCrackedLightFuel;
    // public static Material SeverelySteamCrackedLightFuel;
    // public static Material SulfuricNaphtha;
    // public static Material Naphtha;
    // public static Material LightlyHydroCrackedNaphtha;
    // public static Material SeverelyHydroCrackedNaphtha;
    // public static Material LightlySteamCrackedNaphtha;
    // public static Material SeverelySteamCrackedNaphtha;
    // public static Material SulfuricGas;
    // public static Material RefineryGas;
    // public static Material LightlyHydroCrackedGas;
    // public static Material SeverelyHydroCrackedGas;
    // public static Material LightlySteamCrackedGas;
    // public static Material SeverelySteamCrackedGas;
    // public static Material HydroCrackedEthane;
    // public static Material HydroCrackedEthylene;
    // public static Material HydroCrackedPropene;
    // public static Material HydroCrackedPropane;
    // public static Material HydroCrackedButane;
    // public static Material HydroCrackedButene;
    // public static Material HydroCrackedButadiene;
    // public static Material SteamCrackedEthane;
    // public static Material SteamCrackedEthylene;
    // public static Material SteamCrackedPropene;
    // public static Material SteamCrackedPropane;
    // public static Material SteamCrackedButane;
    // public static Material SteamCrackedButene;
    // public static Material SteamCrackedButadiene;
    // public static Material LPG;

    // public static Material RawGrowthMedium;
    // public static Material SterileGrowthMedium;
    // public static Material Bacteria;
    // public static Material BacterialSludge;
    // public static Material EnrichedBacterialSludge;
    // public static Material Mutagen;
    // public static Material GelatinMixture;
    // public static Material RawGasoline;
    // public static Material Gasoline;
    // public static Material HighOctaneGasoline;
    // public static Material CoalGas;
    // public static Material CoalTar;
    // public static Material Gunpowder;
    // public static Material Oilsands;
    // public static Material RareEarth;
    // public static Material Stone;
    // public static Material Lava;
    // public static Material Glowstone;
    // public static Material NetherStar;
    // public static Material Endstone;
    // public static Material Netherrack;
    // public static Material CetaneBoostedDiesel;
    // public static Material Collagen;
    // public static Material Gelatin;
    // public static Material Agar;
    // public static Material Andesite;
    // public static Material Milk;
    // public static Material Cocoa;
    // public static Material Wheat;
    // public static Material Meat;
    // public static Material Wood;
    // public static Material TreatedWood;
    // public static Material Paper;
    // public static Material FishOil;
    // public static Material RubySlurry;
    // public static Material SapphireSlurry;
    // public static Material GreenSapphireSlurry;
    // public static Material DyeBlack;
    // public static Material DyeRed;
    // public static Material DyeGreen;
    // public static Material DyeBrown;
    // public static Material DyeBlue;
    // public static Material DyePurple;
    // public static Material DyeCyan;
    // public static Material DyeLightGray;
    // public static Material DyeGray;
    // public static Material DyePink;
    // public static Material DyeLime;
    // public static Material DyeYellow;
    // public static Material DyeLightBlue;
    // public static Material DyeMagenta;
    // public static Material DyeOrange;
    // public static Material DyeWhite;

    // public static Material ImpureEnrichedNaquadahSolution;
    // public static Material EnrichedNaquadahSolution;
    // public static Material AcidicEnrichedNaquadahSolution;
    // public static Material EnrichedNaquadahWaste;
    // public static Material ImpureNaquadriaSolution;
    // public static Material NaquadriaSolution;
    // public static Material AcidicNaquadriaSolution;
    // public static Material NaquadriaWaste;
    // public static Material Lapotron;
    // public static Material UUMatter;

    /**
     * Second Degree Compounds
     */
    // public static Material Glass;
    // public static Material Perlite;
    // public static Material Borax;
    // public static Material Olivine;
    // public static Material Opal;
    // public static Material Amethyst;
    // public static Material Lapis;
    // public static Material Blaze;
    // public static Material Apatite;
    // public static Material BlackSteel;
    // public static Material DamascusSteel;
    // public static Material TungstenSteel;
    // public static Material CobaltBrass;
    // public static Material TricalciumPhosphate;
    // public static Material GarnetRed;
    // public static Material GarnetYellow;
    // public static Material Marble;
    // public static Material GraniteBlack;
    // public static Material GraniteRed;
    // public static Material VanadiumMagnetite;
    // public static Material QuartzSand;
    // public static Material Pollucite;
    // public static Material Bentonite;
    // public static Material FullersEarth;
    // public static Material Pitchblende;
    // public static Material Monazite;
    // public static Material Mirabilite;
    // public static Material Trona;
    // public static Material Gypsum;
    // public static Material Zeolite;
    // public static Material Concrete;
    // public static Material SteelMagnetic;
    // public static Material VanadiumSteel;
    // public static Material Potin;
    // public static Material BorosilicateGlass;
    // public static Material NaquadahAlloy;
    // public static Material SulfuricNickelSolution;
    // public static Material SulfuricCopperSolution;
    // public static Material LeadZincSolution;
    // public static Material NitrationMixture;
    // public static Material DilutedSulfuricAcid;
    // public static Material DilutedHydrochloricAcid;
    // public static Material Flint;
    // public static Material Air;
    // public static Material LiquidAir;
    // public static Material NetherAir;
    // public static Material LiquidNetherAir;
    // public static Material EnderAir;
    // public static Material LiquidEnderAir;
    // public static Material AquaRegia;
    // public static Material SaltWater;
    // public static Material Clay;
    // public static Material Redstone;

    /**
     * Third Degree Materials
     */
    // public static Material Electrotine;
    // public static Material EnderEye;
    // public static Material Diatomite;
    // public static Material RedSteel;
    // public static Material BlueSteel;
    // public static Material Basalt;
    // public static Material GraniticMineralSand;
    // public static Material Redrock;
    // public static Material GarnetSand;
    // public static Material HSSG;
    // public static Material IridiumMetalResidue;
    // public static Material Granite;
    // public static Material Brick;
    // public static Material Fireclay;
    // public static Material Diorite;

    /**
     * Fourth Degree Materials
     */
    // public static Material RedAlloy;
    // public static Material BlueAlloy;
    // public static Material BasalticMineralSand;
    // public static Material HSSE;
    // public static Material HSSS;
}
