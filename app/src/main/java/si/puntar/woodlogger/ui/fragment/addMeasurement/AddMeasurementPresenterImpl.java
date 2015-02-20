package si.puntar.woodlogger.ui.fragment.addMeasurement;

import javax.inject.Inject;

import si.puntar.woodlogger.data.model.Log;

/**
 * Created by Puntar on 2/19/15.
 */
public class AddMeasurementPresenterImpl implements AddMeasurementPresenter {

    private final AddMeasurementView view;

    private double selectedLength = 4d;

    @Inject
    public AddMeasurementPresenterImpl(AddMeasurementView view) {
        this.view = view;
    }

    @Override
    public void verifyData(String diameter) {
        view.publishLog(new Log( selectedLength, Double.parseDouble(diameter)));
    }
}
