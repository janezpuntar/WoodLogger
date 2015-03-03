package si.puntar.woodlogger.ui.fragment.addMeasurement;

import si.puntar.woodlogger.data.model.LogLength;

/**
 * Created by Puntar on 2/19/15.
 */
public interface AddMeasurementPresenter {

    void verifyData(String diameter);

    void setCurrentLogLength(LogLength logLength);

    void onResume();

}
