package wales.cosmic.smallviewmodel;

import com.moulberry.lattice.Lattice;
import com.moulberry.lattice.element.LatticeElements;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import wales.cosmic.smallviewmodel.config.SmallViewModelConfig;

public class SmallViewModel implements ClientModInitializer {

    public static final String MODID = "smallviewmodel";

    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitializeClient() {
        SmallViewModelConfig.get();

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, ctx) -> {
            var command = ClientCommandManager.literal("smallviewmodel").executes(context -> {
                Minecraft.getInstance().schedule(() -> Minecraft.getInstance().setScreen(
                    getConfigScreen(Minecraft.getInstance().screen, SmallViewModelConfig.get())
                ));
                return 0;
            });
            dispatcher.register(command);
        });
    }

    public static Screen getConfigScreen(Screen parent, SmallViewModelConfig config) {
        return Lattice.createConfigScreen(LatticeElements.fromAnnotations(Component.translatable("smallviewmodel.title"), config), SmallViewModelConfig::save, parent);
    }

}
