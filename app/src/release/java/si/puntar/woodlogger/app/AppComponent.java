package si.puntar.woodlogger.app;

import javax.inject.Singleton;

import dagger.Component;
import si.puntar.woodlogger.ui.ReleaseUiModule;
import si.puntar.woodlogger.manager.ManagerModule;
import si.puntar.woodlogger.manager.SystemServicesModule;
import si.puntar.woodlogger.ui.activity.main.MainActivity;


/**
 * Created by Puntar on 2/25/15.
 */
@Singleton
@Component(modules = {AppModule.class,
        ManagerModule.class,
        SystemServicesModule.class,
        ReleaseUiModule.class}
)
public interface AppComponent extends AppGraph {

}
