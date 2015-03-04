package si.puntar.woodlogger.ui.activity.addMeasurement;

import java.util.List;

import si.puntar.woodlogger.data.model.Log;

/**
 * Created by Puntar on 2/19/15.
 */
public interface MeasurementPresenter {

    void saveMeasurement(String title, String description, List<Log> data);

    void onPause();
}
