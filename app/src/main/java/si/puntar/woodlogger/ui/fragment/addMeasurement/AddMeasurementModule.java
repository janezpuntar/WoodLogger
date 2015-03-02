package si.puntar.woodlogger.ui.fragment.addMeasurement;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Puntar on 2/19/15.
 */
@Module
public class AddMeasurementModule {

    private AddMeasurementView view;

    public AddMeasurementModule(AddMeasurementView view) {
        this.view = view;
    }

    @Provides
    AddMeasurementView provideView() {
        return view;
    }

    @Provides
    AddMeasurementPresenter providePresenter(AddMeasurementView view) {
        return new AddMeasurementPresenterImpl(view);
    }
}
