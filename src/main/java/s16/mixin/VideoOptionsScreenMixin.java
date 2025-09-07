package s16.mixin;

import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.VideoOptionsScreen;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import s16.ModOptions;

@Mixin(VideoOptionsScreen.class)
public abstract class VideoOptionsScreenMixin extends Screen {

    protected VideoOptionsScreenMixin(Text title) {
        super(title);
    }
    @Inject(method = "init", at = @At("TAIL"))
    private void addCustomSlider(CallbackInfo ci) {
        for (Element child : this.children()) {
            if (child instanceof ButtonListWidget) {
                ButtonListWidget list = (ButtonListWidget) child;

                list.addOptionEntry(ModOptions.ENTITY_DISTANCE_SCALING, null);
                break;
            }
        }
    }
}
