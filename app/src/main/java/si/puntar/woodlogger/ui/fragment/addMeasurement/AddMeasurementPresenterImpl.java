package si.puntar.woodlogger.ui.fragment.addMeasurement;

import javax.inject.Inject;

import si.puntar.woodlogger.data.model.Log;
import si.puntar.woodlogger.data.model.LogLength;

/**
 * Created by Puntar on 2/19/15.
 */
public class AddMeasurementPresenterImpl implements AddMeasurementPresenter {

    private final AddMeasurementView view;

    private LogLength selectedLength;

    @Inject
    public AddMeasurementPresenterImpl(AddMeasurementView view) {
        this.view = view;
    }

    @Override
    public void verifyData(String diameter) {
        view.publishLog(new Log(selectedLength, Double.parseDouble(diameter)));
    }

    @Override
    public void setCurrentLogLength(LogLength logLength) {
        selectedLength = logLength;
    }
}
