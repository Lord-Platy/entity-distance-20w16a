package s16.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.options.AccessibilityScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.options.GameOptions;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import s16.IAngryPiglinsOption;

@Mixin(AccessibilityScreen.class)
public abstract class AccessibilityScreenMixin extends Screen {

    protected AccessibilityScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void addCustomToggleButton(CallbackInfo ci) {
        GameOptions options = this.client.options;
        IAngryPiglinsOption piglinsOpt = (IAngryPiglinsOption) options;

        if (!this.buttons.isEmpty()) {
            int i = ((AccessibilityScreenAccessor)(Object)this).getOptions().length;
            int j = this.width / 2 - 155 + i % 2 * 160;
            int k = this.height / 6 + 24 * (i >> 1);

            this.addButton(new ButtonWidget(
                    j,
                    k,
                    150,
                    20,
                    getToggleText(piglinsOpt.isAngryPiglinsEnabled()),
                    (button) -> {
                        boolean newVal = !piglinsOpt.isAngryPiglinsEnabled();
                        piglinsOpt.setAngryPiglinsEnabled(newVal);
                        button.setMessage(getToggleText(newVal));
                        options.write(); // save immediately
                    }
            ));
        }
    }

    private String getToggleText(boolean value) {
        return value ? "Angry Piglins: ON" : "Angry Piglins: OFF";
    }

}
