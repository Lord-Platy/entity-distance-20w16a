package s16.mixin;

import net.minecraft.client.options.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import s16.IEntityDistanceOptions;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Mixin(GameOptions.class)
public class GameOptionsMixin implements IEntityDistanceOptions {
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
            boolean found = false;

            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).startsWith("entityDistanceScaling:")) {
                    lines.set(i, "entityDistanceScaling:" + entityDistanceScaling);
                    found = true;
                    break;
                }
            }

            if (!found) {
                lines.add("entityDistanceScaling:" + entityDistanceScaling);
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
