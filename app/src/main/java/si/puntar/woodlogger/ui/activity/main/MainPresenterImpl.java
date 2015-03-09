package si.puntar.woodlogger.ui.activity.main;

import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observer;
import rx.Subscription;
import si.puntar.woodlogger.data.model.Order;
import si.puntar.woodlogger.manager.rx.OrderManager;

/**
 * Created by Puntar on 2/17/15.
 */
@Singleton
public class MainPresenterImpl implements MainPresenter {

    private final MainView view;
    private final OrderManager orderManager;

    private Subscription subAllOrders;



    @Inject
    public MainPresenterImpl(MainView view, OrderManager orderManager) {
        this.view = view;
        this.orderManager = orderManager;
    }

    @Override
    public void onResume() {

        if (subAllOrders != null) {
            subAllOrders.unsubscribe();
        }

        subAllOrders = orderManager.getOrders(new Observer<List<Order>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Order> orders) {
                view.addOrders(orders);
            }
        });
    }

    @Override
    public void onPause() {
        if (subAllOrders != null) {
            subAllOrders.unsubscribe();
        }
    }
}
