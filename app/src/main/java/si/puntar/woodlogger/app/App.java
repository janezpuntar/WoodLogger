package si.puntar.woodlogger.app;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;

import si.puntar.woodlogger.BuildConfig;
import timber.log.Timber;

/**
 * Created by Janez Puntar on 06/02/15.
 */
public class App extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Crashlytics.start(this);
        }

        buildComponentAndInject();
    }

    public void buildComponentAndInject() {
        component = Dagger_AppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        component.inject(this);
    }

    public AppComponent getComponent() {
        return component;
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }
}
