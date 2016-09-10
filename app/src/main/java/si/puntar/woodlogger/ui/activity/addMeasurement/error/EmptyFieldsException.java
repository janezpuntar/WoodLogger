package si.puntar.woodlogger.ui.activity.addMeasurement.error;

import android.support.annotation.StringRes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Puntar on 3/4/15.
 */
public class EmptyFieldsException extends Throwable {

    private List<Integer> stringRes;

    public EmptyFieldsException() {
        stringRes = new ArrayList<>();
    }

    public void addStringMessage(@StringRes int stringId) {
        stringRes.add(stringId);
    }

    public boolean isException() {
        return stringRes.size() > 0;
    }

    public List<Integer> getStringRes() {
        return stringRes;
    }
}
