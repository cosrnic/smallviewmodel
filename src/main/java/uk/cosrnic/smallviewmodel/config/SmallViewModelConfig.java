package uk.cosrnic.smallviewmodel.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import uk.cosrnic.smallviewmodel.SmallViewModel;

import static me.shedaniel.autoconfig.AutoConfig.getConfigHolder;

@Config(name = SmallViewModel.MODID)
public class SmallViewModelConfig implements ConfigData {

    // MAIN HAND

    @ConfigEntry.Gui.TransitiveObject
    @ConfigEntry.Category("Main Hand")
    @ConfigEntry.BoundedDiscrete(min = -3, max = 3)
    @ConfigEntry.Gui.PrefixText
    public float posXMain = 0f;

    @ConfigEntry.Gui.TransitiveObject
    @ConfigEntry.Category("Main Hand")
    @ConfigEntry.BoundedDiscrete(min = -3, max = 3)
    public float posYMain = 0f;

    @ConfigEntry.Gui.TransitiveObject
    @ConfigEntry.Category("Main Hand")
    @ConfigEntry.BoundedDiscrete(min = -3, max = 3)
    public float posZMain = 0f;

    @ConfigEntry.Gui.TransitiveObject
    @ConfigEntry.Category("Main Hand")
    @ConfigEntry.BoundedDiscrete(min = -180, max = 180)
    @ConfigEntry.Gui.PrefixText
    public float rotXMain = 0f;

    @ConfigEntry.Gui.TransitiveObject
    @ConfigEntry.Category("Main Hand")
    @ConfigEntry.BoundedDiscrete(min = -180, max = 180)
    public float rotYMain = 0f;

    @ConfigEntry.Gui.TransitiveObject
    @ConfigEntry.Category("Main Hand")
    @ConfigEntry.BoundedDiscrete(min = -180, max = 180)
    public float rotZMain = 0f;

    @ConfigEntry.Gui.TransitiveObject
    @ConfigEntry.Category("Main Hand")
    @ConfigEntry.BoundedDiscrete(max = 5)
    @ConfigEntry.Gui.PrefixText
    public float scaleXMain = 1f;

    @ConfigEntry.Gui.TransitiveObject
    @ConfigEntry.Category("Main Hand")
    @ConfigEntry.BoundedDiscrete(max = 5)
    public float scaleYMain = 1f;

    @ConfigEntry.Gui.TransitiveObject
    @ConfigEntry.Category("Main Hand")
    @ConfigEntry.BoundedDiscrete(max = 5)
    public float scaleZMain = 1f;

    // OFF HAND

    @ConfigEntry.Gui.TransitiveObject
    @ConfigEntry.Category("Off Hand")
    @ConfigEntry.BoundedDiscrete(min = -3, max = 3)
    @ConfigEntry.Gui.PrefixText
    public float posXOff = 0f;

    @ConfigEntry.Gui.TransitiveObject
    @ConfigEntry.Category("Off Hand")
    @ConfigEntry.BoundedDiscrete(min = -3, max = 3)
    public float posYOff = 0f;

    @ConfigEntry.Gui.TransitiveObject
    @ConfigEntry.Category("Off Hand")
    @ConfigEntry.BoundedDiscrete(min = -3, max = 3)
    public float posZOff = 0f;

    @ConfigEntry.Gui.TransitiveObject
    @ConfigEntry.Category("Off Hand")
    @ConfigEntry.BoundedDiscrete(min = -180, max = 180)
    @ConfigEntry.Gui.PrefixText
    public float rotXOff = 0f;

    @ConfigEntry.Gui.TransitiveObject
    @ConfigEntry.Category("Off Hand")
    @ConfigEntry.BoundedDiscrete(min = -180, max = 180)
    public float rotYOff = 0f;

    @ConfigEntry.Gui.TransitiveObject
    @ConfigEntry.Category("Off Hand")
    @ConfigEntry.BoundedDiscrete(min = -180, max = 180)
    public float rotZOff = 0f;

    @ConfigEntry.Gui.TransitiveObject
    @ConfigEntry.Category("Off Hand")
    @ConfigEntry.BoundedDiscrete(max = 5)
    @ConfigEntry.Gui.PrefixText
    public float scaleXOff = 1f;

    @ConfigEntry.Gui.TransitiveObject
    @ConfigEntry.Category("Off Hand")
    @ConfigEntry.BoundedDiscrete(max = 5)
    public float scaleYOff = 1f;

    @ConfigEntry.Gui.TransitiveObject
    @ConfigEntry.Category("Off Hand")
    @ConfigEntry.BoundedDiscrete(max = 5)
    public float scaleZOff = 1f;

    @ConfigEntry.Gui.TransitiveObject
    @ConfigEntry.Category("Swing Animation")
    @ConfigEntry.BoundedDiscrete(min = 2, max = 20)
    public int swingAnimationDuration = 6;

//    // ARM
//
//    @ConfigEntry.Gui.TransitiveObject
//    @ConfigEntry.Category("Arm")
//    @ConfigEntry.BoundedDiscrete(min = -3, max = 3)
//    @ConfigEntry.Gui.PrefixText
//    public float posXArm = 0f;
//
//    @ConfigEntry.Gui.TransitiveObject
//    @ConfigEntry.Category("Arm")
//    @ConfigEntry.BoundedDiscrete(min = -3, max = 3)
//    public float posYArm = 0f;
//
//    @ConfigEntry.Gui.TransitiveObject
//    @ConfigEntry.Category("Arm")
//    @ConfigEntry.BoundedDiscrete(min = -3, max = 3)
//    public float posZArm = 0f;
//
//    @ConfigEntry.Gui.TransitiveObject
//    @ConfigEntry.Category("Arm")
//    @ConfigEntry.BoundedDiscrete(min = -180, max = 180)
//    @ConfigEntry.Gui.PrefixText
//    public float rotXArm = 0f;
//
//    @ConfigEntry.Gui.TransitiveObject
//    @ConfigEntry.Category("Arm")
//    @ConfigEntry.BoundedDiscrete(min = -180, max = 180)
//    public float rotYArm = 0f;
//
//    @ConfigEntry.Gui.TransitiveObject
//    @ConfigEntry.Category("Arm")
//    @ConfigEntry.BoundedDiscrete(min = -180, max = 180)
//    public float rotZArm = 0f;
//
//    @ConfigEntry.Gui.TransitiveObject
//    @ConfigEntry.Category("Arm")
//    @ConfigEntry.BoundedDiscrete(max = 5)
//    @ConfigEntry.Gui.PrefixText
//    public float scaleXArm = 1f;
//
//    @ConfigEntry.Gui.TransitiveObject
//    @ConfigEntry.Category("Arm")
//    @ConfigEntry.BoundedDiscrete( max = 5)
//    public float scaleYArm = 1f;
//
//    @ConfigEntry.Gui.TransitiveObject
//    @ConfigEntry.Category("Arm")
//    @ConfigEntry.BoundedDiscrete( max = 5)
//    public float scaleZArm = 1f;

    public static void register() {
        AutoConfig.register(SmallViewModelConfig.class, GsonConfigSerializer::new);
    }

    public static SmallViewModelConfig get() {
        return getConfigHolder(SmallViewModelConfig.class).getConfig();
    }

    public static void save() {
        getConfigHolder(SmallViewModelConfig.class).save();
    }
}
