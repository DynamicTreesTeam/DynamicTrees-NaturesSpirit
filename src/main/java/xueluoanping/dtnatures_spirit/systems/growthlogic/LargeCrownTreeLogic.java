package xueluoanping.dtnatures_spirit.systems.growthlogic;

import com.ferreusveritas.dynamictrees.api.configuration.ConfigurationProperty;
import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKitConfiguration;
import com.ferreusveritas.dynamictrees.growthlogic.PalmGrowthLogic;
import com.ferreusveritas.dynamictrees.growthlogic.context.DirectionManipulationContext;
import com.ferreusveritas.dynamictrees.systems.GrowSignal;
import com.ferreusveritas.dynamictrees.tree.species.Species;
import com.ferreusveritas.dynamictrees.util.CoordUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Thanks to maxhyper
// signal_energy controls the maximum trunk length.
// The value of each position in the six directions of probMap affects the initial branch size,
// but in the end, only values greater than 0 will always grow unless the fertility is exhausted.
// Because each growth pulse is sent throughout the tree along the trunk.
public class LargeCrownTreeLogic extends PalmGrowthLogic {


    public LargeCrownTreeLogic(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    protected GrowthLogicKitConfiguration createDefaultConfiguration() {
        return super.createDefaultConfiguration();
                // .with(TURNING_LENGTH, 0);
    }

    @Override
    protected void registerProperties() {
        // this.register( TURNING_LENGTH);
    }

    @Override
    public int[] populateDirectionProbabilityMap(GrowthLogicKitConfiguration configuration, DirectionManipulationContext context) {
        final Species species = context.species();
        final Level world = context.level();
        final GrowSignal signal = context.signal();
        int[] probMap = new int[]{0, 2, 0, 0, 0, 0};
        final BlockPos pos = context.pos();
        var rootPos = signal.rootPos;
        Direction originDir = signal.dir.getOpposite();

        int currentHeight = signal.numSteps + 1;
        long seed = CoordUtils.coordHashCode(signal.rootPos, 3) + ((ServerLevel) world).getSeed();
        Random random = new Random(seed);

        if (currentHeight>20){
            Random newRandom = new Random(CoordUtils.coordHashCode(pos, 3) + ((ServerLevel) world).getSeed());
            probMap = new int[]{0, 0, newRandom.nextInt(10)+1,
                    newRandom.nextInt(10)+1,
                    newRandom.nextInt(10)+1,
                    newRandom.nextInt(10)+1};
            probMap[originDir.ordinal()]=0;
        }

        return probMap;
    }

}
