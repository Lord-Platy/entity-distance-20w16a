package s16.mixin;

import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import s16.PiglinUtils;

@Mixin(PiglinBrain.class)
public class PiglinBrainMixin {

    @Inject(method = "onGoldBlockBroken", at = @At("HEAD"), cancellable = true)
    private static void injectOnGoldBlockBroken(PlayerEntity player, CallbackInfo ci) {
        if (s16.AngryPiglinsConfig.isEnabled()) {
            PiglinUtils.onGuardedBlockBroken(player, true);
            ci.cancel();
        }
    }
}
