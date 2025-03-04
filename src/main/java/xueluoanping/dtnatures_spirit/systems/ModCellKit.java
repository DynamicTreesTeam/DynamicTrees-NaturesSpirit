package xueluoanping.dtnatures_spirit.systems;


import com.dtteam.dynamictrees.api.cell.CellKit;
import com.dtteam.dynamictrees.api.registry.Registry;
import net.minecraft.resources.ResourceLocation;
import xueluoanping.dtnatures_spirit.DTNaturesSpirit;
import xueluoanping.dtnatures_spirit.systems.cell.CoconutCellKit;
import xueluoanping.dtnatures_spirit.systems.cell.SingleCellKit;


public class ModCellKit {
    public static final SingleCellKit SINGLE_CELL_KIT = new SingleCellKit(regName("single"));
    public static final CoconutCellKit COCONUT_CELL_KIT = new CoconutCellKit(regName("coconut"));


    private static ResourceLocation regName(String name) {
        return DTNaturesSpirit.rl(name);
    }

    public static void register(final Registry<CellKit> registry) {
        registry.register(SINGLE_CELL_KIT);
        registry.register(COCONUT_CELL_KIT);
    }
}
