package si.puntar.woodlogger.manager.db;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import si.puntar.woodlogger.data.helper.DatabaseHelper;
import si.puntar.woodlogger.data.model.Log;
import si.puntar.woodlogger.data.model.Order;

/**
 * Created by Puntar on 2/12/15.
 */
public class OrderDBManager {

    private final DatabaseHelper databaseHelper;

    @Inject
    public OrderDBManager(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public Log getLogById(long logId) throws SQLException {
        Dao<Log, Long> logDao = databaseHelper.getLogDao();
        return logDao.queryForId(logId);
    }

    public void assignEmptyCollection(Order order) throws SQLException {
        databaseHelper.getOrderDao()
                .assignEmptyForeignCollection(order, Order.NameHelper.MEASURED_LOGS);
    }

    public Dao.CreateOrUpdateStatus createUpdateOrder(Order order) throws SQLException {
        Dao<Order, Long> orderDao = databaseHelper.getOrderDao();

        android.util.Log.e("fdsa", "" + order.getOrderId());
        Dao.CreateOrUpdateStatus status = orderDao.createOrUpdate(order);
        android.util.Log.e("fdsa", "" + order.getOrderId());

        for (Log log : order.getMeasuredLogs()) {
            log.setOrder(order);
            databaseHelper.getLogDao().createOrUpdate(log);
        }

        Order newOrder = databaseHelper.getOrderDao().queryForId(10l);
        List<Log> logList = databaseHelper.getLogDao().queryBuilder().where().eq(Log.NameHelper.FK_ORDER, 4).query();

        return status;
    }

    public List<Order> getOrderList() throws SQLException {
        Dao<Order, Long> orderDao = databaseHelper.getOrderDao();

        return orderDao.queryBuilder()
                .orderBy(Order.NameHelper.DATE_MEASURED, false)
                .query();
    }

    public Order getOrderById(long orderId) throws SQLException {
        Dao<Order, Long> orderDao = databaseHelper.getOrderDao();
        return orderDao.queryForId(orderId);
    }

}
