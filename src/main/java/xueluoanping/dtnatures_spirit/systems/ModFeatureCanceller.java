package xueluoanping.dtnatures_spirit.systems;

import com.ferreusveritas.dynamictrees.api.worldgen.BiomePropertySelectors;
import com.ferreusveritas.dynamictrees.api.worldgen.FeatureCanceller;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.RandomSelectorFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraftforge.registries.ForgeRegistries;
import xueluoanping.dtnatures_spirit.DTNaturesSpirit;

import java.util.ArrayList;
import java.util.List;

public class ModFeatureCanceller extends FeatureCanceller {
    public ModFeatureCanceller(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    public boolean shouldCancel(ConfiguredFeature<?, ?> configuredFeature, BiomePropertySelectors.NormalFeatureCancellation featureCancellations) {
        // Note it not in ForgeRegistries.FEATURES

        for (ConfiguredFeature<?, ?> feature : configuredFeature.getFeatures().toList()) {
            final ResourceLocation featureName = ForgeRegistries.FEATURES.getKey(feature.feature());
            // DTNaturesSpirit.logger(ForgeRegistries.FEATURES.getKey(configuredFeature.feature()));
            if (featureName == null) {
                return false;
            }
            // DTNaturesSpirit.logger(featureName,featureName.toString().equals("natures_spirit:joshua_tree_feature"));
            if (featureName.toString().contains("joshua")) return true;
        }
        return false;
        // && (WorldGenRegistries.CONFIGURED_FEATURE.getKey(configuredFeature) + "").startsWith("fruittrees");
    }


}
