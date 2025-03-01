package org.sidorov.workroom.datalayer.postgredb;

import org.sidorov.workroom.datalayer.Order;
import org.sidorov.workroom.datalayer.data.OrderDao;
import org.sidorov.workroom.logic.OrderStatus;
import org.sidorov.workroom.resource.QueryManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PostgreOrderDao implements OrderDao {

    private static final Logger LOGGER = Logger.getLogger(PostgreOrderDao.class.getName());

    private final Connection connection;

    public PostgreOrderDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Order> getListOrders() {
        List<Order> orders = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(QueryManager.getProperty("query.order.allOrders"));
            while (resultSet.next()) {
                Order order = new Order();
                setOrder(order, resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
        return orders;
    }

    @Override
    public void deleteOrderById(int orderId) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.order.delete"))) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public void createOrder(Order order) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.order.create"))) {
            preparedStatement.setString(1, order.getThingName());
            preparedStatement.setString(2, order.getDescription());
            preparedStatement.setInt(3, order.getOrderStatus().getId());
            preparedStatement.setInt(4, order.getCustomerId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public List<Order> getListOrdersByCustomerId(int customerId, boolean isDeleted) {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.order.getOrdersByCustomerId"))) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, isDeleted ? 1 : 0);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                setOrder(order, resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
        return orders;
    }

    @Override
    public List<Order> getListOrdersByMasterId(int masterId, OrderStatus status) {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.order.getOrdersByMasterIdAndOrderStatus"))) {
            preparedStatement.setInt(1, masterId);
            preparedStatement.setInt(2, status.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                setOrder(order, resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
        return orders;
    }

    @Override
    public List<Order> getListOrdersByStatus(int statusId) {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.order.getOrdersByStatus"))) {
            preparedStatement.setInt(1, statusId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                setOrder(order, resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
        return orders;
    }


    @Override
    public List<Order> getListOrdersByDeleted(boolean isDeleted) {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.order.getOrdersByDeleted"))) {
            preparedStatement.setInt(1, isDeleted ? 1 : 0);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                setOrder(order, resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
        return orders;
    }

    @Override
    public Order getOrderById(int orderId) {
        Order order = null;
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.order.getOrderById"))) {
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order = new Order();
                setOrder(order, resultSet);
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
        return order;
    }

    @Override
    public void updateOrder(Order order) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.order.update"))) {
            preparedStatement.setString(1, order.getThingName());
            preparedStatement.setString(2, order.getDescription());
            preparedStatement.setInt(3, order.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public void setOrderStatus(OrderStatus status, int orderId) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.order.updateOrderStatus"))) {
            preparedStatement.setInt(1, status.getId());
            preparedStatement.setInt(2, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public void setDeleted(boolean isDeleted, int orderId) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.order.updateDeleted"))) {
            preparedStatement.setInt(1, isDeleted ? 1 : 0);
            preparedStatement.setInt(2, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public void setOrderMaster(int orderId, int masterId) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.order.updateMaster"))) {
            preparedStatement.setInt(1, masterId);
            preparedStatement.setInt(2, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public void setOrderCostWork(int orderId, int costWork) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.order.updateCostWork"))) {
            preparedStatement.setInt(1, costWork);
            preparedStatement.setInt(2, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    private void setOrder(Order order, ResultSet resultSet) throws SQLException {
        order.setId(resultSet.getInt(QueryManager.getProperty("column.order.id")));
        order.setThingName(resultSet.getString(QueryManager.getProperty("column.order.thingName")));
        order.setDescription(resultSet.getString(QueryManager.getProperty("column.order.description")));
        order.setOrderStatus(resultSet.getString(QueryManager.getProperty("column.order.orderStatus")));
        order.setCustomerName(resultSet.getString(QueryManager.getProperty("column.order.customerName")));
        order.setMasterName(resultSet.getString(QueryManager.getProperty("column.order.masterName")));
        order.setCustomerId(resultSet.getInt(QueryManager.getProperty("column.order.customerId")));
        order.setMasterId(resultSet.getInt(QueryManager.getProperty("column.order.masterId")));
        order.setCostWork(resultSet.getInt(QueryManager.getProperty("column.order.costWork")));
        order.setDeleted(resultSet.getInt(QueryManager.getProperty("column.order.deleted")) == 1);
    }
}
