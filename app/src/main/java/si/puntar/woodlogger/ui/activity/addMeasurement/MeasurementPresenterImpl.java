package si.puntar.woodlogger.ui.activity.addMeasurement;

import android.os.Bundle;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import si.puntar.woodlogger.R;
import si.puntar.woodlogger.data.model.Log;
import si.puntar.woodlogger.data.model.Order;
import si.puntar.woodlogger.manager.rx.LogManager;
import si.puntar.woodlogger.manager.rx.OrderManager;
import si.puntar.woodlogger.ui.activity.addMeasurement.error.EmptyFieldsException;

/**
 * Created by Puntar on 2/19/15.
 */
public class MeasurementPresenterImpl implements MeasurementPresenter {

    private final MeasurementView view;
    private final OrderManager orderManager;
    private final LogManager logManager;

    private Subscription saveOrder;
    private Subscription getOrder;
    private Subscription removeLog;

    private long orderId;
    private Order order;

    @Inject
    public MeasurementPresenterImpl(MeasurementView view,
                                    OrderManager orderManager,
                                    LogManager logManager) {
        this.view = view;
        this.orderManager = orderManager;
        this.logManager = logManager;
        order = new Order();


    }

    @Override
    public void onCreate(Bundle args) {
        if (args != null && args.containsKey(MeasurementActivity.ORDER_ID)) {
            orderId = args.getLong(MeasurementActivity.ORDER_ID);
        }

        if (orderId == 0) {
            view.setTitle(R.string.add_measurement_activity_title);
        } else {
            displayTotal(order.getTotalVolume());
        }
    }

    @Override
    public void saveMeasurement(String title, String description, List<Log> data) {
        order.setTitle(title);
        order.setDetails(description);
        order.setDateMeasured(new Date(System.currentTimeMillis()));

        if (saveOrder != null) {
            saveOrder.unsubscribe();
        }

        saveOrder = orderManager.saveOrder(new Observer<Order>() {
            @Override
            public void onCompleted() {
                order = null;
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof EmptyFieldsException) {
                    view.showMultipleAlerts(((EmptyFieldsException) e).getStringRes());
                } else {
                    view.showAlert(R.string.something_went_wrong);
                }
            }

            @Override
            public void onNext(Order order) {
                view.successfullySaved();
            }
        }, order, data);
    }

    @Override
    public void onPause() {
        if (saveOrder != null) {
            saveOrder.unsubscribe();
        }

        if (getOrder != null) {
            getOrder.unsubscribe();
        }

        if (removeLog != null) {
            removeLog.unsubscribe();
        }
    }

    @Override
    public void onResume() {

        if (orderId != 0) {
            if (getOrder != null) {
                getOrder.unsubscribe();
            }

            getOrder = orderManager.getOrder(new Observer<Order>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Order order) {
                    MeasurementPresenterImpl.this.order = order;
                    view.setOrder(order);
                    displayTotal(order.getTotalVolume());
                }
            }, orderId);
        }

    }

    @Override
    public void removeLog(long logId) {
        if (removeLog != null) {
            removeLog.unsubscribe();
        }

        removeLog = logManager.removeLog(new Observer<Log>() {
            @Override
            public void onCompleted() {
                view.logRemoved();
                displayTotal(order.getTotalVolume());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Log log) {
            }
        }, logId);
    }

    @Override
    public void displayTotal(double totalVolume) {
        if (orderId == 0) {
            view.setTitle(R.string.add_measurement_activity_title_measured, totalVolume);
        } else {
            view.setTitle(R.string.edit_measurement_activity_title_measured, totalVolume);
        }
    }

}
