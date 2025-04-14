package xueluoanping.dtnatures_spirit.systems.pods;

import com.dtteam.dynamictrees.api.registry.TypedRegistry;

import com.dtteam.dynamictrees.block.pod.Pod;
import com.dtteam.dynamictrees.block.pod.PodBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;


// Thanks supermassimo
public class FallingPalmPod extends Pod {

    public static final TypedRegistry.EntryType<Pod> TYPE = TypedRegistry.newType(FallingPalmPod::new);

    public FallingPalmPod(ResourceLocation registryName) {
        super(registryName);
    }

    protected PodBlock createBlock(Block.Properties properties) {
        return new FallingPodBlock(properties, this);
    }


    @Override
    public void place(LevelAccessor level, BlockPos pos, @Nullable Float seasonValue, Direction facing, int radius) {
        BlockState state = getStateFor(facing, 0, radius);
        level.setBlock(pos, state, Block.UPDATE_ALL);
    }

    @Override
    public void placeDuringWorldGen(LevelAccessor level, BlockPos pos, @Nullable Float seasonValue, Direction facing, int radius) {
        BlockState state = getStateFor(facing, getAgeForWorldGen(level, pos, seasonValue), radius);
        level.setBlock(pos, state, Block.UPDATE_CLIENTS);
    }
    //
    // @Override
    // public void setBlockShapes(Direction facing, VoxelShape[] blockShapes) {
    //     super.setBlockShapes(facing, blockShapes);
    // }
}
