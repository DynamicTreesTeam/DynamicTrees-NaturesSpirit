package xueluoanping.dtnatures_spirit.systems;


import com.ferreusveritas.dynamictrees.api.registry.Registry;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;

import net.minecraft.resources.ResourceLocation;
import xueluoanping.dtnatures_spirit.DTNaturesSpirit;
import xueluoanping.dtnatures_spirit.systems.featuregen.FlowerVinesGenFeature;
import xueluoanping.dtnatures_spirit.systems.featuregen.PalmFruitGenFeature;


public class ModFeatures {
    public static final GenFeature FLOWER_VINES = new FlowerVinesGenFeature(regName("flower_vines"));
    public static final GenFeature PALM_FRUIT = new PalmFruitGenFeature(regName("palm_fruit"));

    private static ResourceLocation regName(String name) {
        return new ResourceLocation(DTNaturesSpirit.MOD_ID, name);
    }

    public static void register(final Registry<GenFeature> registry) {
        registry.register(FLOWER_VINES);
        registry.register(PALM_FRUIT);
    }
}
