package org.sidorov.workroom.command.master;

import org.sidorov.workroom.command.ActionCommand;
import org.sidorov.workroom.command.result.ForwardResult;
import org.sidorov.workroom.command.result.Result;
import org.sidorov.workroom.datalayer.Order;
import org.sidorov.workroom.datalayer.User;
import org.sidorov.workroom.datalayer.data.OrderDao;
import org.sidorov.workroom.helper.UserAccessHelper;
import org.sidorov.workroom.logic.OrderStatus;
import org.sidorov.workroom.logic.UserType;
import org.sidorov.workroom.resource.AppManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowMasterMainPageCommand implements ActionCommand {

    private final Result result = new ForwardResult(AppManager.getProperty("page.masterMain"));

    @Override
    public Result execute(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        if (UserAccessHelper.checkUser(sessionUser, false, UserType.MASTER)) {
            OrderDao orderDao = (OrderDao) request.getServletContext().getAttribute("OrderDao");
            request.setAttribute("availableOrders", orderDao.getListOrdersByStatus(OrderStatus.WAIT_MASTER.getId()));
            List<Order> orders = orderDao.getListOrdersByMasterId(sessionUser.getId(), OrderStatus.WAIT_REPLACEMENT_PARTS);
            orders.addAll(orderDao.getListOrdersByMasterId(sessionUser.getId(), OrderStatus.CREATION_PARTS_LIST));
            orders.addAll(orderDao.getListOrdersByMasterId(sessionUser.getId(), OrderStatus.REPAIR_PROCESS));
            request.setAttribute("uncompletedOrders", orders);
            request.setAttribute("completedOrders", orderDao.getListOrdersByMasterId(sessionUser.getId(), OrderStatus.COMPLETED));
            return result;
        }
        return sessionUser.getUserType().getDefaultResult();
    }
}
