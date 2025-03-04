package xueluoanping.dtnatures_spirit.data.tag;

import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.tree.species.Species;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class DTNSItemTagsProvider extends ItemTagsProvider {
    public DTNSItemTagsProvider(PackOutput output, String modId, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                CompletableFuture<TagsProvider.TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.addDTTags();
    }



    protected void addDTTags() {
        Family.REGISTRY.dataGenerationStream(this.modId).forEach(leavesProperties ->
        {
            leavesProperties.defaultBranchItemTags().forEach(blockTagKey -> tag(blockTagKey).add(leavesProperties.getBranchItem().get()));
        });

        Species.REGISTRY.dataGenerationStream(this.modId).forEach(leavesProperties ->
                leavesProperties.defaultSeedTags().forEach(blockTagKey ->  tag(blockTagKey).addOptional(leavesProperties.getSeedName())));


    }

    @Override
    public String getName() {
        return modId + " DT Item Tags";
    }

}