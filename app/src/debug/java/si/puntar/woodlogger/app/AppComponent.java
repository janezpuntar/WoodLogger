package si.puntar.woodlogger.app;

import dagger.Component;

/**
 * Created by Puntar on 2/17/15.
 */
@ApplicationScope
@Component(modules = { AppModule.class })
public interface AppComponent extends DebugAppGraph {

}
