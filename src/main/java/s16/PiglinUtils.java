package s16;

import net.minecraft.entity.ai.brain.task.LookTargetUtil;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import s16.mixin.PiglinBrainInvoker;

import java.util.List;

public class PiglinUtils {

    public static void onGuardedBlockBroken(PlayerEntity player, boolean bl) {
        List<PiglinEntity> list = player.world.getNonSpectatingEntities(
                PiglinEntity.class,
                player.getBoundingBox().expand(16.0)
        );

        list.stream()
                .filter(PiglinBrainInvoker::callHasIdleActivity)
                .filter(piglin -> !bl || LookTargetUtil.isVisibleInMemory(piglin, player))
                .forEach(piglin -> PiglinBrainInvoker.callAngerAt(piglin, player));
    }
}
