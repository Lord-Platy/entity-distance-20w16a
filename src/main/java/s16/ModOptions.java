package s16;

import net.minecraft.client.options.DoubleOption;
import net.minecraft.client.resource.language.I18n;

public class ModOptions {

    public static final DoubleOption ENTITY_DISTANCE_SCALING = new DoubleOption(
            "Entity Distance",
            0.5,
            5.0,
            0.25F,
            gameOptions -> ((IEntityDistanceOptions) gameOptions).getEntityDistanceScaling(),
            (gameOptions, value) -> ((IEntityDistanceOptions) gameOptions).setEntityDistanceScaling(value),
            (gameOptions, doubleOption) -> {
                double d = doubleOption.get(gameOptions);
                return doubleOption.getDisplayPrefix() + I18n.translate("%s%%", (int)(d * 100.0));
            }
    );
}
