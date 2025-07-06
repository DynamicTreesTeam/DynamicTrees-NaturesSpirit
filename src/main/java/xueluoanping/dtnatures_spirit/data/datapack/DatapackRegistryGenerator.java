package xueluoanping.dtnatures_spirit.data.datapack;



import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import xueluoanping.dtnatures_spirit.DTNaturesSpirit;
import xueluoanping.dtnatures_spirit.registry.ModBiomeModifiers;


import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class DatapackRegistryGenerator extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder REGISTRY_SET_BUILDER = new RegistrySetBuilder()
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap)
            ;

    public DatapackRegistryGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, REGISTRY_SET_BUILDER, Set.of(DTNaturesSpirit.MOD_ID));
    }

}