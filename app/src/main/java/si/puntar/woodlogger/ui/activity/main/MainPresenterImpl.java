package si.puntar.woodlogger.ui.activity.main;

import dagger.Provides;

/**
 * Created by Puntar on 2/17/15.
 */
public class MainPresenterImpl implements MainPresenter {

    private final MainView view;

    public MainPresenterImpl(MainView view) {
        this.view = view;
    }
}
