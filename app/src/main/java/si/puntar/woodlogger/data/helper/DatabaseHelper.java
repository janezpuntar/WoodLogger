package si.puntar.woodlogger.data.helper;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Singleton;

import si.puntar.woodlogger.data.model.Log;
import si.puntar.woodlogger.data.model.LogLength;
import si.puntar.woodlogger.data.model.Order;
import timber.log.Timber;

/**
 * Created by Puntar on 2/12/15.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "woodlogger.db";
    private static final int DATABASE_VERSION = 2;

    private Dao<Log, Long> logDao;
    private Dao<Order, Long> orderDao;
    private Dao<LogLength, Long> logLengthDao;

    @Inject
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database,
                         ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, LogLength.class);
            TableUtils.createTable(connectionSource, Order.class);
            TableUtils.createTable(connectionSource, Log.class);
        } catch (Exception e) {
            Timber.e("Cannot create db tables.", e);
        }

        try {
            Dao<LogLength, Long> logLengthDao = getLogLengthDao();

            logLengthDao.create(new LogLength(4));
            logLengthDao.create(new LogLength(5));
            logLengthDao.create(new LogLength(6));
            logLengthDao.create(new LogLength(7));
            logLengthDao.create(new LogLength(8));
            logLengthDao.create(new LogLength(9));
            logLengthDao.create(new LogLength(10));
            logLengthDao.create(new LogLength(11));
            logLengthDao.create(new LogLength(12));
            logLengthDao.create(new LogLength(13));
            logLengthDao.create(new LogLength(14));
            logLengthDao.create(new LogLength(15));
            logLengthDao.create(new LogLength(16));
            logLengthDao.create(new LogLength(17));
            logLengthDao.create(new LogLength(18));
            logLengthDao.create(new LogLength(19));
            logLengthDao.create(new LogLength(20));
        } catch (SQLException e) {
            Timber.e("Cannot add default lengths.", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {

        if (newVersion == 2) {
            try {
                Dao<LogLength, Long> logLengthDao = getLogLengthDao();

                logLengthDao.create(new LogLength(7));
                logLengthDao.create(new LogLength(9));
                logLengthDao.create(new LogLength(11));
                logLengthDao.create(new LogLength(13));
                logLengthDao.create(new LogLength(15));
                logLengthDao.create(new LogLength(17));
                logLengthDao.create(new LogLength(18));
                logLengthDao.create(new LogLength(19));
                logLengthDao.create(new LogLength(20));

            } catch (SQLException e) {
                Timber.e("Cannot add default lengths.", e);
            }
        }
    }

    public Dao<Order, Long> getOrderDao() throws SQLException {
        if (orderDao == null) {
            orderDao = getDao(Order.class);
        }
        return orderDao;
    }

    public Dao<Log, Long> getLogDao() throws SQLException {
        if (logDao == null) {
            logDao = getDao(Log.class);
        }
        return logDao;
    }

    public Dao<LogLength, Long> getLogLengthDao() throws SQLException {
        if (logLengthDao == null) {
            logLengthDao = getDao(LogLength.class);
        }
        return logLengthDao;
    }
}
