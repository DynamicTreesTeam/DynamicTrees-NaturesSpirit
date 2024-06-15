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
import xueluoanping.dtnatures_spirit.DTNaturesSpirit;

import java.util.Random;

// Thanks to maxhyper
// signal_energy controls the maximum trunk length.
// The value of each position in the six directions of probMap affects the initial branch size,
// but in the end, only values greater than 0 will always grow unless the fertility is exhausted.
// Because each growth pulse is sent throughout the tree along the trunk.
public class CrookedPalmLogic extends PalmGrowthLogic {

    public static final ConfigurationProperty<Float> CHANCE_TO_DIVERGE = ConfigurationProperty.floatProperty("chance_to_diverge");
    public static final ConfigurationProperty<Float> CHANCE_TO_SPLIT = ConfigurationProperty.floatProperty("chance_to_split");
    public static final ConfigurationProperty<Integer> TURNING_HEIGHT = ConfigurationProperty.integer("turning_height");

    public CrookedPalmLogic(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    protected GrowthLogicKitConfiguration createDefaultConfiguration() {
        return super.createDefaultConfiguration()
                .with(CHANCE_TO_DIVERGE, 0.8f)
                .with(CHANCE_TO_SPLIT, 0.06f)
                .with(TURNING_HEIGHT, 5); // can only split under the bottom half
    }

    @Override
    protected void registerProperties() {
        this.register(CHANCE_TO_DIVERGE, CHANCE_TO_SPLIT, TURNING_HEIGHT);
    }

    @Override
    public int[] populateDirectionProbabilityMap(GrowthLogicKitConfiguration configuration, DirectionManipulationContext context) {
        final Species species = context.species();
        final Level world = context.level();
        final GrowSignal signal = context.signal();
        final int[] probMap = context.probMap();
        final BlockPos pos = context.pos();
        Direction originDir = signal.dir.getOpposite();

        // Alter probability map for direction change
        probMap[0] = 0; // Down is always disallowed for palm
        probMap[1] = 0;
        // Start by disabling probability on the sides
        probMap[2] = probMap[3] = probMap[4] = probMap[5] = 0;

        long seed = CoordUtils.coordHashCode(signal.rootPos, 3) + ((ServerLevel) world).getSeed();
        Random random = new Random(seed);

        // Disable the direction we came from
        int randomHeight = species.getLowestBranchHeight() + random.nextInt(2);
        int randomW = random.nextInt(2);
        int randomDirection = 2 + random.nextInt(4);

        int step = signal.numSteps;

        if (randomHeight <= step && step <= randomHeight + randomW) {
            probMap[randomDirection] = 10;
        } else {
            probMap[Direction.UP.ordinal()] = species.getUpProbability();
        }

        return probMap;
    }

}
