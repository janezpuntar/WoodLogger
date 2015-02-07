package si.puntar.woodlogger;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import timber.log.Timber;

/**
 * Created by Janez Puntar on 06/02/15.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Crashlytics.start(this);
        }
    }
}
