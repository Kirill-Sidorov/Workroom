package org.sidorov.workroom.datalayer.postgredb;

import org.sidorov.workroom.datalayer.OrderReplacementPart;
import org.sidorov.workroom.datalayer.data.OrderReplacementPartDao;
import org.sidorov.workroom.resource.QueryManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PostgreOrderReplacementPartDao implements OrderReplacementPartDao {

    private static final Logger LOGGER = Logger.getLogger(PostgreOrderReplacementPartDao.class.getName());
    
    private final Connection connection;

    public PostgreOrderReplacementPartDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<OrderReplacementPart> getPartsListByOrderId(int orderId) {
        List<OrderReplacementPart> parts = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.partsList.getPartsByOrderId"))) {
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderReplacementPart orderPart = new OrderReplacementPart();
                setOrderPart(orderPart, resultSet);
                parts.add(orderPart);
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
        return parts;
    }

    @Override
    public List<OrderReplacementPart> getOrderPartsWithAllParts(int orderId) {
        List<OrderReplacementPart> parts = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.partsList.getOrderPartsWithAllParts"))) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, orderId);
            preparedStatement.setInt(3, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderReplacementPart orderPart = new OrderReplacementPart();
                setOrderPart(orderPart, resultSet);
                orderPart.setPartName(resultSet.getString(QueryManager.getProperty("column.replacementPart.name")));
                orderPart.setCost(resultSet.getInt(QueryManager.getProperty("column.replacementPart.cost")));
                parts.add(orderPart);
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
        return parts;
    }

    @Override
    public List<OrderReplacementPart> getOrderPartsWithNumberPartsInStock(int orderId) {
        List<OrderReplacementPart> parts = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.partsList.getOrderPartsWithNumberPartsInStock"))) {
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderReplacementPart orderPart = new OrderReplacementPart();
                setOrderPart(orderPart, resultSet);
                orderPart.setPartName(resultSet.getString(QueryManager.getProperty("column.replacementPart.name")));
                orderPart.setInStock(resultSet.getInt(QueryManager.getProperty("column.replacementPart.inStock")));
                parts.add(orderPart);
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
        return parts;
    }

    @Override
    public void updateOrderPart(int orderId, int partId, int numberParts) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.partsList.updateOrderPart"))) {
            preparedStatement.setInt(1, numberParts);
            preparedStatement.setInt(2, orderId);
            preparedStatement.setInt(3, partId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public void createOrderPart(int orderId, int partId, int numberParts) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.partsList.createOrderPart"))) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, partId);
            preparedStatement.setInt(3, numberParts);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public void deleteOrderPart(int orderId, int partId) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.partsList.deleteOrderPart"))) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, partId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public List<OrderReplacementPart> getOrderPartsList(int orderId) {
        List<OrderReplacementPart> parts = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.partsList.getOrderPartsList"))) {
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderReplacementPart orderPart = new OrderReplacementPart();
                setOrderPart(orderPart, resultSet);
                orderPart.setPartName(resultSet.getString(QueryManager.getProperty("column.replacementPart.name")));
                orderPart.setCost(resultSet.getInt(QueryManager.getProperty("column.replacementPart.cost")));
                parts.add(orderPart);
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
        return parts;
    }

    private void setOrderPart(OrderReplacementPart orderPart, ResultSet resultSet) throws SQLException {
        orderPart.setOrderId(resultSet.getInt(QueryManager.getProperty("column.partsList.orderId")));
        orderPart.setPartId(resultSet.getInt(QueryManager.getProperty("column.partsList.replacementPart")));
        orderPart.setNumberParts(resultSet.getInt(QueryManager.getProperty("column.partsList.numberParts")));
    }
}
