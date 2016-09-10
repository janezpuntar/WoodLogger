package si.puntar.woodlogger.ui.activity.addMeasurement;

import android.os.Bundle;

import java.util.List;

import si.puntar.woodlogger.data.model.Log;

/**
 * Created by Puntar on 2/19/15.
 */
public interface MeasurementPresenter {

    void onCreate(Bundle args);

    void saveMeasurement(String title, String description, List<Log> data);

    void onPause();

    void onResume();

    void removeLog(long logId);

    void displayTotal(double totalVolume);
}
