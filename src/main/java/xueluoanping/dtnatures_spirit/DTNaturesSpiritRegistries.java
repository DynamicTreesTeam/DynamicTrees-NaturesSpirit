package xueluoanping.dtnatures_spirit;

import com.ferreusveritas.dynamictrees.api.registry.RegistryEvent;
import com.ferreusveritas.dynamictrees.api.registry.TypeRegistryEvent;
import com.ferreusveritas.dynamictrees.api.worldgen.FeatureCanceller;


import com.ferreusveritas.dynamictrees.block.leaves.LeavesProperties;
import com.ferreusveritas.dynamictrees.systems.fruit.Fruit;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xueluoanping.dtnatures_spirit.systems.featuregen.CherryFeatures;
import xueluoanping.dtnatures_spirit.systems.fruit.FruitTypes;
import xueluoanping.dtnatures_spirit.systems.fruit.NamedFruitTypes;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DTNaturesSpiritRegistries {

    @SubscribeEvent
    public static void registerLeavesPropertiesTypes(final TypeRegistryEvent<LeavesProperties> event) {
        DTNaturesSpirit.LOGGER.debug("registerLeavesPropertiesTypes");
        // event.registerType(new ResourceLocation(DTNaturesSpirit.MOD_ID, "cherry"), CherryLeavesProperties.TYPE);
        // event.registerType(new ResourceLocation(DTNaturesSpirit.MOD_ID, "fruittrees"), FruitsLeavesProperties.TYPE);
        // event.registerType(new ResourceLocation(DTNaturesSpirit.MOD_ID, "named_cherry_leaves"), NamedVanillaCherryLeaveProperties.TYPE);


    }

    @SubscribeEvent
    public static void registerFruitTypes(final TypeRegistryEvent<Fruit> event) {
        DTNaturesSpirit.LOGGER.debug("registerFruitTypes");
        event.registerType(new ResourceLocation(DTNaturesSpirit.MOD_ID, "fly_passable"), FruitTypes.TYPE);
        event.registerType(new ResourceLocation(DTNaturesSpirit.MOD_ID, "named_fruit"), NamedFruitTypes.TYPE);

    }


    @SubscribeEvent
    public static void onGenFeatureRegistry(final com.ferreusveritas.dynamictrees.api.registry.RegistryEvent<GenFeature> event) {
        CherryFeatures.register(event.getRegistry());
    }


}
