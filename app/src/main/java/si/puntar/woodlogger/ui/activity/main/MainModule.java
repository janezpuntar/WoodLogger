package si.puntar.woodlogger.ui.activity.main;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Puntar on 2/17/15.
 */
@Module
public class MainModule {

    private final MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }

    @Provides MainView provideView() {
        return this.view;
    }

    @Provides MainPresenter providePresenter(MainView view) {
        return new MainPresenterImpl(view);
    }
}
