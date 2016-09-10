package si.puntar.woodlogger.manager.rx;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import si.puntar.woodlogger.data.EndObserver;
import si.puntar.woodlogger.data.model.Log;
import si.puntar.woodlogger.data.model.Order;
import si.puntar.woodlogger.manager.db.LogDBManager;
import si.puntar.woodlogger.manager.db.OrderDBManager;

/**
 * Created by Puntar on 2/16/15.
 */
public class LogManager {


    private final LogDBManager logDBManager;


    private Map<String, PublishSubject<Log>> logOperation;

    @Inject
    public LogManager(LogDBManager logDBManager) {
        this.logDBManager = logDBManager;
        logOperation = new HashMap<>();
    }

    public Subscription removeLog(Observer<Log> observer, final long logId) {

        final String key = "removeById" + String.valueOf(logId);

        if (logOperation.containsKey(key)) {
            return logOperation.get(key).subscribe(observer);
        }

        PublishSubject<Log> logPublishSubject = PublishSubject.create();
        logOperation.put(key, logPublishSubject);
        Subscription subscription = logPublishSubject.subscribe(observer);

        logPublishSubject.subscribe(new EndObserver<Log>() {
            @Override
            public void onEnd() {
                logOperation.remove(key);
            }
        });

        Observable.create(new Observable.OnSubscribe<Log>() {
            @Override
            public void call(Subscriber<? super Log> subscriber) {
                try {
                    logDBManager.removeLog(logId);
                    subscriber.onCompleted();
                } catch (SQLException e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(logPublishSubject);

        return subscription;
    }
}
