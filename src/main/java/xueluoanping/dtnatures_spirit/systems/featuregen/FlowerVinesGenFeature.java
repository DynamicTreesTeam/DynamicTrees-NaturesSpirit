package xueluoanping.dtnatures_spirit.systems.featuregen;

import com.dtteam.dynamictrees.api.configuration.ConfigurationProperty;
import com.dtteam.dynamictrees.block.leaves.DynamicLeavesBlock;
import com.dtteam.dynamictrees.block.leaves.LeavesProperties;
import com.dtteam.dynamictrees.systems.genfeature.GenFeatureConfiguration;
import com.dtteam.dynamictrees.tree.species.Species;
import com.dtteam.dynamictrees.utility.helper.CoordUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;

import java.util.List;
import java.util.Random;

// have to fix some
public class FlowerVinesGenFeature extends com.dtteam.dynamictrees.systems.genfeature.VinesGenFeature {

    public static final ConfigurationProperty<LeavesProperties> ABOVE_LEAVES = ConfigurationProperty.property("above_leaves", LeavesProperties.class);
    public static final ConfigurationProperty<Float> ABOVE_LEAVES_CHANCE = ConfigurationProperty.floatProperty("above_leaves_chance");

    public static final ConfigurationProperty<LeavesProperties> TOP_LEAVES = ConfigurationProperty.property("top_leaves", LeavesProperties.class);


    public FlowerVinesGenFeature(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    protected void registerProperties() {
        super.registerProperties();
        register(ABOVE_LEAVES, ABOVE_LEAVES_CHANCE, TOP_LEAVES);
    }

    @Override
    public GenFeatureConfiguration createDefaultConfiguration() {
        return super.createDefaultConfiguration()
                .with(ABOVE_LEAVES, LeavesProperties.NULL)
                .with(ABOVE_LEAVES_CHANCE, 0.5f)
                .with(TOP_LEAVES, LeavesProperties.NULL);
    }

    @Override
    public boolean shouldApply(Species species, GenFeatureConfiguration configuration) {
        List.of(ABOVE_LEAVES, TOP_LEAVES).forEach(property -> {
            configuration.get(property).ifValid(properties -> {
                properties.setFamily(species.getFamily());
                species.addValidLeafBlocks(properties);
            });
        });
        return true;
    }

    // This is WIP (and isn't needed in the base mod anyway, as well as the fact that there's almost certainly a better way of doing this).
    private BlockPos findGround(LevelAccessor level, BlockPos vinePos) {
        BlockPos.MutableBlockPos mPos = new BlockPos.MutableBlockPos(vinePos.getX(), vinePos.getY(), vinePos.getZ());
        do {
            mPos.move(Direction.DOWN);
            if (mPos.getY() <= 0) {
                return BlockPos.ZERO;
            }
        } while (level.isEmptyBlock(vinePos) || level.getBlockState(vinePos).getBlock() instanceof DynamicLeavesBlock);

        return mPos.above();
    }

    private Block getLeavesBlock(GenFeatureConfiguration conifuration, ConfigurationProperty<LeavesProperties> LEAVES) {
        LeavesProperties properties = conifuration.get(LEAVES);
        if (!properties.isValid() || properties.getDynamicLeavesBlock().isEmpty()) {
            return conifuration.get(LEAVES).getPrimitiveLeaves().getBlock();
        }
        return properties.getDynamicLeavesBlock().get();
    }



    @Override
    protected void addVerticalVines(GenFeatureConfiguration configuration, LevelAccessor level, Species species, BlockPos rootPos, BlockPos branchPos, boolean worldgen) {
        // Uses fruit ray trace method to grab a position under the tree's leaves.
        BlockPos vinePos = CoordUtils.getRayTraceFruitPos(level, species, rootPos, branchPos,worldgen);

        // if (!safeBounds.inBounds(vinePos, true)) {
        //     return;
        // }

        if (configuration.get(VINE_TYPE) == VineType.FLOOR) {
            vinePos = this.findGround(level, vinePos);
        }

        if (vinePos == BlockPos.ZERO) {
            return;
        }

        if (level.isEmptyBlock(vinePos)) {
            long seed = CoordUtils.coordHashCode(vinePos, 3);
            if (level instanceof ServerLevel serverLevel)
                seed += serverLevel.getSeed();

            var random = new Random(seed);
            if ((random.nextFloat() <= 0.75) && !worldgen) return;

            var above = getLeavesBlock(configuration, ABOVE_LEAVES);
            var top = getLeavesBlock(configuration, TOP_LEAVES);

            if (top != Blocks.AIR) {
                var topPos = vinePos.above();
                var nowBlockState = level.getBlockState(topPos);
                var nowBlock = nowBlockState.getBlock();
                if (nowBlock instanceof DynamicLeavesBlock & nowBlock != above) {
                    level.setBlock(topPos, top.defaultBlockState().setValue(DynamicLeavesBlock.DISTANCE,
                            nowBlockState.getValue(DynamicLeavesBlock.DISTANCE)), Block.UPDATE_ALL);
                }
            }
            if (above != Blocks.AIR) {
                var abovePos = vinePos.above().above();
                var nowBlockState = level.getBlockState(abovePos);
                var nowBlock = nowBlockState.getBlock();
                if (nowBlock instanceof DynamicLeavesBlock & nowBlock != above & nowBlock != top) {
                    if (configuration.get(ABOVE_LEAVES_CHANCE) > random.nextFloat())
                        level.setBlock(abovePos, above.defaultBlockState().setValue(DynamicLeavesBlock.DISTANCE,
                                nowBlockState.getValue(DynamicLeavesBlock.DISTANCE)), Block.UPDATE_ALL);
                }
            }
            // if(true)
            // {
            //     return;
            // }

            this.placeVines(level, vinePos, configuration.get(BLOCK).defaultBlockState(),
                    configuration.get(MAX_LENGTH),
                    configuration.getAsOptional(TIP_BLOCK)
                            .map(block -> {
                                var state = block.defaultBlockState();
                                if (state.hasProperty(GrowingPlantHeadBlock.AGE)) {
                                    state = state.setValue(GrowingPlantHeadBlock.AGE, worldgen ?
                                            25 : 25 - random.nextInt(configuration.get(MAX_LENGTH)));
                                }
                                return state;
                            })
                            .orElse(null),
                    configuration.get(VINE_TYPE), worldgen);
        }

    }
}

