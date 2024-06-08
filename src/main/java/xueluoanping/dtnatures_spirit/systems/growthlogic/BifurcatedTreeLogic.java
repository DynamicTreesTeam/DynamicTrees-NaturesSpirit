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

import java.util.Random;

// Thanks to maxhyper
// signal_energy controls the maximum trunk length.
// The value of each position in the six directions of probMap affects the initial branch size,
// but in the end, only values greater than 0 will always grow unless the fertility is exhausted.
// Because each growth pulse is sent throughout the tree along the trunk.
public class BifurcatedTreeLogic extends PalmGrowthLogic {

    public static final ConfigurationProperty<Float> CHANCE_TO_DIVERGE = ConfigurationProperty.floatProperty("chance_to_diverge");
    public static final ConfigurationProperty<Float> CHANCE_TO_SPLIT = ConfigurationProperty.floatProperty("chance_to_split");
    public static final ConfigurationProperty<Integer> TURNING_HEIGHT = ConfigurationProperty.integer("turning_height");
    public static final ConfigurationProperty<Integer> TURNING_LENGTH = ConfigurationProperty.integer("turning_length");

    public BifurcatedTreeLogic(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    protected GrowthLogicKitConfiguration createDefaultConfiguration() {
        return super.createDefaultConfiguration()
                .with(CHANCE_TO_DIVERGE, 0.8f)
                .with(CHANCE_TO_SPLIT, 0.7f)
                .with(TURNING_LENGTH, 2)
                .with(TURNING_HEIGHT, 8); // can only split under the bottom half
    }

    @Override
    protected void registerProperties() {
        this.register(CHANCE_TO_DIVERGE, CHANCE_TO_SPLIT, TURNING_LENGTH, TURNING_HEIGHT);
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

        int diverge = (int) (4 / configuration.get(CHANCE_TO_DIVERGE));
        int split = (int) (1 / configuration.get(CHANCE_TO_SPLIT));
        int randCoordCode = Math.abs(CoordUtils.coordHashCode(pos, 2));

        int directionSelection = randCoordCode % diverge;
        int splitSelection = randCoordCode % split;

        // Disable the direction we came from
        int currentSignUsed = signal.numSteps + 1;
        long seed = CoordUtils.coordHashCode(signal.rootPos, 3) + ((ServerLevel) world).getSeed();
        Random random_pos = new Random(seed);
        int randomHeight = Math.abs(random_pos.nextInt() % 2);
        int randomW = Math.abs(random_pos.nextInt() % 3);

        // DTRankine.logger();
        int d1 = 2 + Math.min(Math.abs(random_pos.nextInt() % 4), 3);
        int d2 = d1 + 1 <= 5 ? d1 + 1 : d1 + 1 - 4;
        int d3 = d1 + 2 <= 5 ? d1 + 2 : d1 + 2 - 4;
        int d4 = d1 + 3 <= 5 ? d1 + 3 : d1 + 3 - 4;
        int real_turned_height = configuration.get(TURNING_HEIGHT) + randomHeight + 1;
        float chane_to_spilt = configuration.get(CHANCE_TO_SPLIT);
        if (currentSignUsed == real_turned_height - 1 && signal.dir.ordinal() != d1 && signal.dir.ordinal() != d2) {
            if (random_pos.nextFloat() / 1.25f < chane_to_spilt)
                probMap[d3] = 10;
            if (chane_to_spilt == 1)
                probMap[d4] = 10;
            probMap[1] = 10;
        } else if (currentSignUsed == real_turned_height && signal.dir == Direction.UP) {
            probMap[d1] = 10;
            if (random_pos.nextFloat() < chane_to_spilt)
                probMap[d2] = 10;
        } else if (currentSignUsed >= real_turned_height
                && currentSignUsed < real_turned_height + Math.min(randomW + configuration.get(TURNING_LENGTH), 3)) {
            probMap[signal.dir.ordinal()] = 10;
        } else {
            probMap[Direction.UP.ordinal()] = species.getUpProbability();
        }

        // DTRankine.logger(random_pos.nextFloat() , configuration.get(CHANCE_TO_SPLIT));
        // DTRankine.logger(signal.dir, currentSignUsed, real_turned_height, d1, d2, d3, probMap, signal.energy, signal.numSteps, signal.numTurns, signal.delta);
        return probMap;
    }

}
