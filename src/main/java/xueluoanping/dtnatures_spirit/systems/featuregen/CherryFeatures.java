package xueluoanping.dtnatures_spirit.systems.featuregen;


import com.ferreusveritas.dynamictrees.api.registry.Registry;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
import net.minecraft.resources.ResourceLocation;
import xueluoanping.dtnatures_spirit.DTNaturesSpirit;


public class CherryFeatures {
    public static final GenFeature FALLEN_LEAVES = new FeatureGenFallenLeaves(regName("fallen_leaves"));
    private static ResourceLocation regName(String name) {
        return new ResourceLocation(DTNaturesSpirit.MOD_ID, name);
    }

    public static void register(final Registry<GenFeature> registry) {
        registry.registerAll(FALLEN_LEAVES);
    }
}
