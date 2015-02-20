package si.puntar.woodlogger.data.helper;

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
@Singleton
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "woodlogger.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Log, Long> logDao;
    private Dao<Order, Long> orderDao;
    private Dao<LogLength, Long> logLengthDao;

    @Inject
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {

            TableUtils.createTable(connectionSource, Log.class);
            TableUtils.createTable(connectionSource, Order.class);
            TableUtils.createTable(connectionSource, LogLength.class);
        } catch (SQLException e) {
            Timber.e("Cannot create db tables.", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public Dao<Order, Long> getOrderDao() throws SQLException{
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
