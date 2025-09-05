package s16.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import s16.IEntityDistanceOptions;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

    @ModifyArg(
            method = "setupTerrain",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/Entity;setRenderDistanceMultiplier(D)V"
            ),
            index = 0
    )
    private double injectEntityDistanceScaling(double original) {

        MinecraftClient client = MinecraftClient.getInstance();

        return original * ((IEntityDistanceOptions) client.options).getEntityDistanceScaling();
    }
}
