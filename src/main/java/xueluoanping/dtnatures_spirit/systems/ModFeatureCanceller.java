package xueluoanping.dtnatures_spirit.systems;


import com.dtteam.dynamictrees.api.worldgen.BiomePropertySelectors;
import com.dtteam.dynamictrees.api.worldgen.FeatureCanceller;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import xueluoanping.dtnatures_spirit.DTNaturesSpirit;

public class ModFeatureCanceller extends FeatureCanceller {
    public ModFeatureCanceller(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    public boolean shouldCancel(ConfiguredFeature<?, ?> configuredFeature, BiomePropertySelectors.NormalFeatureCancellation featureCancellations) {
        // Note it not in ForgeRegistries.FEATURES
        final ResourceLocation featureName = BuiltInRegistries.FEATURE.getKey(configuredFeature.feature());
        // DTNaturesSpirit.logger(ForgeRegistries.FEATURES.getKey(configuredFeature.feature()));
        if (featureName == null) {
            return false;
        }
        // DTNaturesSpirit.logger(featureName,featureName.toString().equals("natures_spirit:joshua_tree_feature"));
        return featureName.toString().equals("natures_spirit:joshua_tree_feature");
        // && (WorldGenRegistries.CONFIGURED_FEATURE.getKey(configuredFeature) + "").startsWith("fruittrees");
    }


}
