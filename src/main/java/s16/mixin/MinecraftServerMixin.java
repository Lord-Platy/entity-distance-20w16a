package s16.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import s16.IEntityTrackerOptions;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin implements IEntityTrackerOptions {
    @Override
    public int adjustTrackingDistance(int initialDistance) {
        return initialDistance;
    }
}
