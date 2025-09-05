/*
package s16.mixin;

import net.minecraft.server.dedicated.MinecraftDedicatedServer;
import net.minecraft.server.dedicated.ServerPropertiesHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import s16.IEntityTrackerOptions;

import java.net.Proxy;

@Mixin(MinecraftDedicatedServer.class)
public abstract class MinecraftDedicatedServerMixin implements IEntityTrackerOptions {
    @Shadow @Final
    private ServerPropertiesHandler properties;

    @Override
    public int adjustTrackingDistance(int initialDistance) {
        return this.properties.getProperties().entityBroadcastRangePercentage * initialDistance / 100;
    }
}*/




