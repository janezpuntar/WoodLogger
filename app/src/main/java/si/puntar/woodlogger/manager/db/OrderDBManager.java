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

    public int updateOrder(Order order) throws SQLException {
        Dao<Order, Long> orderDao = databaseHelper.getOrderDao();
        return orderDao.update(order);
    }

    public Order createOrder(Order order) throws SQLException {
        Dao<Order, Long> orderDao = databaseHelper.getOrderDao();
        return orderDao.createIfNotExists(order);
    }

    public void removeOrder(long orderId) throws SQLException {
        Dao<Order, Long> orderDao = databaseHelper.getOrderDao();
        orderDao.deleteById(orderId);
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
