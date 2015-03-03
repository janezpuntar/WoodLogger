package si.puntar.woodlogger.ui.fragment.addMeasurement;

import si.puntar.woodlogger.data.model.Log;
import si.puntar.woodlogger.data.model.LogLength;

/**
 * Created by Puntar on 2/19/15.
 */
public interface AddMeasurementView {

    void publishLog(Log log);

    void changeSelectedLogLength(LogLength data);


}
