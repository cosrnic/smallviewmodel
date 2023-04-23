package uk.cosrnic.smallviewmodel;

import net.fabricmc.api.ModInitializer;
import uk.cosrnic.smallviewmodel.config.SmallViewModelConfig;

public class SmallViewModel implements ModInitializer {

    public static final String MODID = "smallviewmodel";
    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        SmallViewModelConfig.register();
    }
}
