package si.puntar.woodlogger.ui.activity.addMeasurement;

import android.support.annotation.StringRes;

import java.util.List;

import si.puntar.woodlogger.data.model.Order;
import si.puntar.woodlogger.ui.ViewFunctions;

/**
 * Created by Puntar on 2/19/15.
 */
public interface MeasurementView extends ViewFunctions {

    void setTitle(@StringRes int title);

    void successfullySaved();

    void showMultipleAlerts(List<Integer> alerts);

    void setOrder(Order order);

    void logRemoved();

}
