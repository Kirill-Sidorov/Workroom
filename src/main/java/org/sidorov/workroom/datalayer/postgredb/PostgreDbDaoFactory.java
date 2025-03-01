package org.sidorov.workroom.datalayer.postgredb;

import io.github.cdimascio.dotenv.Dotenv;
import org.sidorov.workroom.datalayer.DaoFactory;
import org.sidorov.workroom.datalayer.data.OrderDao;
import org.sidorov.workroom.datalayer.data.OrderReplacementPartDao;
import org.sidorov.workroom.datalayer.data.ReplacementPartDao;
import org.sidorov.workroom.datalayer.data.UserDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import static java.sql.Connection.TRANSACTION_SERIALIZABLE;

public class PostgreDbDaoFactory implements DaoFactory {

    private static final Logger LOGGER = Logger.getLogger(PostgreDbDaoFactory.class.getName());
    private static volatile PostgreDbDaoFactory instance;

    private final Connection connection;

    private PostgreDbDaoFactory() throws ClassNotFoundException, SQLException {

        ResourceBundle dbProperties = ResourceBundle.getBundle("db");

        String driverName = dbProperties.getString("jdbc.driver.drivername");
        String url = dbProperties.getString("jdbc.driver.url");

        Dotenv dotenv = Dotenv.configure().load();
        String user = dotenv.get("db_username");
        String password = dotenv.get("db_password");

        Class.forName(driverName);
        connection = DriverManager.getConnection(url, user, password);
        connection.setTransactionIsolation(TRANSACTION_SERIALIZABLE);
        connection.setAutoCommit(true);
        LOGGER.info("Connected to Postgres DB");
    }

    public static PostgreDbDaoFactory getInstance() throws ClassNotFoundException, SQLException {
        PostgreDbDaoFactory factory = instance;
        if (factory == null) {
            synchronized (PostgreDbDaoFactory.class) {
                factory = instance;
                if (factory == null) {
                    instance = factory = new PostgreDbDaoFactory();
                }
            }
        }
        return factory;
    }

    @Override
    public OrderDao getOrderDao() {
        return new PostgreOrderDao(connection);
    }

    @Override
    public OrderReplacementPartDao getOrderReplacementPartDao() {
        return new PostgreOrderReplacementPartDao(connection);
    }

    @Override
    public ReplacementPartDao getReplacementPartDao() {
        return new PostgreReplacementPartDao(connection);
    }

    @Override
    public UserDao getUserDao() {
        return new PostgreUserDao(connection);
    }
}
