package xueluoanping.dtnatures_spirit.data;


import com.dtteam.dynamictrees.treepack.Resources;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import xueluoanping.dtnatures_spirit.DTNaturesSpirit;
import xueluoanping.dtnatures_spirit.data.datapack.DatapackRegistryGenerator;
import xueluoanping.dtnatures_spirit.data.loot.DTNSLootTableProvider;
import xueluoanping.dtnatures_spirit.data.tag.DTNSBlockTagsProvider;
import xueluoanping.dtnatures_spirit.data.tag.DTNSItemTagsProvider;

import java.util.concurrent.CompletableFuture;


public class start {
    public final static String MODID = DTNaturesSpirit.MOD_ID;

    public static void dataGen(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        if (event.includeServer()) {
            DTNaturesSpirit.logger("Generate recipe");
            Resources.MANAGER.reload(Resources.MANAGER.prepareReload(null, null));
            String modId = DTNaturesSpirit.MOD_ID;

            DTNSBlockTagsProvider blockTags = new DTNSBlockTagsProvider(packOutput, lookupProvider,modId,helper);
            generator.addProvider(event.includeServer(), blockTags);
            generator.addProvider(event.includeServer(), new DTNSItemTagsProvider(packOutput, MODID, lookupProvider, blockTags.contentsGetter(), helper));
            //
            generator.addProvider(event.includeServer(), new DTNSLootTableProvider(packOutput, MODID, helper, lookupProvider));
            // generator.addProvider(new GLMProvider(generator, MODID));


            // GatherDataHelper.gatherLootData(modId, event);
            generator.addProvider(event.includeServer(), new DatapackRegistryGenerator(packOutput, lookupProvider));

        }
        if (event.includeClient()) {
            // generator.addProvider(event.includeServer(),new Lang_EN(packOutput, helper));
            // generator.addProvider(event.includeServer(),new Lang_ZH(packOutput, helper));
            // generator.addProvider(new BlockStatesDataProvider(generator, helper));
            // generator.addProvider(new ItemModelProvider(generator, helper));
        }


    }
}
