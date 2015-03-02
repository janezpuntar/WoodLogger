package si.puntar.woodlogger.ui;

import android.app.UiModeManager;

import dagger.Module;
import dagger.Provides;
import si.puntar.woodlogger.ui.activity.WorkScope;
import si.puntar.woodlogger.ui.activity.base.AppContainer;

@Module(includes = UiModeManager.class)
public class ReleaseUiModule {
    @Provides
    @WorkScope
    AppContainer provideAppContainer() {
        return AppContainer.DEFAULT;
    }


}
