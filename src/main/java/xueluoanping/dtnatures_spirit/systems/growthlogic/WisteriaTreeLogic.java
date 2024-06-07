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
public class WisteriaTreeLogic extends PalmGrowthLogic {

    public static final ConfigurationProperty<Integer> TURNING_LENGTH = ConfigurationProperty.integer("turning_length");

    public WisteriaTreeLogic(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    protected GrowthLogicKitConfiguration createDefaultConfiguration() {
        return super.createDefaultConfiguration()
                .with(TURNING_LENGTH, 0);
    }

    @Override
    protected void registerProperties() {
        this.register( TURNING_LENGTH);
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

        int forkLength = Math.abs(random.nextInt(4)) + 1;
        int forkHeight = Math.abs(random.nextInt(4)) + 1 + species.getLowestBranchHeight();
        int tl = configuration.get(TURNING_LENGTH);
        int bendingNode = (tl > 0 ? Math.abs(random.nextInt(tl)) : 0) + species.getLowestBranchHeight();
        int directionOrin = random.nextInt(4) + 2;


        // DTNaturesSpirit.logger(species.getLowestBranchHeight(),currentHeight, pos, originDir.ordinal());

        List<Integer> casesList = new ArrayList<>();
        for (int i = forkHeight; i < forkHeight + forkLength; i++) {
            casesList.add(i);
        }
        if (casesList.contains(currentHeight)) {
            probMap[directionOrin] = 1;
        }
        if (currentHeight <= bendingNode) {
            probMap[1] = 0;
            probMap[directionOrin] = 1;
        }
        // if (pos.getX() == rootPos.getX() && pos.getZ() == rootPos.getZ() && pos.getY() > rootPos.getY() + 6)
        // // if (random.nextBoolean())
        // {
        //     probMap = new int[]{0, 0, 0, 0, 0, 0};
        // }
        // DTNaturesSpirit.logger(currentHeight, probMap);
        return probMap;
    }

}
