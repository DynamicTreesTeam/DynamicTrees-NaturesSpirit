package xueluoanping.dtnatures_spirit.data.tag;

import com.dtteam.dynamictrees.block.leaves.LeavesProperties;
import com.dtteam.dynamictrees.block.soil.SoilProperties;
import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.tree.species.Species;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class DTNSBlockTagsProvider extends BlockTagsProvider {
    public DTNSBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.addDTTags();
    }

    protected void addDTTags() {
        LeavesProperties.REGISTRY.dataGenerationStream(this.modId).forEach(leavesProperties ->
                leavesProperties.defaultLeavesTags().forEach(blockTagKey ->  tag(blockTagKey).addOptional(leavesProperties.getBlockRegistryName())));

        Family.REGISTRY.dataGenerationStream(this.modId).forEach(family ->
        {
            family.defaultBranchTags().forEach(blockTagKey -> tag(blockTagKey).add(family.getBranch().get()));
            family.defaultStrippedBranchTags().forEach(blockTagKey -> tag(blockTagKey).add(family.getStrippedBranch().get()));
        });

        Species.REGISTRY.dataGenerationStream(this.modId).forEach(species ->
                species.defaultSaplingTags().forEach(blockTagKey ->  tag(blockTagKey).addOptional(species.getSaplingRegName())));

        SoilProperties.REGISTRY.dataGenerationStream(this.modId).forEach(soilProperties ->
                soilProperties.defaultSoilBlockTags().forEach(blockTagKey ->  tag(blockTagKey).addOptional(soilProperties.getBlockRegistryName())));


    }

    @Override
    public String getName() {
        return modId + " DT Block Tags";
    }
}
