package s16.mixin;

import net.minecraft.client.gui.screen.options.AccessibilityScreen;
import net.minecraft.client.options.Option;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AccessibilityScreen.class)
public interface AccessibilityScreenAccessor {
    @Accessor("OPTIONS")
    Option[] getOptions();
}


