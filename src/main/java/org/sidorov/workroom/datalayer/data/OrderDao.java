package org.sidorov.workroom.datalayer.data;

import org.sidorov.workroom.datalayer.Order;
import org.sidorov.workroom.logic.OrderStatus;

import java.util.List;

public interface OrderDao {
    List<Order> getListOrders();

    List<Order> getListOrdersByCustomerId(int customerId, boolean isDeleted);

    List<Order> getListOrdersByMasterId(int masterId, OrderStatus status);

    List<Order> getListOrdersByStatus(int statusId);

    List<Order> getListOrdersByDeleted(boolean isDeleted);

    Order getOrderById(int orderId);

    void createOrder(Order order);

    void deleteOrderById(int orderId);

    void updateOrder(Order order);

    void setOrderStatus(OrderStatus status, int orderId);

    void setDeleted(boolean isDeleted, int orderId);

    void setOrderMaster(int orderId, int masterId);

    void setOrderCostWork(int orderId, int costWork);
}
