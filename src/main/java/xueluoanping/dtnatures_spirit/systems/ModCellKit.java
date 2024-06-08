package xueluoanping.dtnatures_spirit.systems;


import com.ferreusveritas.dynamictrees.api.cell.CellKit;
import com.ferreusveritas.dynamictrees.api.registry.Registry;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
import net.minecraft.resources.ResourceLocation;
import xueluoanping.dtnatures_spirit.DTNaturesSpirit;
import xueluoanping.dtnatures_spirit.systems.cell.SingleCellKit;


public class ModCellKit {
    public static final SingleCellKit SINGLE_CELL_KIT = new SingleCellKit(regName("single"));

    private static ResourceLocation regName(String name) {
        return new ResourceLocation(DTNaturesSpirit.MOD_ID, name);
    }

    public static void register(final Registry<CellKit> registry) {
        registry.register(SINGLE_CELL_KIT);
    }
}
