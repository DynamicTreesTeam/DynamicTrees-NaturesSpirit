package xueluoanping.dtnatures_spirit.systems;

import com.ferreusveritas.dynamictrees.api.registry.Registry;
import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKit;
import net.minecraft.resources.ResourceLocation;
import xueluoanping.dtnatures_spirit.DTNaturesSpirit;
import xueluoanping.dtnatures_spirit.systems.growthlogic.*;


public class ModGrowthLogicKits {
    public static final GrowthLogicKit CROOKED_PALM = new CrookedPalmLogic(new ResourceLocation(DTNaturesSpirit.MOD_ID, "crooked_palm"));
    public static final GrowthLogicKit HEMLOCK = new RedwoodLogic(new ResourceLocation(DTNaturesSpirit.MOD_ID, "hemlock"));
    public static final GrowthLogicKit WISTERIA = new WisteriaTreeLogic(new ResourceLocation(DTNaturesSpirit.MOD_ID, "wisteria"));
    public static final GrowthLogicKit BIFURCATED = new BifurcatedTreeLogic(new ResourceLocation(DTNaturesSpirit.MOD_ID, "bifurcated"));
    public static final GrowthLogicKit LARGE_CROWN = new LargeCrownTreeLogic(new ResourceLocation(DTNaturesSpirit.MOD_ID, "large_crown"));

    public static void register(final Registry<GrowthLogicKit> registry) {
        registry.registerAll(CROOKED_PALM, HEMLOCK, WISTERIA, BIFURCATED);
        registry.register(LARGE_CROWN);
    }
}
