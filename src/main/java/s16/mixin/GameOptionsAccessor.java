package s16.mixin;

import net.minecraft.client.options.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.io.File;

@Mixin(GameOptions.class)
public interface GameOptionsAccessor {
    @Accessor("optionsFile")
    File getOptionsFile();
}
