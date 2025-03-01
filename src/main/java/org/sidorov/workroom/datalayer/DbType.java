package org.sidorov.workroom.datalayer;

import org.sidorov.workroom.datalayer.postgredb.PostgreDbDaoFactory;

import java.sql.SQLException;

public enum DbType {
    POSTGRE {
        @Override
        public DaoFactory getDaoFactory() {
            DaoFactory postgreDbDaoFactory = null;
            try {
                postgreDbDaoFactory = PostgreDbDaoFactory.getInstance();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            return postgreDbDaoFactory;
        }
    };

    public abstract DaoFactory getDaoFactory();
}
