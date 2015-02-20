package si.puntar.woodlogger.data.model;

/**
 * Created by Puntar on 2/12/15.
 */
public class Log {

    private long logId;
    private double length;
    private double diameter;

    public Log() { }

    public Log(double length, double diameter) {
        this.length = length;
        this.diameter = diameter;
    }

    public double getLength() {
        return length;
    }

    public double getDiameter() {
        return diameter;
    }

    public double getVolume() {
        return Math.PI * Math.pow((diameter / 2), 2) * length;
    }

    public static class NameHelper {
        public static final String TABLE_NAME = "taLog";
        public static final String ID = "logId";
        public static final String LENGTH = "length";
        public static final String DIAMETER = "diameter";
    }
}
