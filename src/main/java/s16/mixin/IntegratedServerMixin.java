package s16.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.server.integrated.IntegratedServer;
import org.spongepowered.asm.mixin.Mixin;
import s16.IEntityDistanceOptions;
import s16.IEntityTrackerOptions;

@Mixin(IntegratedServer.class)
public abstract class IntegratedServerMixin implements IEntityTrackerOptions {
    @Override
    public int adjustTrackingDistance(int initialDistance) {
        MinecraftClient client = MinecraftClient.getInstance();
        double scale = ((IEntityDistanceOptions) client.options).getEntityDistanceScaling();
        return (int) (initialDistance * scale);
    }
}

