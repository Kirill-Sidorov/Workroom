package org.sidorov.workroom.datalayer.postgredb;

import org.sidorov.workroom.datalayer.User;
import org.sidorov.workroom.datalayer.data.UserDao;
import org.sidorov.workroom.resource.QueryManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PostgreUserDao implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(PostgreUserDao.class.getName());
    
    private final Connection connection;

    public PostgreUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createUser(User user) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.user.create"))) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getUserType().getId());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.setString(6, user.getPhone());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public List<User> getListUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(QueryManager.getProperty("query.user.allUsers"));
            while (resultSet.next()) {
                User user = new User();
                setUser(user, resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
        return users;
    }

    @Override
    public List<User> getListUsersByStatus(String status) {
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.user.getListUsersByStatus"))) {
            preparedStatement.setString(1, status);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                setUser(user, resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
        return users;
    }

    @Override
    public User getUserByLogin(String login) {
        User user = null;
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.user.getUserByLogin"))) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                setUser(user, resultSet);
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
        return user;
    }

    @Override
    public void setAuthorized(boolean isAuthorized, int userId) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.user.setAuthorized"))) {
            preparedStatement.setInt(1, isAuthorized ? 1 : 0);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public void setStatus(String status, int userId) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.user.setStatus"))) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public void deleteUserById(int userId) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.user.delete"))) {
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public User getUserById(int userId) {
        User user = null;
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.user.getUserById"))) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                setUser(user, resultSet);
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(QueryManager.getProperty("query.user.update"))) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setInt(5, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    private void setUser(User user, ResultSet resultSet) throws SQLException {
        user.setId(resultSet.getInt(QueryManager.getProperty("column.user.id")));
        user.setLogin(resultSet.getString(QueryManager.getProperty("column.user.login")));
        user.setPassword(resultSet.getString(QueryManager.getProperty("column.user.password")));
        user.setStatus(resultSet.getString(QueryManager.getProperty("column.user.status")));
        user.setAuthorized(resultSet.getInt(QueryManager.getProperty("column.user.authorized")) == 1);
        user.setUserType(resultSet.getString(QueryManager.getProperty("column.user.userType")));
        user.setFirstName(resultSet.getString(QueryManager.getProperty("column.user.firstName")));
        user.setLastName(resultSet.getString(QueryManager.getProperty("column.user.lastName")));
        user.setPhone(resultSet.getString(QueryManager.getProperty("column.user.phone")));
    }
}
