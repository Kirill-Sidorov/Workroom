package org.sidorov.workroom.command.main;

import org.sidorov.workroom.command.ActionCommand;
import org.sidorov.workroom.command.result.Result;
import org.sidorov.workroom.datalayer.User;
import org.sidorov.workroom.datalayer.data.OrderDao;
import org.sidorov.workroom.helper.UserAccessHelper;
import org.sidorov.workroom.logic.UserType;

import javax.servlet.http.HttpServletRequest;

public class CancelOrderCommand implements ActionCommand {

    @Override
    public Result execute(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        if (UserAccessHelper.checkUser(sessionUser, UserType.CUSTOMER, UserType.MASTER)) {
            int id = Integer.parseInt(request.getParameter("id"));
            OrderDao orderDao = (OrderDao) request.getServletContext().getAttribute("OrderDao");
            orderDao.setDeleted(true, id);
        }
        return sessionUser.getUserType().getDefaultResult();
    }
}
