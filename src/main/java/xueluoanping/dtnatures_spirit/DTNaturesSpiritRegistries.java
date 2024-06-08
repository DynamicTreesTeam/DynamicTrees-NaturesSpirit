package xueluoanping.dtnatures_spirit;

import com.ferreusveritas.dynamictrees.api.cell.CellKit;
import com.ferreusveritas.dynamictrees.api.registry.RegistryEvent;
import com.ferreusveritas.dynamictrees.api.registry.TypeRegistryEvent;
import com.ferreusveritas.dynamictrees.api.worldgen.FeatureCanceller;
import com.ferreusveritas.dynamictrees.block.leaves.LeavesProperties;
import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKit;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
import com.ferreusveritas.dynamictrees.systems.pod.Pod;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xueluoanping.dtnatures_spirit.systems.ModCellKit;
import xueluoanping.dtnatures_spirit.systems.ModFeatureCanceller;
import xueluoanping.dtnatures_spirit.systems.ModFeatures;
import xueluoanping.dtnatures_spirit.systems.ModGrowthLogicKits;
import xueluoanping.dtnatures_spirit.systems.pods.FallingPalmPod;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DTNaturesSpiritRegistries {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DTNaturesSpirit.MOD_ID);

    public static final RegistryObject<SoundEvent> FRUIT_BONK = registerSound("falling_fruit.bonk");

    public static RegistryObject<SoundEvent> registerSound (String name){
        return SOUNDS.register(name, ()-> SoundEvent.createVariableRangeEvent(DTNaturesSpirit.rl(name)));
    }

    @SubscribeEvent
    public static void registerLeavesPropertiesTypes(final TypeRegistryEvent<LeavesProperties> event) {
        DTNaturesSpirit.LOGGER.debug("registerLeavesPropertiesTypes");
        // event.registerType(new ResourceLocation(DTNaturesSpirit.MOD_ID, "cherry"), CherryLeavesProperties.TYPE);
        // event.registerType(new ResourceLocation(DTNaturesSpirit.MOD_ID, "fruittrees"), FruitsLeavesProperties.TYPE);
        // event.registerType(new ResourceLocation(DTNaturesSpirit.MOD_ID, "named_cherry_leaves"), NamedVanillaCherryLeaveProperties.TYPE);

    }


    @SubscribeEvent
    public static void onFeatureCancellerRegistry(final RegistryEvent<FeatureCanceller> event) {
        event.getRegistry().register(new ModFeatureCanceller(DTNaturesSpirit.rl( "joshua_tree_feature")));
    }
    @SubscribeEvent
    public static void registerPodTypes(final TypeRegistryEvent<Pod> event) {
        // DTNaturesSpirit.LOGGER.debug("registerFruitTypes");
        event.registerType(DTNaturesSpirit.rl( "falling_palm"), FallingPalmPod.TYPE);
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

    @SubscribeEvent
    public static void onCellKitsRegistry(final com.ferreusveritas.dynamictrees.api.registry.RegistryEvent<CellKit> event) {
        ModCellKit.register(event.getRegistry());
    }

}
