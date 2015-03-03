package si.puntar.woodlogger.ui.dialog.logLenght;

import dagger.Component;
import si.puntar.woodlogger.app.AppComponent;
import si.puntar.woodlogger.app.WorkScope;

/**
 * Created by Puntar on 2/23/15.
 */
@WorkScope
@Component(
        dependencies = AppComponent.class,
        modules = LogLengthDialogModule.class
)
public interface LogLengthDialogComponent {
    void inject(LogLengthDialog dialog);
}
