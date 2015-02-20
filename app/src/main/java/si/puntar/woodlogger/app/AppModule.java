package si.puntar.woodlogger.app;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Janez Puntar on 06/02/15.
 */
@Module
public class AppModule {

    private final App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @ApplicationScope
    public App provideApplication() {
        return this.app;
    }

    @Provides
    @ApplicationScope
    public Context provideContext() {
        return this.app;
    }

}
