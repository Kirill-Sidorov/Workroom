package org.sidorov.workroom.command.master;

import org.sidorov.workroom.command.ActionCommand;
import org.sidorov.workroom.command.result.Result;
import org.sidorov.workroom.datalayer.User;
import org.sidorov.workroom.datalayer.data.OrderDao;
import org.sidorov.workroom.helper.UserAccessHelper;
import org.sidorov.workroom.logic.OrderStatus;
import org.sidorov.workroom.logic.UserType;

import javax.servlet.http.HttpServletRequest;

public class TakeOrderCommand implements ActionCommand {

    @Override
    public Result execute(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        if (UserAccessHelper.checkUser(sessionUser, UserType.MASTER)) {
            int orderId = Integer.parseInt(request.getParameter("id"));
            OrderDao orderDao = (OrderDao) request.getServletContext().getAttribute("OrderDao");
            orderDao.setOrderStatus(OrderStatus.CREATION_PARTS_LIST, orderId);
            orderDao.setOrderMaster(orderId, sessionUser.getId());
        }
        return sessionUser.getUserType().getDefaultResult();
    }
}
