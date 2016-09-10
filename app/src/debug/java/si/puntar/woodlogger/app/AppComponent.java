package si.puntar.woodlogger.app;

import javax.inject.Singleton;

import dagger.Component;
import si.puntar.woodlogger.ui.DebugUiModule;


/**
 * Created by Puntar on 2/25/15.
 */
@Singleton
@Component(modules = {
        AppModule.class,
        DebugUiModule.class}
)
public interface AppComponent extends AppGraph {

}
