package xueluoanping.dtnatures_spirit.data.datapack;

import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

@SuppressWarnings("removal")
public class NSPlacedFeatures {

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE,  ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID,name));
    }
    public static final ResourceKey<PlacedFeature> LARGE_REDWOOD_CHECKED = registerKey("large_redwood_checked");
    public static final ResourceKey<PlacedFeature> REDWOOD_CHECKED = registerKey("redwood_checked");
    public static final ResourceKey<PlacedFeature> LARGE_FROSTY_REDWOOD_CHECKED = registerKey("large_frosty_redwood_checked");
    public static final ResourceKey<PlacedFeature> FROSTY_REDWOOD_CHECKED = registerKey("frosty_redwood_checked");
    public static final ResourceKey<PlacedFeature> ASPEN_CHECKED = registerKey("aspen_checked");
    public static final ResourceKey<PlacedFeature> YELLOW_ASPEN_CHECKED = registerKey("yellow_aspen_checked");
    public static final ResourceKey<PlacedFeature> ASPEN_BEES_CHECKED = registerKey("aspen_bees_checked");
    public static final ResourceKey<PlacedFeature> YELLOW_ASPEN_BEES_CHECKED = registerKey("yellow_aspen_bees_checked");
    public static final ResourceKey<PlacedFeature> RED_MAPLE_CHECKED = registerKey("red_maple_checked");
    public static final ResourceKey<PlacedFeature> ORANGE_MAPLE_CHECKED = registerKey("orange_maple_checked");
    public static final ResourceKey<PlacedFeature> YELLOW_MAPLE_CHECKED = registerKey("yellow_maple_checked");
    public static final ResourceKey<PlacedFeature> CYPRESS_CHECKED = registerKey("cypress_checked");
    public static final ResourceKey<PlacedFeature> FIR_CHECKED = registerKey("fir_checked");
    public static final ResourceKey<PlacedFeature> LARCH_CHECKED = registerKey("larch_checked");
    public static final ResourceKey<PlacedFeature> WILLOW_CHECKED = registerKey("willow_checked");
    public static final ResourceKey<PlacedFeature> WILLOW_PLACED = registerKey("willow_placed");
    public static final ResourceKey<PlacedFeature> WHITE_WISTERIA_CHECKED = registerKey("white_wisteria_checked");
    public static final ResourceKey<PlacedFeature> BLUE_WISTERIA_CHECKED = registerKey("blue_wisteria_checked");
    public static final ResourceKey<PlacedFeature> PINK_WISTERIA_CHECKED = registerKey("pink_wisteria_checked");
    public static final ResourceKey<PlacedFeature> PURPLE_WISTERIA_CHECKED = registerKey("purple_wisteria_checked");
    public static final ResourceKey<PlacedFeature> SUGI_CHECKED = registerKey("sugi_checked");
    public static final ResourceKey<PlacedFeature> LARGE_SUGI_CHECKED = registerKey("large_sugi_checked");
    public static final ResourceKey<PlacedFeature> OLIVE_CHECKED = registerKey("olive_checked");
    public static final ResourceKey<PlacedFeature> GHAF_CHECKED = registerKey("ghaf_checked");
    public static final ResourceKey<PlacedFeature> PALO_VERDE_CHECKED = registerKey("palo_verde_checked");
    public static final ResourceKey<PlacedFeature> MAHOGANY_CHECKED = registerKey("mahogany_checked");
    public static final ResourceKey<PlacedFeature> SAXAUL_CHECKED = registerKey("saxaul_checked");
    //   public static final ResourceKey <PlacedFeature> BANYAN_CHECKED = registerKey("banyan_checked");
    public static final ResourceKey<PlacedFeature> COCONUT_CHECKED = registerKey("coconut_checked");
    public static final ResourceKey<PlacedFeature> CEDAR_CHECKED = registerKey("cedar_checked");
    public static final ResourceKey<PlacedFeature> JOSHUA_CHECKED = registerKey("joshua_checked");
    public static final ResourceKey<PlacedFeature> ALLUAUDIA_CHECKED = registerKey("alluaudia_checked");

    public static final ResourceKey<PlacedFeature> OAK_BUSH_CHECKED = registerKey("oak_bush_checked");
    public static final ResourceKey<PlacedFeature> SPRUCE_BUSH_CHECKED = registerKey("spruce_bush_checked");

    public static final ResourceKey<PlacedFeature> FLOWER_WISTERIA_PLACED = registerKey("flower_wisteria_placed");
    public static final ResourceKey<PlacedFeature> FLOWER_SUGI_PLACED = registerKey("flower_sugi_placed");
    public static final ResourceKey<PlacedFeature> FLOWER_RIVER_PLACED = registerKey("flower_river_placed");
    public static final ResourceKey<PlacedFeature> FLOWER_GOLDEN_PLACED = registerKey("flower_golden_placed");
    public static final ResourceKey<PlacedFeature> FLOWER_GOLDEN2_PLACED = registerKey("flower_golden2_placed");
    public static final ResourceKey<PlacedFeature> FLOWER_CYPRESS_PLACED = registerKey("flower_cypress_placed");
    public static final ResourceKey<PlacedFeature> FLOWER_CARNATION_PLACED = registerKey("flower_carnation_placed");
    public static final ResourceKey<PlacedFeature> PATCH_SCORCHED_GRASS_PLACED = registerKey("patch_scorched_grass_placed");
    public static final ResourceKey<PlacedFeature> PATCH_TALL_SCORCHED_GRASS_PLACED = registerKey("patch_tall_scorched_grass_placed");
    public static final ResourceKey<PlacedFeature> FLOWER_STRATIFIED_DESERT_PLACED = registerKey("flower_stratified_desert_placed");

    public static final ResourceKey<PlacedFeature> WISTERIA_WATER = registerKey("wisteria_water_placed");
    public static final ResourceKey<PlacedFeature> LAVENDER_WATER = registerKey("lavender_water_placed");
    public static final ResourceKey<PlacedFeature> SWAMP_WATER = registerKey("swamp_water_placed");
    public static final ResourceKey<PlacedFeature> MARSH_WATER = registerKey("marsh_water_placed");
    public static final ResourceKey<PlacedFeature> RIVER_WATER = registerKey("river_water_placed");

    public static final ResourceKey<PlacedFeature> LARGE_REDWOOD_PLACED = registerKey("large_redwood_placed");
    public static final ResourceKey<PlacedFeature> REDWOOD_PLACED = registerKey("redwood_placed");
    public static final ResourceKey<PlacedFeature> LARGE_FROSTY_REDWOOD_PLACED = registerKey("large_frosty_redwood_placed");
    public static final ResourceKey<PlacedFeature> FROSTY_REDWOOD_PLACED = registerKey("frosty_redwood_placed");
    public static final ResourceKey<PlacedFeature> ASPEN_PLACED = registerKey("aspen_placed");
    public static final ResourceKey<PlacedFeature> YELLOW_ASPEN_PLACED = registerKey("yellow_aspen_placed");
    public static final ResourceKey<PlacedFeature> MAPLE_PLACED = registerKey("maple_placed");
    public static final ResourceKey<PlacedFeature> MAPLE2_PLACED = registerKey("maple2_placed");
    public static final ResourceKey<PlacedFeature> FEW_ASPEN_PLACED = registerKey("few_aspen_placed");
    public static final ResourceKey<PlacedFeature> DENSE_CYPRESS_PLACED = registerKey("dense_cypress_placed");
    public static final ResourceKey<PlacedFeature> CYPRESS_PLACED = registerKey("cypress_placed");
    public static final ResourceKey<PlacedFeature> FIR_PLACED = registerKey("fir_placed");
    public static final ResourceKey<PlacedFeature> DENSE_FIR_PLACED = registerKey("dense_fir_placed");
    public static final ResourceKey<PlacedFeature> SPRUCE_BUSH_PLACED = registerKey("spruce_bush_placed");
    public static final ResourceKey<PlacedFeature> WISTERIA_PLACED = registerKey("wisteria_placed");
    public static final ResourceKey<PlacedFeature> SUGI_PLACED = registerKey("sugi_placed");
    public static final ResourceKey<PlacedFeature> OLIVE_PLACED = registerKey("olive_placed");
    public static final ResourceKey<PlacedFeature> GHAF_PLACED = registerKey("ghaf_placed");
    public static final ResourceKey<PlacedFeature> PALO_VERDE_PLACED = registerKey("palo_verde_placed");
    public static final ResourceKey<PlacedFeature> SAXAUL_PLACED = registerKey("saxaul_placed");
    public static final ResourceKey<PlacedFeature> SPARSE_SAXAUL_PLACED = registerKey("sparse_saxaul_placed");
    public static final ResourceKey<PlacedFeature> JOSHUA_PLACED = registerKey("joshua_placed");
    public static final ResourceKey<PlacedFeature> ALLUAUDIA_PLACED = registerKey("alluaudia_placed");
    public static final ResourceKey<PlacedFeature> COCONUT_PLACED = registerKey("coconut_placed");
    public static final ResourceKey<PlacedFeature> CEDAR_PLACED = registerKey("cedar_placed");
    public static final ResourceKey<PlacedFeature> CEDAR_BUSH_PLACED = registerKey("cedar_bush_placed");
    public static final ResourceKey<PlacedFeature> DENSE_CEDAR_PLACED = registerKey("dense_cedar_placed");
    public static final ResourceKey<PlacedFeature> MAHOGANY_PLACED = registerKey("mahogany_placed");
    public static final ResourceKey<PlacedFeature> SPARSE_OLIVE_PLACED = registerKey("sparse_olive_placed");

    public static final ResourceKey<PlacedFeature> OAK_BUSH_PLACED = registerKey("oak_bush_placed");
    public static final ResourceKey<PlacedFeature> CUSTOM_FANCY_OAK_TREE_PLACED = registerKey("custom_fancy_oak_tree_placed");
    public static final ResourceKey<PlacedFeature> CUSTOM_FANCY_OAK_TREE2_PLACED = registerKey("custom_fancy_oak_tree2_placed");
    public static final ResourceKey<PlacedFeature> CATTAILS = registerKey("cattails_placed");
    public static final ResourceKey<PlacedFeature> LOTUS_PLANT_PLACED = registerKey("lotus_plant_placed");
    public static final ResourceKey<PlacedFeature> ROOTED_DESERT_TURNIP = registerKey("rooted_desert_turnip_placed");
}
