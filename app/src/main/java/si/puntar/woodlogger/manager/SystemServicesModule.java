package si.puntar.woodlogger.manager;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;
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
    ConnectivityManager provideConnectivityManager(Context application) {
        return (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

//    @Provides
//    NekClass provideDouble() {
//        return new NekClass(32);
//    }
}