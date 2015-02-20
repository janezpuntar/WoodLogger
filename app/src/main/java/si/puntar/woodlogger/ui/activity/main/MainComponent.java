package si.puntar.woodlogger.ui.activity.main;

import dagger.Component;
import si.puntar.woodlogger.app.AppComponent;
import si.puntar.woodlogger.ui.activity.ActivityScope;

/**
 * Created by Puntar on 2/17/15.
 */
@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = MainModule.class
)
public interface MainComponent {
    void inject(MainActivity activity);
}
