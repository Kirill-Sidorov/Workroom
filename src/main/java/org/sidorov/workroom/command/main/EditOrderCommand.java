package org.sidorov.workroom.command.main;

import org.sidorov.workroom.command.ActionCommand;
import org.sidorov.workroom.command.result.Result;
import org.sidorov.workroom.datalayer.Order;
import org.sidorov.workroom.datalayer.User;
import org.sidorov.workroom.datalayer.data.OrderDao;
import org.sidorov.workroom.helper.UserAccessHelper;
import org.sidorov.workroom.logic.OrderStatus;
import org.sidorov.workroom.logic.UserType;

import javax.servlet.http.HttpServletRequest;

public class EditOrderCommand implements ActionCommand {

    @Override
    public Result execute(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        if (UserAccessHelper.checkUser(sessionUser, UserType.CUSTOMER, UserType.MODERATOR)) {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            Order order = new Order();

            order.setId(orderId);
            order.setThingName(request.getParameter("thingName"));
            order.setDescription(request.getParameter("description"));

            OrderDao orderDao = (OrderDao) request.getServletContext().getAttribute("OrderDao");
            orderDao.updateOrder(order);

            if (UserType.MODERATOR.equals(sessionUser.getUserType())) {
                orderDao.setOrderStatus(OrderStatus.WAIT_MASTER, orderId);
            }
        }
        return sessionUser.getUserType().getDefaultResult();
    }
}
