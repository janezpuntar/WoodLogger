package si.puntar.woodlogger.ui.fragment.addMeasurement;

import dagger.Component;
import si.puntar.woodlogger.app.AppComponent;
import si.puntar.woodlogger.app.WorkScope;

/**
 * Created by Puntar on 2/19/15.
 */
@WorkScope
@Component(
        dependencies = AppComponent.class,
        modules = AddMeasurementModule.class
)
public interface AddMeasurementComponent {
    void inject(AddMeasurementFragment fragment);
}
