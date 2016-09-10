package si.puntar.woodlogger.ui.fragment.addMeasurement;

import si.puntar.woodlogger.data.model.Log;
import si.puntar.woodlogger.data.model.LogLength;
import si.puntar.woodlogger.ui.ViewFunctions;

/**
 * Created by Puntar on 2/19/15.
 */
public interface AddMeasurementView extends ViewFunctions {

    void publishLog(Log log);

    void changeSelectedLogLength(LogLength data);


}
