package si.puntar.woodlogger.ui;

import dagger.Module;
import dagger.Provides;
import si.puntar.woodlogger.ui.activity.base.AppContainer;

@Module
public class DebugUiModule {

    @Provides
    AppContainer provideAppContainer() {
        return AppContainer.DEFAULT;
    }


}
