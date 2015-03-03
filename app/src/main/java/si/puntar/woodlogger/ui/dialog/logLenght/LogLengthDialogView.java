package si.puntar.woodlogger.ui.dialog.logLenght;

import java.util.List;

import si.puntar.woodlogger.data.model.LogLength;
import si.puntar.woodlogger.ui.ViewFunctions;

/**
 * Created by Puntar on 2/23/15.
 */
public interface LogLengthDialogView extends ViewFunctions {

    void setData(List<LogLength> data);
}
