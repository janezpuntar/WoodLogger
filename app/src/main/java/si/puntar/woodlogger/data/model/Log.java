package si.puntar.woodlogger.data.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.misc.BaseDaoEnabled;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by Puntar on 2/12/15.
 */
@DatabaseTable(tableName = Log.NameHelper.TABLE_NAME)
public class Log {

    @DatabaseField(generatedId = true, columnName = NameHelper.LOG_ID)
    private long logId;

    @DatabaseField(foreign = true,
            foreignAutoRefresh = true,
            columnName = NameHelper.LENGTH)
    private LogLength logLength;

    @DatabaseField(columnName = NameHelper.DIAMETER)
    private double diameter;

    @DatabaseField(columnName = NameHelper.DATE_INSERTED)
    private Date dateInserted;

    @DatabaseField(foreign = true,
            foreignAutoRefresh = true,
            columnName = Log.NameHelper.FK_ORDER)
    private Order order;

    public Log() {
    }

    public Log(LogLength length, double diameter) {
        this.logLength = length;
        this.diameter = diameter;
        this.dateInserted = new Date(System.currentTimeMillis());
    }

    public double getLength() {
        return logLength.getLength();
    }

    public double getDiameter() {
        return diameter;
    }

    public double getVolume() {
        return Math.PI * Math.pow((diameter / 200), 2) * logLength.getLength();
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public long getLogId() {
        return logId;
    }

    public static class NameHelper {
        public static final String TABLE_NAME = "Log";
        public static final String LOG_ID = "logId";
        public static final String LENGTH = "length";
        public static final String DIAMETER = "diameter";
        public static final String DATE_INSERTED = "dateInserted";
        public static final String FK_ORDER = "foreignOrder";
    }
}
