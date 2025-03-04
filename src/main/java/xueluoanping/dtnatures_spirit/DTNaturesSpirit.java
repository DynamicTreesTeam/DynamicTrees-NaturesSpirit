package xueluoanping.dtnatures_spirit;

import com.dtteam.dynamictrees.api.registry.RegistryHandler;
import com.dtteam.dynamictrees.treepack.Resources;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xueluoanping.dtnatures_spirit.data.start;

import java.util.List;
import java.util.Objects;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(DTNaturesSpirit.MOD_ID)
public class DTNaturesSpirit {
    public static final String MOD_ID = "dtnatures_spirit";
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public static final boolean useLogger = Objects.equals(System.getProperty("forgegradle.runs.dev"), "true");

    public DTNaturesSpirit(IEventBus modEventBus, ModContainer modContainer)  {


        modEventBus.addListener(this::gatherData);

        // Register ourselves for server and other game events we are interested in
        NeoForge.EVENT_BUS.register(this);
        // MinecraftForge.EVENT_BUS.register(TreeGrowHandler.instance);
        RegistryHandler.setup(MOD_ID);


        DTNaturesSpiritRegistries.SOUNDS.register(modEventBus);

    }




    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    // @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    // public static class RegistryEvents {
    //     @SubscribeEvent
    //     public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
    //         // register a new block here
    //         //            LOGGER.info("HELLO from Register Block");
    //     }
    // }

    public void gatherData(final GatherDataEvent event) {
        Resources.MANAGER.gatherData();

        // GatherDataHelper.gatherAllData(
        //         MOD_ID,
        //         event,
        //         SoilProperties.REGISTRY,
        //         Family.REGISTRY,
        //         Species.REGISTRY,
        //         LeavesProperties.REGISTRY
        // );

        start.dataGen(event);
    }

    public static void logger(Object... x) {

        // if (General.bool.get())
        if (useLogger) {
            StringBuilder output = new StringBuilder();

            for (Object i : x) {
                if (i == null) output.append(", ").append("null");
                else if (i.getClass().isArray()) {
                    output.append(", [");
                    for (Object c : (int[]) i) {
                        output.append(c).append(",");
                    }
                    output.append("]");
                } else if (i instanceof List) {
                    output.append(", [");
                    for (Object c : (List) i) {
                        output.append(c);
                    }
                    output.append("]");
                } else
                    output.append(", ").append(i);
            }
            LOGGER.info(output.substring(1));
        }

    }

    public static ResourceLocation rl(String name) {
        return  ResourceLocation.fromNamespaceAndPath(DTNaturesSpirit.MOD_ID, name);
    }
}
