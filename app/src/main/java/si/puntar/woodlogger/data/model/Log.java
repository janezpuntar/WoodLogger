package si.puntar.woodlogger.data.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Puntar on 2/12/15.
 */
@DatabaseTable(tableName = Log.NameHelper.TABLE_NAME)
public class Log {

    @DatabaseField(generatedId = true, columnName = NameHelper.LOG_ID)
    private long logId;

    @DatabaseField(foreign = true,
            columnName = NameHelper.LENGTH)
    private LogLength logLength;

    @DatabaseField(columnName = NameHelper.DIAMETER)
    private double diameter;

    public Log() {
    }

    public Log(LogLength length, double diameter) {
        this.logLength = length;
        this.diameter = diameter;
    }

    public double getLength() {
        return logLength.getLength();
    }

    public double getDiameter() {
        return diameter;
    }

    public double getVolume() {
        return Math.PI * Math.pow((diameter / 2), 2) * logLength.getLength();
    }

    public static class NameHelper {
        public static final String TABLE_NAME = "Log";
        public static final String LOG_ID = "logId";
        public static final String LENGTH = "length";
        public static final String DIAMETER = "diameter";
    }
}
