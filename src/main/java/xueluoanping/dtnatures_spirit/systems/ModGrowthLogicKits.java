package xueluoanping.dtnatures_spirit.systems;

import com.dtteam.dynamictrees.api.registry.Registry;
import com.dtteam.dynamictrees.systems.growthlogic.GrowthLogicKit;
import xueluoanping.dtnatures_spirit.DTNaturesSpirit;
import xueluoanping.dtnatures_spirit.systems.growthlogic.*;


public class ModGrowthLogicKits {
    public static final GrowthLogicKit CROOKED_PALM = new CrookedPalmLogic(DTNaturesSpirit.rl("crooked_palm"));
    public static final GrowthLogicKit REDWOOD = new RedwoodLogic(DTNaturesSpirit.rl("redwood"));
    public static final GrowthLogicKit WISTERIA = new WisteriaTreeLogic(DTNaturesSpirit.rl("wisteria"));
    public static final GrowthLogicKit BIFURCATED = new BifurcatedTreeLogic(DTNaturesSpirit.rl("bifurcated"));
    public static final GrowthLogicKit LARGE_CROWN = new LargeCrownTreeLogic(DTNaturesSpirit.rl("large_crown"));

    public static void register(final Registry<GrowthLogicKit> registry) {
        registry.registerAll(CROOKED_PALM, REDWOOD, WISTERIA, BIFURCATED);
        registry.register(LARGE_CROWN);
    }
}
