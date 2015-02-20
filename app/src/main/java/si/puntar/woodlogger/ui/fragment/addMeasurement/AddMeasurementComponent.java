package si.puntar.woodlogger.ui.fragment.addMeasurement;

import dagger.Component;
import si.puntar.woodlogger.app.AppComponent;
import si.puntar.woodlogger.ui.activity.ActivityScope;
import si.puntar.woodlogger.ui.activity.FragmentScope;
import si.puntar.woodlogger.ui.activity.main.MainModule;

/**
 * Created by Puntar on 2/19/15.
 */
@FragmentScope
@Component(
        dependencies = AppComponent.class,
        modules = AddMeasurementModule.class
)
public interface AddMeasurementComponent {
    void inject(AddMeasurementFragment fragment);
}
