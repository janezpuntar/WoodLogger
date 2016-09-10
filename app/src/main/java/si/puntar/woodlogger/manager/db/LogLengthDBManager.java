package si.puntar.woodlogger.manager.db;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
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

        List<LogLength> data = logLengthDao.queryForAll();

        Collections.sort(data, new Comparator<LogLength>() {
            @Override
            public int compare(LogLength logLength, LogLength t1) {
                if (logLength.getLength() - t1.getLength() >= 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        return data;
    }

    public LogLength getLogLength() throws SQLException {
        Dao<LogLength, Long> logLengthDao = databaseHelper.getLogLengthDao();
        return logLengthDao.queryBuilder().queryForFirst();
    }
}
