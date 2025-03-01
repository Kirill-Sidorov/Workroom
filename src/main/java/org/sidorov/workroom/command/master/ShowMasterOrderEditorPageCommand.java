package org.sidorov.workroom.command.master;

import org.sidorov.workroom.command.ActionCommand;
import org.sidorov.workroom.command.result.ForwardResult;
import org.sidorov.workroom.command.result.Result;
import org.sidorov.workroom.datalayer.Order;
import org.sidorov.workroom.datalayer.User;
import org.sidorov.workroom.datalayer.data.OrderDao;
import org.sidorov.workroom.datalayer.data.OrderReplacementPartDao;
import org.sidorov.workroom.helper.UserAccessHelper;
import org.sidorov.workroom.logic.UserType;
import org.sidorov.workroom.resource.AppManager;

import javax.servlet.http.HttpServletRequest;

public class ShowMasterOrderEditorPageCommand implements ActionCommand {

    private final Result result = new ForwardResult(AppManager.getProperty("page.masterOrderEditor"));

    @Override
    public Result execute(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        if (UserAccessHelper.checkUser(sessionUser, UserType.MASTER)) {
            int orderId = Integer.parseInt(request.getParameter("id"));
            OrderDao orderDao = (OrderDao) request.getServletContext().getAttribute("OrderDao");
            Order order = orderDao.getOrderById(orderId);
            if (order != null) {
                request.setAttribute("order", order);
                OrderReplacementPartDao orderReplacementPartDao = (OrderReplacementPartDao) request.getServletContext()
                        .getAttribute("OrderReplacementPartDao");
                request.setAttribute("replacementParts", orderReplacementPartDao.getOrderPartsWithAllParts(orderId));
            }
            return result;
        }
        return sessionUser.getUserType().getDefaultResult();
    }
}
