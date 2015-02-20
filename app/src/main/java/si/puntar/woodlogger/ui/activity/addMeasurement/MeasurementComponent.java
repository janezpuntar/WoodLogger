package si.puntar.woodlogger.ui.activity.addMeasurement;

import dagger.Component;
import si.puntar.woodlogger.app.AppComponent;
import si.puntar.woodlogger.ui.activity.ActivityScope;
import si.puntar.woodlogger.ui.activity.main.MainComponent;
import si.puntar.woodlogger.ui.activity.main.MainModule;

/**
 * Created by Puntar on 2/19/15.
 */
@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = MeasurementModule.class
)
public interface MeasurementComponent {
    void inject(MeasurementActivity activity);
}
