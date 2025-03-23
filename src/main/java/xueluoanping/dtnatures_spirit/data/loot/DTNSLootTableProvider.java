package xueluoanping.dtnatures_spirit.data.loot;

import com.dtteam.dynamictrees.data.provider.DTBlockLootSubProvider;
import com.dtteam.dynamictrees.data.provider.DTLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public class DTNSLootTableProvider extends DTLootTableProvider {

    private final String modId;
    private final PackOutput generator;
    private final List<SubProviderEntry> subProviderEntries;
    private final ExistingFileHelper fileHelper;

    public DTNSLootTableProvider(PackOutput output, String modId, ExistingFileHelper fileHelper, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, modId, fileHelper, registries);
        this.generator = output;
        this.subProviderEntries = List.of(new SubProviderEntry(
                BlockLoot2::new,
                LootContextParamSets.BLOCK
        ));
        this.modId = modId;
        this.fileHelper = fileHelper;
    }

    @Override
    public List<SubProviderEntry> getTables() {
        return subProviderEntries;
    }

    public class BlockLoot2 extends DTBlockLootSubProvider {

        protected BlockLoot2(HolderLookup.Provider registries) {
            super(registries, modId, fileHelper);
        }
    }
}
