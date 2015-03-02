package si.puntar.woodlogger.app;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import si.puntar.woodlogger.data.DataModule;
import si.puntar.woodlogger.manager.ManagerModule;
import si.puntar.woodlogger.manager.SystemServicesModule;

/**
 * Created by Janez Puntar on 06/02/15.
 */
@Module(
        includes = {
                SystemServicesModule.class
        }
)
public class AppModule {

    private final App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides @Singleton App provideApp() {
        return this.app;
    }

    @Provides @Singleton Context provideContext(App app) {
        return app;
    }






}
