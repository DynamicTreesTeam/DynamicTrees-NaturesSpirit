package xueluoanping.dtnatures_spirit.mixin;

import net.hibiscus.naturespirit.blocks.WisteriaVinePlant;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin({WisteriaVinePlant.class})
public class MixinWisteriaVinePlant {

    @Inject(at = @At("HEAD"), method = "canSurvive", cancellable = true)
    private void zz$canSurvive(BlockState state, LevelReader worldIn, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        // cir.setReturnValue(true);
    }
}
