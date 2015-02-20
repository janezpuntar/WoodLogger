package si.puntar.woodlogger.app;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import dagger.Provides;
import si.puntar.woodlogger.BuildConfig;
import timber.log.Timber;

/**
 * Created by Janez Puntar on 06/02/15.
 */
public interface AppGraph {

    void inject(App app);
}
