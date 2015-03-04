package si.puntar.woodlogger.ui.activity.addMeasurement;

import java.util.List;

import si.puntar.woodlogger.ui.ViewFunctions;

/**
 * Created by Puntar on 2/19/15.
 */
public interface MeasurementView extends ViewFunctions {

    void successfullySaved();

    void showMultipleAlerts(List<Integer> alerts);

}
