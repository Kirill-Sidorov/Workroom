package org.sidorov.workroom.command.main;

import org.sidorov.workroom.command.ActionCommand;
import org.sidorov.workroom.command.result.ForwardResult;
import org.sidorov.workroom.command.result.Result;
import org.sidorov.workroom.datalayer.Order;
import org.sidorov.workroom.datalayer.User;
import org.sidorov.workroom.datalayer.data.OrderDao;
import org.sidorov.workroom.helper.UserAccessHelper;
import org.sidorov.workroom.logic.UserType;
import org.sidorov.workroom.resource.AppManager;

import javax.servlet.http.HttpServletRequest;

public class ShowOrderEditorPageCommand implements ActionCommand {

    private final Result result = new ForwardResult(AppManager.getProperty("page.orderEditor"));

    @Override
    public Result execute(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        if (UserAccessHelper.checkUser(sessionUser, UserType.CUSTOMER, UserType.MODERATOR)) {
            int id = Integer.parseInt(request.getParameter("id"));
            OrderDao orderDao = (OrderDao) request.getServletContext().getAttribute("OrderDao");
            Order order = orderDao.getOrderById(id);
            if (order != null) {
                request.setAttribute("order", order);
            }
            return result;
        }
        return sessionUser.getUserType().getDefaultResult();
    }
}
