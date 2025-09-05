package s16.mixin;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import s16.IEntityTrackerOptions;

import java.util.Collection;

@Mixin(targets = "net.minecraft.server.world.ThreadedAnvilChunkStorage$EntityTracker")
public abstract class EntityTrackerMixin {
    @Shadow private Entity entity;
    @Shadow private int maxDistance;

    @Unique
    private int adjustTrackingDistance(int initialDistance) {
        return ((IEntityTrackerOptions) this.entity.getEntityWorld().getServer())
                .adjustTrackingDistance(initialDistance);
    }

    @Overwrite
    private int getMaxTrackDistance() {
        Collection<Entity> collection = this.entity.getPassengersDeep();
        int i = this.maxDistance;

        for (Entity entity : collection) {
            int j = entity.getType().getMaxTrackDistance() * 16;
            if (j > i) {
                i = j;
            }
        }
        return this.adjustTrackingDistance(i);
    }
}
