package s16.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.mob.PiglinEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PiglinBrain.class)
public interface PiglinBrainInvoker {
    @Invoker("hasIdleActivity")
    static boolean callHasIdleActivity(PiglinEntity piglin) {
        throw new AssertionError();
    }

    @Invoker("angerAt")
    static void callAngerAt(PiglinEntity piglin, LivingEntity target) {
        throw new AssertionError();
    }
}