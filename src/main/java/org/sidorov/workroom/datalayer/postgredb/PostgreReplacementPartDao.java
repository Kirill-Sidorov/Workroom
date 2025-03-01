package org.sidorov.workroom.datalayer.postgredb;

import org.sidorov.workroom.datalayer.ReplacementPart;
import org.sidorov.workroom.datalayer.data.ReplacementPartDao;
import org.sidorov.workroom.resource.QueryManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PostgreReplacementPartDao implements ReplacementPartDao {

    private static final Logger LOGGER = Logger.getLogger(PostgreReplacementPartDao.class.getName());
    
    private final Connection connection;

    public PostgreReplacementPartDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<ReplacementPart> getReplacementPartsList() {
        List<ReplacementPart> parts = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(QueryManager.getProperty("query.replacementPart.allParts"));
            while (resultSet.next()) {
                ReplacementPart part = new ReplacementPart();
                setReplacementPart(part, resultSet);
                parts.add(part);
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
        return parts;
    }

    @Override
    public ReplacementPart getPartById(int id) {
        ReplacementPart part = null;
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.replacementPart.getPartById"))) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                part = new ReplacementPart();
                setReplacementPart(part, resultSet);
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
        return part;
    }

    @Override
    public void updatePart(ReplacementPart part) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.replacementPart.update"))) {
            preparedStatement.setString(1, part.getName());
            preparedStatement.setInt(2, part.getInStock());
            preparedStatement.setInt(3, part.getCost());
            preparedStatement.setInt(4, part.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public void deletePart(int partId) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.replacementPart.delete"))) {
            preparedStatement.setInt(1, partId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public void createPart(ReplacementPart part) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.replacementPart.create"))) {
            preparedStatement.setString(1, part.getName());
            preparedStatement.setInt(2, part.getInStock());
            preparedStatement.setInt(3, part.getCost());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public void updateInStock(int partId, int inStock) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.replacementPart.updateInStock"))) {
            preparedStatement.setInt(1, inStock);
            preparedStatement.setInt(2, partId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    private void setReplacementPart(ReplacementPart part, ResultSet resultSet) throws SQLException {
        part.setId(resultSet.getInt(QueryManager.getProperty("column.replacementPart.id")));
        part.setName(resultSet.getString(QueryManager.getProperty("column.replacementPart.name")));
        part.setInStock(resultSet.getInt(QueryManager.getProperty("column.replacementPart.inStock")));
        part.setCost(resultSet.getInt(QueryManager.getProperty("column.replacementPart.cost")));
    }
}
