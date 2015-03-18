package si.puntar.woodlogger.ui.activity.addMeasurement;

import dagger.Module;
import dagger.Provides;
import si.puntar.woodlogger.manager.rx.LogManager;
import si.puntar.woodlogger.manager.rx.OrderManager;

/**
 * Created by Puntar on 2/19/15.
 */
@Module
public class MeasurementModule {

    private MeasurementView view;

    public MeasurementModule(MeasurementView view) {
        this.view = view;
    }

    @Provides
    MeasurementView provideView() {
        return view;
    }

    @Provides
    MeasurementPresenter providePresenter(MeasurementView view,
                                          OrderManager orderManager,
                                          LogManager logManager) {
        return new MeasurementPresenterImpl(view, orderManager, logManager);
    }
}
