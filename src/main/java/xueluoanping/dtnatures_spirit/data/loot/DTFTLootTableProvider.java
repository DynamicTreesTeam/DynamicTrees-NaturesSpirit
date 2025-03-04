package xueluoanping.dtnatures_spirit.data.loot;



import com.dtteam.dynamictrees.data.provider.DTLootTableProvider;
import com.dtteam.dynamictrees.treepack.Resources;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;


//  I inherited DTLootTableProvider, but many of its functions are private,
//  so I have to copy them to facilitate modification.

public class DTFTLootTableProvider extends DTLootTableProvider {

    private final PackOutput generator;
    private final String modId;
    private final ExistingFileHelper existingFileHelper;

    public DTFTLootTableProvider(PackOutput output, String modId, ExistingFileHelper fileHelper, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, modId, fileHelper, registries);
        this.generator = output;
        this.modId = modId;
        this.existingFileHelper = fileHelper;
    }

    // The reason why these functions appear is that
    // the loot table of the leaves block needs to be overwritten.

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {

        Resources.MANAGER.reload(Resources.MANAGER.prepareReload(null, null));
        // Resources.MANAGER.gatherData();
        // Resources.MANAGER.setup();
        // First generate the default


        // Now overwrite and generate the parts that need to be customized.
        // Not need any more
        // addTables();
        // writeTables(cache);
        return super.run(cache);
    }

}
