package si.puntar.woodlogger.data.model;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.misc.BaseDaoEnabled;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import timber.log.Timber;

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

    @ForeignCollectionField(eager = true,

            columnName = NameHelper.MEASURED_LOGS)
    private ForeignCollection<Log> measuredLogs;

    public Order() {

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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setMeasuredLogs(List<Log> measuredLog) {
        measuredLogs.addAll(measuredLog);
    }

    public ForeignCollection<Log> getMeasuredLogs() {
        return measuredLogs;
    }

    public Date getDateMeasured() {
        return dateMeasured;
    }

    public void setDateMeasured(Date dateMeasured) {
        this.dateMeasured = dateMeasured;
    }

    public double getTotalVolume() {

        double sum = 0;

        CloseableIterator<Log> iterator = measuredLogs.closeableIterator();
        try {
            while (iterator.hasNext()) {
                Log item = iterator.next();
                sum += item.getVolume();
            }
        } finally {
            try {
                iterator.close();
            } catch (SQLException e) {
                Timber.e("");
            }
        }

        return sum;
    }

    public static class NameHelper {
        public static final String TABLE_NAME = "order";
        public static final String ORDER_ID = "orderId";
        public static final String TITLE = "title";
        public static final String DETAILS = "details";
        public static final String DATE_MEASURED = "dateMeasured";
        public static final String LAT = "lat";
        public static final String LNG = "lng";
        public static final String MEASURED_LOGS = "measuredLogs";

    }
}
