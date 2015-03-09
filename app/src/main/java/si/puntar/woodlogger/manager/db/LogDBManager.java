package si.puntar.woodlogger.manager.db;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import si.puntar.woodlogger.data.helper.DatabaseHelper;
import si.puntar.woodlogger.data.model.Log;
import si.puntar.woodlogger.data.model.Order;

/**
 * Created by Puntar on 2/12/15.
 */
public class LogDBManager {

    private final DatabaseHelper databaseHelper;

    @Inject
    public LogDBManager(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public Log getLogById(long logId) throws SQLException {
        Dao<Log, Long> logDao = databaseHelper.getLogDao();
        return logDao.queryForId(logId);
    }

    public List<Log> getLogs() throws SQLException {
        Dao<Log, Long> logDao = databaseHelper.getLogDao();
        return logDao.queryForAll();
    }

    public void saveLog(Log log) throws SQLException {

        Dao<Log, Long> logDao = databaseHelper.getLogDao();

        android.util.Log.e("fdsa", "" + log.getLogId());
        logDao.create(log);
        android.util.Log.e("fdsa", "" + log.getLogId());

    }
}
