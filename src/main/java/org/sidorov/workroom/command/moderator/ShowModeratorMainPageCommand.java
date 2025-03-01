package org.sidorov.workroom.command.moderator;

import org.sidorov.workroom.command.ActionCommand;
import org.sidorov.workroom.command.result.ForwardResult;
import org.sidorov.workroom.command.result.Result;
import org.sidorov.workroom.datalayer.User;
import org.sidorov.workroom.datalayer.data.OrderDao;
import org.sidorov.workroom.datalayer.data.UserDao;
import org.sidorov.workroom.helper.UserAccessHelper;
import org.sidorov.workroom.logic.OrderStatus;
import org.sidorov.workroom.logic.UserType;
import org.sidorov.workroom.resource.AppManager;

import javax.servlet.http.HttpServletRequest;

public class ShowModeratorMainPageCommand implements ActionCommand {

    private final Result result = new ForwardResult(AppManager.getProperty("page.moderatorMain"));

    @Override
    public Result execute(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        if (UserAccessHelper.checkUser(sessionUser, UserType.MODERATOR)) {
            UserDao userDao = (UserDao) request.getServletContext().getAttribute("UserDao");
            request.setAttribute("unlockedUsers", userDao.getListUsers());

            OrderDao orderDao = (OrderDao) request.getServletContext().getAttribute("OrderDao");
            request.setAttribute("pendingOrders", orderDao.getListOrdersByStatus(OrderStatus.CHECK_BY_MODERATOR.getId()));
            request.setAttribute("canceledOrders", orderDao.getListOrdersByDeleted(true));

            return result;
        }
        return sessionUser.getUserType().getDefaultResult();
    }
}
