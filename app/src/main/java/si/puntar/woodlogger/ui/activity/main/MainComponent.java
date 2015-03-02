package si.puntar.woodlogger.ui.activity.main;

import javax.inject.Singleton;

import dagger.Component;
import si.puntar.woodlogger.app.AppComponent;
import si.puntar.woodlogger.app.WorkScope;
import si.puntar.woodlogger.manager.NekClass;

/**
 * Created by Puntar on 2/17/15.
 */
@WorkScope
@Component(
        dependencies = AppComponent.class,
        modules = MainModule.class
)
public interface MainComponent {
    void inject(MainActivity activity);
}
