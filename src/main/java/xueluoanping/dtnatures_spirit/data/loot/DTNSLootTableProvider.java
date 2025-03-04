package xueluoanping.dtnatures_spirit.data.loot;

import com.dtteam.dynamictrees.data.provider.DTLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public class DTNSLootTableProvider extends DTLootTableProvider {

    private final PackOutput generator;
    private final List<SubProviderEntry> subProviderEntries;

    public DTNSLootTableProvider(PackOutput output, String modId, ExistingFileHelper fileHelper, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, modId, fileHelper, registries);
        this.generator = output;
        this.subProviderEntries = List.of(new SubProviderEntry(
                BlockLoot2::new,
                LootContextParamSets.BLOCK
        ));

    }

    @Override
    public List<SubProviderEntry> getTables() {
        return subProviderEntries;
    }

    public class BlockLoot2 extends DTLootTableProvider.BlockLoot{

        protected BlockLoot2(HolderLookup.Provider registries) {
            super(registries);
        }
    }
}
