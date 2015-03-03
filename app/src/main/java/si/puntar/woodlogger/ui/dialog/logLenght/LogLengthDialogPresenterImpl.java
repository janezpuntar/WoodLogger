package si.puntar.woodlogger.ui.dialog.logLenght;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import si.puntar.woodlogger.R;
import si.puntar.woodlogger.data.model.LogLength;
import si.puntar.woodlogger.manager.rx.LogLengthManager;

/**
 * Created by Puntar on 2/23/15.
 */
public class LogLengthDialogPresenterImpl implements LogLengthDialogPresenter {

    private final LogLengthDialogView view;
    private final LogLengthManager logLengthManager;

    private Subscription logLengths;

    @Inject
    public LogLengthDialogPresenterImpl(LogLengthDialogView view, LogLengthManager logLengthManager) {
        this.view = view;
        this.logLengthManager = logLengthManager;
    }

    @Override
    public void onResume() {
        if (logLengths != null) {
            logLengths.unsubscribe();
        }

        logLengths = logLengthManager.getLogLengths(new Observer<List<LogLength>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.showAlert(R.string.abc_action_bar_home_description);
            }

            @Override
            public void onNext(List<LogLength> list) {
                view.setData(list);
            }
        });
    }

    @Override
    public void onPause() {
        if (logLengths != null) {
            logLengths.unsubscribe();
        }
    }
}
