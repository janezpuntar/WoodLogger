package si.puntar.woodlogger.manager.rx;

import android.text.TextUtils;

import com.j256.ormlite.dao.Dao;

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
import si.puntar.woodlogger.R;
import si.puntar.woodlogger.data.EndObserver;
import si.puntar.woodlogger.data.model.Log;
import si.puntar.woodlogger.data.model.Order;
import si.puntar.woodlogger.manager.db.LogDBManager;
import si.puntar.woodlogger.manager.db.OrderDBManager;
import si.puntar.woodlogger.ui.activity.addMeasurement.error.EmptyFieldsException;
import timber.log.Timber;

/**
 * Created by Puntar on 2/16/15.
 */
public class OrderManager {

    private final OrderDBManager orderDBManager;
    private final LogDBManager logDBManager;

    private PublishSubject<List<Order>> getOrders;
    private Map<String, PublishSubject<Order>> orderOperation;

    @Inject
    public OrderManager(OrderDBManager orderDBManager, LogDBManager logDBManager) {
        this.orderDBManager = orderDBManager;
        this.logDBManager = logDBManager;
        orderOperation = new HashMap<>();
    }

    public void assignEmptyCollection(Order order) {

        try {
            orderDBManager.assignEmptyCollection(order);
            Timber.e("assign empty collection");
        } catch (SQLException e) {
            Timber.e("Cannot assign empty collection", e);
        }
    }

    public Subscription getOrders(Observer<List<Order>> observer) {

        if (getOrders != null) {
            return getOrders.subscribe(observer);
        }

        getOrders = PublishSubject.create();
        getOrders.subscribe(new EndObserver<List<Order>>() {
            @Override
            public void onEnd() {
                getOrders = null;
            }
        });

        Subscription subscription = getOrders.subscribe(observer);

        Observable.create(new Observable.OnSubscribe<List<Order>>() {
            @Override
            public void call(Subscriber<? super List<Order>> subscriber) {

                try {
                    subscriber.onNext(orderDBManager.getOrderList());
                    subscriber.onCompleted();
                } catch (SQLException e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getOrders);

        return subscription;
    }

    public Subscription removeOrder(Observer<Order> observer, final long orderId) {

        final String key = "removeById" + String.valueOf(orderId);

        if (orderOperation.containsKey(key)) {
            return orderOperation.get(key).subscribe(observer);
        }

        PublishSubject<Order> orderPublishSubject = PublishSubject.create();
        orderOperation.put(key, orderPublishSubject);
        Subscription subscription = orderPublishSubject.subscribe(observer);

        orderPublishSubject.subscribe(new EndObserver<Order>() {
            @Override
            public void onEnd() {
                orderOperation.remove(key);
            }
        });

        Observable.create(new Observable.OnSubscribe<Order>() {
            @Override
            public void call(Subscriber<? super Order> subscriber) {
                try {
                    orderDBManager.removeOrder(orderId);
                    subscriber.onCompleted();
                } catch (SQLException e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(orderPublishSubject);

        return subscription;
    }

    public Subscription getOrder(Observer<Order> observer, final long orderId) {

        final String key = "byId" + String.valueOf(orderId);

        if (orderOperation.containsKey(key)) {
            return orderOperation.get(key).subscribe(observer);
        }

        PublishSubject<Order> orderPublishSubject = PublishSubject.create();
        orderOperation.put(key, orderPublishSubject);
        Subscription subscription = orderPublishSubject.subscribe(observer);

        orderPublishSubject.subscribe(new EndObserver<Order>() {
            @Override
            public void onEnd() {
                orderOperation.remove(key);
            }
        });

        Observable.create(new Observable.OnSubscribe<Order>() {
            @Override
            public void call(Subscriber<? super Order> subscriber) {

                try {
                    subscriber.onNext(orderDBManager.getOrderById(orderId));
                    subscriber.onCompleted();
                } catch (SQLException e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(orderPublishSubject);

        return subscription;
    }

    public Subscription saveOrder(Observer<Order> observer, final Order order,
                                  final List<Log> logList) {

        final String key = "save" + String.valueOf(order.getOrderId());

        if (orderOperation.containsKey(key)) {
            return orderOperation.get(key).subscribe(observer);
        }

        PublishSubject<Order> orderPublishSubject = PublishSubject.create();
        orderOperation.put(key, orderPublishSubject);
        Subscription subscription = orderPublishSubject.subscribe(observer);

        orderPublishSubject.subscribe(new EndObserver<Order>() {
            @Override
            public void onEnd() {
                orderOperation.remove(key);
            }
        });

        Observable.create(
                new Observable.OnSubscribe<Order>() {
                    @Override
                    public void call(Subscriber<? super Order> subscriber) {

                        try {

                            EmptyFieldsException exception = new EmptyFieldsException();

                            if (TextUtils.isEmpty(order.getTitle())) {
                                exception.addStringMessage(R.string.add_title);
                            }

                            if (TextUtils.isEmpty(order.getDetails())) {
                                exception.addStringMessage(R.string.add_description);
                            }

                            if (exception.isException()) {
                                subscriber.onError(exception);
                            }

                            if (order.getOrderId() == 0) {
                                Order createdOrder = orderDBManager.createOrder(order);
                                for (Log log : logList) {
                                    log.setOrder(createdOrder);
                                    logDBManager.createLog(log);
                                }

                                subscriber.onNext(createdOrder);

                            } else {
                                orderDBManager.updateOrder(order);

                                for (Log item : logList) {

                                    if (!order.getMeasuredLogs().contains(item)) {
                                        item.setOrder(order);
                                        logDBManager.createLog(item);
                                    }
                                }
                                subscriber.onNext(order);
                            }
                            subscriber.onCompleted();
                        } catch (SQLException e) {
                            subscriber.onError(e);
                            Timber.e("Cannot save order - saveOrder()", e);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(orderPublishSubject);

        return subscription;
    }
}
