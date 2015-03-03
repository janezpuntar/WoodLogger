package si.puntar.woodlogger.data.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Puntar on 2/12/15.
 */
@DatabaseTable(tableName = LogLength.NameHelper.TABLE_NAME)
public class LogLength {

    @DatabaseField(generatedId = true, columnName = NameHelper.LOG_LENGTH_ID)
    private long logLengthId;

    @DatabaseField(columnName = NameHelper.LOG_LENGTH)
    private double logLength;

    public LogLength() {
    }

    public LogLength(float logLength) {
        this.logLength = logLength;
    }

    public long getLogLengthId() {
        return logLengthId;
    }

    public double getLength() {
        return logLength;
    }

    public static class NameHelper {
        public static final String TABLE_NAME = "LogLength";
        public static final String LOG_LENGTH_ID = "logLengthId";
        public static final String LOG_LENGTH = "logLength";
    }
}
