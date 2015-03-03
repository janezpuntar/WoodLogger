package si.puntar.woodlogger.ui.dialog.logLenght;

import dagger.Module;
import dagger.Provides;
import si.puntar.woodlogger.manager.rx.LogLengthManager;

/**
 * Created by Puntar on 2/23/15.
 */
@Module
public class LogLengthDialogModule {

    private final LogLengthDialogView view;

    public LogLengthDialogModule(LogLengthDialogView view) {
        this.view = view;
    }

    @Provides
    LogLengthDialogView provideView() {
        return view;
    }

    @Provides
    LogLengthDialogPresenter providePresenter(LogLengthDialogView view,
                                              LogLengthManager logLengthManager) {
        return new LogLengthDialogPresenterImpl(view, logLengthManager);
    }
}
