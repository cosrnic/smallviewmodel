package wales.cosmic.smallviewmodel.config;


import com.google.gson.Gson;
import com.moulberry.lattice.annotation.LatticeCategory;
import com.moulberry.lattice.annotation.LatticeFormatValues;
import com.moulberry.lattice.annotation.LatticeOption;
import com.moulberry.lattice.annotation.constraint.LatticeFloatRange;
import com.moulberry.lattice.annotation.widget.LatticeWidgetSlider;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class SmallViewModelConfig {

    private static final Logger log = LoggerFactory.getLogger(SmallViewModelConfig.class);
    private static SmallViewModelConfig INSTANCE;

    @LatticeCategory(name = "smallviewmodel.main_hand")
    public MainHand mainHand = new MainHand();

    @LatticeCategory(name = "smallviewmodel.off_hand")
    public OffHand offHand = new OffHand();

    @LatticeCategory(name = "smallviewmodel.arm")
    public Arm arm = new Arm();

    public static class MainHand {

        @LatticeCategory(name = "smallviewmodel.position")
        public Position position = new Position();

        @LatticeCategory(name = "smallviewmodel.rotation")
        public Rotation rotation = new Rotation();

        @LatticeCategory(name = "smallviewmodel.scale")
        public Scale scale = new Scale();

    }

    public static class OffHand {

        @LatticeCategory(name = "smallviewmodel.position")
        public Position position = new Position();

        @LatticeCategory(name = "smallviewmodel.rotation")
        public Rotation rotation = new Rotation();

        @LatticeCategory(name = "smallviewmodel.scale")
        public Scale scale = new Scale();
    }

    public static class Arm {

        @LatticeCategory(name = "smallviewmodel.position")
        public Position position = new Position();

        @LatticeCategory(name = "smallviewmodel.rotation")
        public Rotation rotation = new Rotation();

        @LatticeCategory(name = "smallviewmodel.scale")
        public Scale scale = new Scale();
    }

    public static class Position {
        @LatticeOption(title = "smallviewmodel.position.x")
//        @LatticeDisableIf(function = "checkDisabled", frequency = LatticeDynamicFrequency.EVERY_TICK)
        @LatticeFloatRange(min = -3, max = 3, clampMin = -3, clampMax = 3)
        @LatticeFormatValues(formattingString = "%.2f")
        @LatticeWidgetSlider
        public float x = 0f;

        @LatticeOption(title = "smallviewmodel.position.y")
//        @LatticeDisableIf(function = "checkDisabled", frequency = LatticeDynamicFrequency.EVERY_TICK)
        @LatticeFloatRange(min = -3, max = 3, clampMin = -3, clampMax = 3)
        @LatticeFormatValues(formattingString = "%.2f")
        @LatticeWidgetSlider
        public float y = 0f;

        @LatticeOption(title = "smallviewmodel.position.z")
//        @LatticeDisableIf(function = "checkDisabled", frequency = LatticeDynamicFrequency.EVERY_TICK)
        @LatticeFloatRange(min = -3, max = 3, clampMin = -3, clampMax = 3)
        @LatticeFormatValues(formattingString = "%.2f")
        @LatticeWidgetSlider
        public float z = 0f;

        // todo: this for some reason effects all sliders even ones in different classes, unsure why probably a Lattice bug
//        @LatticeOption(title = "smallviewmodel.enabled")
//        @LatticeWidgetButton
//        public boolean enabled = true;
//
//        private boolean checkDisabled() {
//            return !this.enabled;
//        }

    }

    public static class Rotation {
        @LatticeOption(title = "smallviewmodel.rotation.x")
//        @LatticeDisableIf(function = "checkDisabled", frequency = LatticeDynamicFrequency.EVERY_TICK)
        @LatticeFloatRange(min = -180, max = 180, clampMin = -180, clampMax = 180)
        @LatticeFormatValues(formattingString = "%.2f")
        @LatticeWidgetSlider
        public float x = 0f;

        @LatticeOption(title = "smallviewmodel.rotation.y")
//        @LatticeDisableIf(function = "checkDisabled", frequency = LatticeDynamicFrequency.EVERY_TICK)
        @LatticeFloatRange(min = -180, max = 180, clampMin = -180, clampMax = 180)
        @LatticeFormatValues(formattingString = "%.2f")
        @LatticeWidgetSlider
        public float y = 0f;

        @LatticeOption(title = "smallviewmodel.rotation.z")
//        @LatticeDisableIf(function = "checkDisabled", frequency = LatticeDynamicFrequency.EVERY_TICK)
        @LatticeFloatRange(min = -180, max = 180, clampMin = -180, clampMax = 180)
        @LatticeFormatValues(formattingString = "%.2f")
        @LatticeWidgetSlider
        public float z = 0f;

        // todo: this for some reason effects all sliders even ones in different classes, unsure why probably a Lattice bug
//        @LatticeOption(title = "smallviewmodel.enabled")
//        @LatticeWidgetButton
//        public boolean enabled = true;
//
//        private boolean checkDisabled() {
//            return !this.enabled;
//        }
    }

    public static class Scale {
        @LatticeOption(title = "smallviewmodel.scale.x")
//        @LatticeDisableIf(function = "checkDisabled", frequency = LatticeDynamicFrequency.EVERY_TICK)
        @LatticeFloatRange(min = 0.1f, max = 5, clampMin = 0.1f, clampMax = 5)
        @LatticeFormatValues(formattingString = "%.2f")
        @LatticeWidgetSlider
        public float x = 1f;

        @LatticeOption(title = "smallviewmodel.scale.y")
//        @LatticeDisableIf(function = "checkDisabled", frequency = LatticeDynamicFrequency.EVERY_TICK)
        @LatticeFloatRange(min = 0.1f, max = 5, clampMin = 0.1f, clampMax = 5)
        @LatticeFormatValues(formattingString = "%.2f")
        @LatticeWidgetSlider
        public float y = 1f;

        @LatticeOption(title = "smallviewmodel.scale.z")
//        @LatticeDisableIf(function = "checkDisabled", frequency = LatticeDynamicFrequency.EVERY_TICK)
        @LatticeFloatRange(min = 0.1f, max = 5, clampMin = 0.1f, clampMax = 5)
        @LatticeFormatValues(formattingString = "%.2f")
        @LatticeWidgetSlider
        public float z = 1f;

        // todo: this for some reason effects all sliders even ones in different classes, unsure why probably a Lattice bug
//        @LatticeOption(title = "smallviewmodel.enabled")
//        @LatticeWidgetButton
//        public boolean enabled = true;
//
//        private boolean checkDisabled() {
//            return !this.enabled;
//        }
    }

    public static SmallViewModelConfig get() {
        if (INSTANCE == null) {
            load();
        }
        return INSTANCE;
    }

    public static synchronized void save() {
        if (INSTANCE == null) {
            throw new IllegalStateException("Cannot save if the config wasn't loaded");
        }

        Path configDir = FabricLoader.getInstance().getConfigDir().resolve("smallviewmodel");
        Path configFile = configDir.resolve("smallviewmodel.json");

        var json = new Gson().toJson(INSTANCE);

        try {
            Files.createDirectories(configDir);
            Files.writeString(configFile, json, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE, StandardOpenOption.DSYNC);
        } catch (IOException e) {
            log.error("Failed to save config", e);
        }

    }

    private static void load() {
        if (INSTANCE != null) {
            throw new IllegalStateException("Config is already loaded");
        }

        Path configDir = FabricLoader.getInstance().getConfigDir().resolve("smallviewmodel");
        Path configFile = configDir.resolve("smallviewmodel.json");
        if (!Files.exists(configFile)) {
            INSTANCE = new SmallViewModelConfig();
            save();
        }

        try {
            String json = Files.readString(configFile);

            INSTANCE = new Gson().fromJson(json, SmallViewModelConfig.class);
        } catch (IOException e) {
            log.warn("Failed to load config, defaulting", e);
            INSTANCE = new SmallViewModelConfig();
        }

    }
}
