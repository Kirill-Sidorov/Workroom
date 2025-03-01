package org.sidorov.workroom.command.customer;

import org.sidorov.workroom.command.ActionCommand;
import org.sidorov.workroom.command.result.ForwardResult;
import org.sidorov.workroom.command.result.Result;
import org.sidorov.workroom.datalayer.Order;
import org.sidorov.workroom.datalayer.OrderReplacementPart;
import org.sidorov.workroom.datalayer.User;
import org.sidorov.workroom.datalayer.data.OrderDao;
import org.sidorov.workroom.datalayer.data.OrderReplacementPartDao;
import org.sidorov.workroom.helper.UserAccessHelper;
import org.sidorov.workroom.logic.UserType;
import org.sidorov.workroom.resource.AppManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowFullCostPageCommand implements ActionCommand {

    private final Result result = new ForwardResult(AppManager.getProperty("page.customerFullCost"));

    @Override
    public Result execute(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        if (UserAccessHelper.checkUser(sessionUser, UserType.CUSTOMER)) {
            int orderId = Integer.parseInt(request.getParameter("id"));
            OrderDao orderDao = (OrderDao) request.getServletContext().getAttribute("OrderDao");
            Order order = orderDao.getOrderById(orderId);
            OrderReplacementPartDao orderReplacementPartDao = (OrderReplacementPartDao) request.getServletContext()
                    .getAttribute("OrderReplacementPartDao");
            List<OrderReplacementPart> orderParts = orderReplacementPartDao.getOrderPartsList(orderId);
            int fullCost = order.getCostWork();
            for (OrderReplacementPart part : orderParts) {
                fullCost += part.getNumberParts() * part.getCost();
            }
            request.setAttribute("order", order);
            request.setAttribute("parts", orderParts);
            request.setAttribute("fullCost", fullCost);
            return result;
        }
        return sessionUser.getUserType().getDefaultResult();
    }
}
