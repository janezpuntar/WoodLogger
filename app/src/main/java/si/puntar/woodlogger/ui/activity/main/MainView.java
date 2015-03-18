package si.puntar.woodlogger.ui.activity.main;

import java.util.List;

import si.puntar.woodlogger.data.model.Order;

/**
 * Created by Puntar on 2/17/15.
 */
public interface MainView {

    void addOrders(List<Order> item);

    void orderRemoved();

    void setEmptyOrderListHint(boolean visibility);

}
