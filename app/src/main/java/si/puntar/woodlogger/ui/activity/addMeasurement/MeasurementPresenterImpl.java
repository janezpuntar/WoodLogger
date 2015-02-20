package si.puntar.woodlogger.ui.activity.addMeasurement;

import javax.inject.Inject;

/**
 * Created by Puntar on 2/19/15.
 */
public class MeasurementPresenterImpl implements MeasurementPresenter {

    private MeasurementView view;

    @Inject
    public MeasurementPresenterImpl(MeasurementView view) {
        this.view = view;
    }
}
