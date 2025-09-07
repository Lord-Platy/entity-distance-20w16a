package s16.mixin;

import net.minecraft.client.options.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import s16.IEntityDistanceOptions;
import s16.IAngryPiglinsOption;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Mixin(GameOptions.class)
public class GameOptionsMixin implements IEntityDistanceOptions, IAngryPiglinsOption {

    @Unique
    private double entityDistanceScaling = 1.0D;

    @Override
    public double getEntityDistanceScaling() {
        return entityDistanceScaling;
    }

    @Override
    public void setEntityDistanceScaling(double value) {
        this.entityDistanceScaling = value;
    }

    @Unique
    private boolean angryPiglins = false;

    @Override
    public boolean isAngryPiglinsEnabled() {
        return angryPiglins;
    }

    @Override
    public void setAngryPiglinsEnabled(boolean enabled) {
        this.angryPiglins = enabled;
        s16.AngryPiglinsConfig.setEnabled(enabled);
    }

    @Inject(method = "load", at = @At("TAIL"))
    private void onLoad(CallbackInfo ci) {
        Path optionsFile = ((GameOptionsAccessor) (Object) this).getOptionsFile().toPath();

        if (Files.exists(optionsFile)) {
            try {
                for (String line : Files.readAllLines(optionsFile)) {
                    if (line.startsWith("entityDistanceScaling:")) {
                        try {
                            entityDistanceScaling = Double.parseDouble(line.split(":")[1]);
                        } catch (NumberFormatException ignored) {}
                    } else if (line.startsWith("angryPiglins:")) {
                        setAngryPiglinsEnabled(Boolean.parseBoolean(line.split(":")[1]));
                    }
                }
            } catch (IOException ignored) {}
        }
    }

    @Inject(method = "write", at = @At("TAIL"))
    private void onWrite(CallbackInfo ci) {
        Path optionsFile = ((GameOptionsAccessor) (Object) this).getOptionsFile().toPath();

        try {
            List<String> lines = Files.readAllLines(optionsFile);

            boolean foundEntityDistance = false;
            boolean foundAngryPiglins = false;

            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).startsWith("entityDistanceScaling:")) {
                    lines.set(i, "entityDistanceScaling:" + entityDistanceScaling);
                    foundEntityDistance = true;
                } else if (lines.get(i).startsWith("angryPiglins:")) {
                    lines.set(i, "angryPiglins:" + angryPiglins);
                    foundAngryPiglins = true;
                }
            }

            if (!foundEntityDistance) {
                lines.add("entityDistanceScaling:" + entityDistanceScaling);
            }
            if (!foundAngryPiglins) {
                lines.add("angryPiglins:" + angryPiglins);
            }

            try (BufferedWriter writer = Files.newBufferedWriter(optionsFile)) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException ignored) {}
    }
}
