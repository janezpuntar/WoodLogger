package si.puntar.woodlogger.app;

import android.view.inputmethod.InputMethodManager;

import si.puntar.woodlogger.manager.rx.LogLengthManager;
import si.puntar.woodlogger.manager.rx.LogManager;
import si.puntar.woodlogger.manager.rx.OrderManager;
import si.puntar.woodlogger.ui.activity.base.AppContainer;

/**
 * Created by Janez Puntar on 06/02/15.
 */
public interface AppGraph {

    void inject(App app);
    AppContainer appContainer();
    InputMethodManager getInputMethodManager();
    OrderManager getOrderManager();
    LogManager getLogManager();
    LogLengthManager getLogLengthManager();

}
