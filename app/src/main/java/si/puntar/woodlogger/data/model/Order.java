package si.puntar.woodlogger.data.model;

import com.j256.ormlite.field.DatabaseField;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Puntar on 2/12/15.
 */
public class Order {

    private long orderId;
    private String title;
    private String details;
    private double lat;
    private double lng;
    private List<Log> measuredLogs;

    public Order() {
        measuredLogs = new ArrayList<>(5);
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
}
