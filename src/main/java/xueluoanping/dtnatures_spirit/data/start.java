package xueluoanping.dtnatures_spirit.data;

import com.ferreusveritas.dynamictrees.data.provider.DTBlockTagsProvider;
import com.ferreusveritas.dynamictrees.data.provider.DTItemTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import xueluoanping.dtnatures_spirit.DTNaturesSpirit;
import xueluoanping.dtnatures_spirit.data.datapack.DatapackRegistryGenerator;
import xueluoanping.dtnatures_spirit.data.lang.Lang_EN;
import xueluoanping.dtnatures_spirit.data.lang.Lang_ZH;
import xueluoanping.dtnatures_spirit.data.loot.DTFTLootTableProvider;

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

            // work it until 1.21
            // generator.addProvider(event.includeServer(),new RecipeDataProvider(packOutput));

            DTBlockTagsProvider blockTags = new DTBlockTagsProvider(packOutput,lookupProvider, MODID, helper);
            generator.addProvider(event.includeServer(),blockTags);
            generator.addProvider(event.includeServer(),new DTItemTagsProvider(packOutput, MODID, lookupProvider, blockTags.contentsGetter(), helper));

            generator.addProvider(event.includeServer(),new DTFTLootTableProvider(packOutput,MODID,helper));

            generator.addProvider(event.includeServer(),new DatapackRegistryGenerator(packOutput, lookupProvider));



            // generator.addProvider(new SimpleMP(generator));

        }
        if (event.includeClient()) {
            // generator.addProvider(event.includeServer(),new Lang_EN(packOutput, helper));
            // generator.addProvider(event.includeServer(),new Lang_ZH(packOutput, helper));
            // generator.addProvider(new BlockStatesDataProvider(generator, helper));
            // generator.addProvider(new ItemModelProvider(generator, helper));
        }


    }
}
