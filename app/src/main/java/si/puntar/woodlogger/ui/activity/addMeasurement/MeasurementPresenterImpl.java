package si.puntar.woodlogger.ui.activity.addMeasurement;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import si.puntar.woodlogger.R;
import si.puntar.woodlogger.data.model.Log;
import si.puntar.woodlogger.data.model.Order;
import si.puntar.woodlogger.manager.rx.OrderManager;
import si.puntar.woodlogger.ui.activity.addMeasurement.error.EmptyFieldsException;

/**
 * Created by Puntar on 2/19/15.
 */
public class MeasurementPresenterImpl implements MeasurementPresenter {

    private final MeasurementView view;
    private final OrderManager orderManager;

    private Subscription saveOrder;

    private Order order;

    @Inject
    public MeasurementPresenterImpl(MeasurementView view, OrderManager orderManager) {
        this.view = view;
        this.orderManager = orderManager;
        order = new Order();
    }

    @Override
    public void saveMeasurement(String title, String description, List<Log> data) {
        order.setTitle(title);
        order.setDetails(description);
        order.setMeasuredLogs(data);

        if (saveOrder != null) {
            saveOrder.unsubscribe();
        }

        saveOrder = orderManager.saveOrder(new Observer<Order>() {
            @Override
            public void onCompleted() {

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
        }, order);
    }

    @Override
    public void onPause() {
        if (saveOrder != null) {
            saveOrder.unsubscribe();
        }
    }
}
