package si.puntar.woodlogger.manager;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import si.puntar.woodlogger.data.DataModule;
import si.puntar.woodlogger.data.helper.DatabaseHelper;
import si.puntar.woodlogger.manager.db.LogDBManager;
import si.puntar.woodlogger.manager.db.LogLengthDBManager;
import si.puntar.woodlogger.manager.db.OrderDBManager;
import si.puntar.woodlogger.manager.rx.LogLengthManager;
import si.puntar.woodlogger.manager.rx.LogManager;
import si.puntar.woodlogger.manager.rx.OrderManager;

/**
 * Created by Puntar on 2/25/15.
 */
@Module(
        includes = DataModule.class
)
public class ManagerModule {

    @Provides
    LogDBManager provideLogDBManager(DatabaseHelper helper) {
        return new LogDBManager(helper);
    }

    @Provides
    LogLengthDBManager provideLogLengthDBManager(DatabaseHelper helper) {
        return new LogLengthDBManager(helper);
    }

    @Provides
    OrderDBManager provideOrderDBManager(DatabaseHelper helper) {
        return new OrderDBManager(helper);
    }

    @Provides
    LogLengthManager provideLogLengthManager(LogLengthDBManager lldbm) {
        return new LogLengthManager(lldbm);
    }

    @Provides
    LogManager provideLogManager(LogDBManager logDBManager) {
        return new LogManager(logDBManager);
    }

    @Provides
    OrderManager provideOrderManager(OrderDBManager orderDBManager, LogDBManager logDBManager) {
        return new OrderManager(orderDBManager, logDBManager);
    }
}
