package si.puntar.woodlogger.manager.db;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import si.puntar.woodlogger.data.helper.DatabaseHelper;
import si.puntar.woodlogger.data.model.LogLength;

/**
 * Created by Puntar on 2/12/15.
 */
public class LogLengthDBManager {

    private final DatabaseHelper databaseHelper;

    @Inject
    public LogLengthDBManager(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public List<LogLength> getLogLengths() throws SQLException {
        Dao<LogLength, Long> logLengthDao = databaseHelper.getLogLengthDao();

        return logLengthDao.queryForAll();
    }

    public LogLength getLogLength() throws SQLException {
        Dao<LogLength, Long> logLengthDao = databaseHelper.getLogLengthDao();
        return logLengthDao.queryBuilder().queryForFirst();
    }
}
