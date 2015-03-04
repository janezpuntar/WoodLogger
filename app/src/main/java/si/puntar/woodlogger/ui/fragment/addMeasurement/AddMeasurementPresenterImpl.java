package si.puntar.woodlogger.ui.fragment.addMeasurement;

import android.text.TextUtils;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import si.puntar.woodlogger.R;
import si.puntar.woodlogger.data.model.Log;
import si.puntar.woodlogger.data.model.LogLength;
import si.puntar.woodlogger.manager.rx.LogLengthManager;

/**
 * Created by Puntar on 2/19/15.
 */
public class AddMeasurementPresenterImpl implements AddMeasurementPresenter {

    private final AddMeasurementView view;
    private final LogLengthManager logLengthManager;

    private LogLength selectedLength;

    private Subscription getDefaultLogLength;

    @Inject
    public AddMeasurementPresenterImpl(AddMeasurementView view,
            LogLengthManager logLengthManager) {
        this.view = view;
        this.logLengthManager = logLengthManager;
    }

    @Override
    public void verifyData(String diameter) {
        if (!TextUtils.isEmpty(diameter)) {
            view.publishLog(new Log(selectedLength, Double.parseDouble(diameter)));
        } else {
            view.showAlert(R.string.insert_diameter);
        }
    }

    @Override
    public void setCurrentLogLength(LogLength logLength) {
        selectedLength = logLength;
    }

    @Override public void onResume() {
        if (getDefaultLogLength != null) {
            getDefaultLogLength.unsubscribe();
        }

        getDefaultLogLength = logLengthManager.getLogLength(new Observer<LogLength>() {
            @Override public void onCompleted() {

            }

            @Override public void onError(Throwable e) {

            }

            @Override public void onNext(LogLength logLength) {
                view.changeSelectedLogLength(logLength);
            }
        }, 1); // TODO

    }
}
