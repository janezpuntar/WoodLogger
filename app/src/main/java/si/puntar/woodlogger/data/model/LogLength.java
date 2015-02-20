package si.puntar.woodlogger.data.model;

/**
 * Created by Puntar on 2/12/15.
 */
public class LogLength {

    private long logLengthId;
    private double logLength;

    public LogLength() { }

    public LogLength(long logLengthId, float logLength) {
        this.logLengthId = logLengthId;
        this.logLength = logLength;
    }

    public long getLogLengthId() {
        return logLengthId;
    }

    public double getLogLength() {
        return logLength;
    }
}
