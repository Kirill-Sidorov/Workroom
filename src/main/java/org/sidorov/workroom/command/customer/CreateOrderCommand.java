package org.sidorov.workroom.command.customer;

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

public class CreateOrderCommand implements ActionCommand {

    private final Result errorOrderCreationResult = new ForwardResult(AppManager.getProperty("page.customerOrderCreation"));

    @Override
    public Result execute(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        if (UserAccessHelper.checkUser(sessionUser, UserType.CUSTOMER)) {

            Order order = new Order();
            order.setCustomerId(sessionUser.getId());
            order.setThingName(request.getParameter("thingName"));
            order.setDescription(request.getParameter("description"));
            order.setOrderStatusValue(OrderStatus.CHECK_BY_MODERATOR);

            if (!checkTextFields(order)) {
                request.setAttribute("errorOrderCreationMessage", AppManager.getProperty("message.allFieldsNeedFilled"));
                return errorOrderCreationResult;
            }

            OrderDao orderDao = (OrderDao) request.getServletContext().getAttribute("OrderDao");
            orderDao.createOrder(order);
        }
        return sessionUser.getUserType().getDefaultResult();
    }

    private boolean checkTextFields(Order order) {
        return order.getThingName().length() != 0 && order.getDescription().length() != 0;
    }
}
