package si.puntar.woodlogger.ui.activity.addMeasurement;

import javax.inject.Singleton;

import dagger.Component;
import si.puntar.woodlogger.app.AppComponent;
import si.puntar.woodlogger.app.WorkScope;


/**
 * Created by Puntar on 2/19/15.
 */
@WorkScope
@Component(
        dependencies = AppComponent.class,
        modules = MeasurementModule.class
)
public interface MeasurementComponent {
    void inject(MeasurementActivity activity);
}
