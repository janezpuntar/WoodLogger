package si.puntar.woodlogger.ui.activity.main;

import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;
import javax.inject.Singleton;

import si.puntar.woodlogger.manager.NekClass;
import si.puntar.woodlogger.manager.rx.OrderManager;

/**
 * Created by Puntar on 2/17/15.
 */
@Singleton
public class MainPresenterImpl implements MainPresenter {

    private final MainView view;

    @Inject
    public MainPresenterImpl(MainView view, ConnectivityManager nekClass) {
        this.view = view;
    }

    @Override
    public void neki() {
        Log.e("fdsa", "dsdsdssd");
    }
}
