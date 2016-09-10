package si.puntar.woodlogger.manager;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;
import android.view.inputmethod.InputMethodManager;

import dagger.Module;
import dagger.Provides;

import javax.inject.Inject;
import javax.inject.Singleton;

@Module
public class SystemServicesModule {

    @Provides
    SharedPreferences providePreferenceManager(Context application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    LocationManager provideLocationManager(Context context) {
        return (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    @Provides
    InputMethodManager proInputMethodManager(Context context) {
        return (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }



}