package org.sidorov.workroom.datalayer;

import org.sidorov.workroom.datalayer.data.OrderDao;
import org.sidorov.workroom.datalayer.data.OrderReplacementPartDao;
import org.sidorov.workroom.datalayer.data.ReplacementPartDao;
import org.sidorov.workroom.datalayer.data.UserDao;

public interface DaoFactory {
    OrderDao getOrderDao();

    OrderReplacementPartDao getOrderReplacementPartDao();

    ReplacementPartDao getReplacementPartDao();

    UserDao getUserDao();
}
