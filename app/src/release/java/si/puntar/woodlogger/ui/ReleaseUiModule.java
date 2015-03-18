package si.puntar.woodlogger.ui;

import android.app.UiModeManager;

import dagger.Module;
import dagger.Provides;
import si.puntar.woodlogger.ui.activity.base.AppContainer;

@Module
public class ReleaseUiModule {

    @Provides
    AppContainer provideAppContainer() {
        return AppContainer.DEFAULT;
    }
}
