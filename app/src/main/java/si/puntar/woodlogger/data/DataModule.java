package si.puntar.woodlogger.data;

import android.app.Application;
import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import dagger.Module;
import dagger.Provides;
import si.puntar.woodlogger.data.helper.DatabaseHelper;

/**
 * Created by Puntar on 2/25/15.
 */
@Module
public class DataModule {

    @Provides
    DatabaseHelper provideDatabaseHelper(Context context) {
        return OpenHelperManager.getHelper(context, DatabaseHelper.class);
    }
}
