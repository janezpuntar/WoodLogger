package si.puntar.woodlogger.manager.rx;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import si.puntar.woodlogger.data.EndObserver;
import si.puntar.woodlogger.data.model.LogLength;
import si.puntar.woodlogger.data.model.Order;
import si.puntar.woodlogger.manager.db.LogLengthDBManager;

/**
 * Created by Puntar on 2/16/15.
 */
public class LogLengthManager {

    private final LogLengthDBManager logLengthDBManager;

    private PublishSubject<List<LogLength>> getLogLengths;
    private Map<String, PublishSubject<LogLength>> logLengthPS;

    @Inject
    public LogLengthManager(LogLengthDBManager logLengthDBManager) {
        this.logLengthDBManager = logLengthDBManager;
        logLengthPS = new HashMap<>();
    }

    public Subscription getLogLengths(Observer<List<LogLength>> observer) {

        if (getLogLengths != null) {
            return getLogLengths.subscribe(observer);
        }

        getLogLengths = PublishSubject.create();
        getLogLengths.subscribe(new EndObserver<List<LogLength>>() {
            @Override
            public void onEnd() {
                getLogLengths = null;
            }
        });

        Subscription subscription = getLogLengths.subscribe(observer);

        Observable.create(new Observable.OnSubscribe<List<LogLength>>() {
            @Override
            public void call(Subscriber<? super List<LogLength>> subscriber) {

                try {
                    subscriber.onNext(logLengthDBManager.getLogLengths());
                    subscriber.onCompleted();
                } catch (SQLException e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getLogLengths);

        return subscription;
    }

    public Subscription getLogLength(Observer<LogLength> observer, final long logLenghtId) {

        final String key = "byId" + String.valueOf(logLenghtId);

        if (logLengthPS.containsKey(key)) {
            return logLengthPS.get(key).subscribe(observer);
        }

        PublishSubject<LogLength> logLengthPublishSubject = PublishSubject.create();
        logLengthPS.put(key, logLengthPublishSubject);
        Subscription subscription = logLengthPublishSubject.subscribe(observer);

        logLengthPublishSubject.subscribe(new EndObserver<LogLength>() {
            @Override
            public void onEnd() {
                logLengthPS.remove(key);
            }
        });

        Observable.create(new Observable.OnSubscribe<LogLength>() {
            @Override
            public void call(Subscriber<? super LogLength> subscriber) {

                try {
                    subscriber.onNext(logLengthDBManager.getLogLength());
                    subscriber.onCompleted();
                } catch (SQLException e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(logLengthPublishSubject);

        return subscription;
    }


}
