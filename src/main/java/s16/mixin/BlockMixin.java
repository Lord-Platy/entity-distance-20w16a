package s16.mixin;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import s16.PiglinUtils;

@Mixin(Block.class)
public abstract class BlockMixin {
    @Redirect(
            method = "onBreak",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/mob/PiglinBrain;onGoldBlockBroken(Lnet/minecraft/entity/player/PlayerEntity;)V"
            )
    )
    private void redirectGoldBlockBrokenInOnBreak(PlayerEntity player) {
        if (s16.AngryPiglinsConfig.isEnabled()) {
            PiglinUtils.onGuardedBlockBroken(player, false);
        } else {
            net.minecraft.entity.mob.PiglinBrain.onGoldBlockBroken(player);
        }
    }
}

