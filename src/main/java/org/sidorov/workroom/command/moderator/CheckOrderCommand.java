package org.sidorov.workroom.command.moderator;

import org.sidorov.workroom.command.ActionCommand;
import org.sidorov.workroom.command.result.Result;
import org.sidorov.workroom.datalayer.User;
import org.sidorov.workroom.datalayer.data.OrderDao;
import org.sidorov.workroom.helper.UserAccessHelper;
import org.sidorov.workroom.logic.OrderStatus;
import org.sidorov.workroom.logic.UserType;

import javax.servlet.http.HttpServletRequest;

public class CheckOrderCommand implements ActionCommand {

    @Override
    public Result execute(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        if (UserAccessHelper.checkUser(sessionUser, UserType.MODERATOR)) {
            int id = Integer.parseInt(request.getParameter("id"));
            OrderDao orderDao = (OrderDao) request.getServletContext().getAttribute("OrderDao");
            orderDao.setOrderStatus(OrderStatus.WAIT_MASTER, id);
        }
        return sessionUser.getUserType().getDefaultResult();
    }
}
