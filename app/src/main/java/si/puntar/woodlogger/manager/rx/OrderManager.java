package si.puntar.woodlogger.manager.rx;

import com.j256.ormlite.dao.Dao;

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
import si.puntar.woodlogger.data.model.Order;
import si.puntar.woodlogger.manager.db.OrderDBManager;

/**
 * Created by Puntar on 2/16/15.
 */
public class OrderManager {

    private final OrderDBManager orderDBManager;

    private PublishSubject<List<Order>> getOrders;
    private Map<String, PublishSubject<Order>> orderOperation;

    @Inject
    public OrderManager(OrderDBManager orderDBManager) {
        this.orderDBManager = orderDBManager;
        orderOperation = new HashMap<>();
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

    public Subscription saveOrder(Observer<Order> observer, final Order order) {

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

        Observable.create(new Observable.OnSubscribe<Order>() {
            @Override
            public void call(Subscriber<? super Order> subscriber) {

                try {
                    Dao.CreateOrUpdateStatus status = orderDBManager.createUpdateOrder(order);

                    if (status.getNumLinesChanged() > 0) {
                        subscriber.onNext(order);
                    }

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
}
