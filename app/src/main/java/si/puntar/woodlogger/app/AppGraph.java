package si.puntar.woodlogger.app;

import si.puntar.woodlogger.ui.activity.base.AppContainer;

/**
 * Created by Janez Puntar on 06/02/15.
 */
public interface AppGraph {

    void inject(App app);

    AppContainer appContainer();
}
