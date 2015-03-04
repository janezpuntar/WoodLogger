package si.puntar.woodlogger.data.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Puntar on 2/12/15.
 */
@DatabaseTable(tableName = Order.NameHelper.TABLE_NAME)
public class Order {

    @DatabaseField(generatedId = true, columnName = NameHelper.ORDER_ID)
    private long orderId;

    @DatabaseField(columnName = NameHelper.TITLE)
    private String title;

    @DatabaseField(columnName = NameHelper.DETAILS)
    private String details;

    @DatabaseField(columnName = NameHelper.LAT)
    private double lat;

    @DatabaseField(columnName = NameHelper.LNG)
    private double lng;

    @DatabaseField(columnName = NameHelper.DATE_MEASURED)
    private Date dateMeasured;

    @DatabaseField(foreign = true,
            foreignAutoCreate = true,
            foreignAutoRefresh = true,
            columnName = NameHelper.MEASURED_LOGS)
    private List<Log> measuredLogs;

    public Order() {
        measuredLogs = new ArrayList<>(5);
    }

    public long getOrderId() {
        return orderId;
    }

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public List<Log> getMeasuredLogs() {
        return measuredLogs;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setMeasuredLogs(List<Log> measuredLogs) {
        this.measuredLogs = measuredLogs;
    }

    public static class NameHelper {
        public static final String TABLE_NAME = "order";
        public static final String ORDER_ID = "orderId";
        public static final String TITLE = "title";
        public static final String DETAILS = "details";
        public static final String DATE_MEASURED = "dateMeasured";
        public static final String LAT = "lat";
        public static final String LNG = "lng";
        public static final String MEASURED_LOGS = "measured_logs";

    }
}
