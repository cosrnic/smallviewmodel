package wales.cosmic.smallviewmodel.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import wales.cosmic.smallviewmodel.SmallViewModel;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        var config = SmallViewModelConfig.get();
        return parent -> SmallViewModel.getConfigScreen(parent, config);
    }
}
