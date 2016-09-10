package si.puntar.woodlogger.ui.activity.main;

import android.net.ConnectivityManager;

import dagger.Module;
import dagger.Provides;
import si.puntar.woodlogger.manager.rx.OrderManager;

/**
 * Created by Puntar on 2/17/15.
 */
@Module
public class MainModule {

    private final MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }

    @Provides
    MainView provideView() {
        return this.view;
    }

    @Provides
    MainPresenter providePresenter(MainView view, OrderManager orderManager) {
        return new MainPresenterImpl(view, orderManager);
    }
}
