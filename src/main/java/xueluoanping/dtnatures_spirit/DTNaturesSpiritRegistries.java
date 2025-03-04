package xueluoanping.dtnatures_spirit;


import com.dtteam.dynamictrees.api.cell.CellKit;
import com.dtteam.dynamictrees.api.worldgen.FeatureCanceller;
import com.dtteam.dynamictrees.block.leaves.LeavesProperties;
import com.dtteam.dynamictrees.block.pod.Pod;
import com.dtteam.dynamictrees.event.RegistryEvent;
import com.dtteam.dynamictrees.event.TypeRegistryEvent;
import com.dtteam.dynamictrees.systems.genfeature.GenFeature;
import com.dtteam.dynamictrees.systems.growthlogic.GrowthLogicKit;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xueluoanping.dtnatures_spirit.systems.ModCellKit;
import xueluoanping.dtnatures_spirit.systems.ModFeatureCanceller;
import xueluoanping.dtnatures_spirit.systems.ModFeatures;
import xueluoanping.dtnatures_spirit.systems.ModGrowthLogicKits;
import xueluoanping.dtnatures_spirit.systems.pods.FallingPalmPod;


@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class DTNaturesSpiritRegistries {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, DTNaturesSpirit.MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> FRUIT_BONK = registerSound("falling_fruit.bonk");

    public static DeferredHolder<SoundEvent, SoundEvent> registerSound (String name){
        return SOUNDS.register(name, ()-> SoundEvent.createVariableRangeEvent(DTNaturesSpirit.rl(name)));
    }

    @SubscribeEvent
    public static void registerLeavesPropertiesTypes(final TypeRegistryEvent<LeavesProperties> event) {

    }

    @SubscribeEvent
    public static void onFeatureCancellerRegistry(final RegistryEvent<FeatureCanceller> event) {
        if (event.isEntryOfType(FeatureCanceller.class))
        event.getRegistry().register(new ModFeatureCanceller(DTNaturesSpirit.rl( "joshua_tree_feature")));
    }
    @SubscribeEvent
    public static void registerPodTypes(final TypeRegistryEvent<Pod> event) {
        if (event.isEntryOfType(Pod.class))
            event.registerType(DTNaturesSpirit.rl( "falling_palm"), FallingPalmPod.TYPE);
    }


    @SubscribeEvent
    public static void onGenFeatureRegistry(final RegistryEvent<GenFeature> event) {
        if (event.isEntryOfType(GenFeature.class))

            ModFeatures.register(event.getRegistry());
    }

    @SubscribeEvent
    public static void onGrowthLogicKitsRegistry(final RegistryEvent<GrowthLogicKit> event) {
        if (event.isEntryOfType(GrowthLogicKit.class))

            ModGrowthLogicKits.register(event.getRegistry());
    }

    @SubscribeEvent
    public static void onCellKitsRegistry(final RegistryEvent<CellKit> event) {
        if (event.isEntryOfType(CellKit.class))
            ModCellKit.register(event.getRegistry());
    }

}
