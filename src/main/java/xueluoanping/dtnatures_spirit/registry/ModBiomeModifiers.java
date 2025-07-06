package xueluoanping.dtnatures_spirit.registry;

import net.hibiscus.naturespirit.registration.NSBiomes;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import xueluoanping.dtnatures_spirit.DTNaturesSpirit;
import xueluoanping.dtnatures_spirit.data.datapack.NSPlacedFeatures;

import java.util.Set;

public class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> REMOVE_CEDAR = createKey("remove_cedar");
    public static final ResourceKey<BiomeModifier> REMOVE_JOSHUA = createKey("remove_joshua");
    private static ResourceKey<BiomeModifier> createKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, DTNaturesSpirit.rl(name));
    }

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        HolderGetter<Biome> holderGetter = context.lookup(Registries.BIOME);
        HolderGetter<PlacedFeature> placedFeatureHolderGetter = context.lookup(Registries.PLACED_FEATURE);
        context.register(REMOVE_CEDAR, new ForgeBiomeModifiers.RemoveFeaturesBiomeModifier(
                HolderSet.direct(holderGetter.getOrThrow(NSBiomes.CEDAR_THICKET),
                        holderGetter.getOrThrow(NSBiomes.XERIC_PLAINS)),
                HolderSet.direct(
                        placedFeatureHolderGetter.getOrThrow(NSPlacedFeatures.DENSE_CEDAR_PLACED),
                        placedFeatureHolderGetter.getOrThrow(NSPlacedFeatures.CEDAR_PLACED),
                        placedFeatureHolderGetter.getOrThrow(NSPlacedFeatures.CEDAR_BUSH_PLACED),
                        placedFeatureHolderGetter.getOrThrow(NSPlacedFeatures.OLIVE_PLACED),
                        placedFeatureHolderGetter.getOrThrow(NSPlacedFeatures.SPARSE_OLIVE_PLACED)),
                Set.of(GenerationStep.Decoration.values())));

        context.register(REMOVE_JOSHUA, new ForgeBiomeModifiers.RemoveFeaturesBiomeModifier(
                HolderSet.direct(holderGetter.getOrThrow(NSBiomes.BLOOMING_HIGHLANDS),
                        holderGetter.getOrThrow(NSBiomes.BLOOMING_DUNES),
                        holderGetter.getOrThrow(NSBiomes.LIVELY_DUNES),
                        holderGetter.getOrThrow(NSBiomes.STRATIFIED_DESERT)),
                HolderSet.direct(
                        placedFeatureHolderGetter.getOrThrow(NSPlacedFeatures.JOSHUA_PLACED)),
                Set.of(GenerationStep.Decoration.values())));
    }
}
