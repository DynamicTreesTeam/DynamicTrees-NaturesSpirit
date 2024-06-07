package xueluoanping.dtnatures_spirit;

import com.ferreusveritas.dynamictrees.api.registry.TypeRegistryEvent;
import com.ferreusveritas.dynamictrees.block.leaves.LeavesProperties;
import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKit;
import com.ferreusveritas.dynamictrees.systems.fruit.Fruit;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xueluoanping.dtnatures_spirit.systems.ModFeatures;
import xueluoanping.dtnatures_spirit.systems.ModGrowthLogicKits;


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
        // DTNaturesSpirit.LOGGER.debug("registerFruitTypes");
        // event.registerType(new ResourceLocation(DTNaturesSpirit.MOD_ID, "fly_passable"), FruitTypes.TYPE);
        // event.registerType(new ResourceLocation(DTNaturesSpirit.MOD_ID, "named_fruit"), NamedFruitTypes.TYPE);
    }


    @SubscribeEvent
    public static void onGenFeatureRegistry(final com.ferreusveritas.dynamictrees.api.registry.RegistryEvent<GenFeature> event) {
        ModFeatures.register(event.getRegistry());
    }

    @SubscribeEvent
    public static void onGrowthLogicKitsRegistry(final com.ferreusveritas.dynamictrees.api.registry.RegistryEvent<GrowthLogicKit> event) {
        ModGrowthLogicKits.register(event.getRegistry());
    }

}
