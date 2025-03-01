package org.sidorov.workroom.command.storekeeper;

import org.sidorov.workroom.command.ActionCommand;
import org.sidorov.workroom.command.result.Result;
import org.sidorov.workroom.datalayer.OrderReplacementPart;
import org.sidorov.workroom.datalayer.User;
import org.sidorov.workroom.datalayer.data.OrderDao;
import org.sidorov.workroom.datalayer.data.OrderReplacementPartDao;
import org.sidorov.workroom.datalayer.data.ReplacementPartDao;
import org.sidorov.workroom.helper.UserAccessHelper;
import org.sidorov.workroom.logic.OrderStatus;
import org.sidorov.workroom.logic.UserType;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ConfirmReplacementPartsCommand implements ActionCommand {

    @Override
    public Result execute(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        if (UserAccessHelper.checkUser(sessionUser, UserType.STOREKEEPER)) {
            OrderReplacementPartDao orderReplacementPartDao = (OrderReplacementPartDao) request.getServletContext()
                    .getAttribute("OrderReplacementPartDao");
            ReplacementPartDao replacementPartDao = (ReplacementPartDao) request.getServletContext().getAttribute("ReplacementPartDao");

            int orderId = Integer.parseInt(request.getParameter("orderId"));
            List<OrderReplacementPart> orderParts = orderReplacementPartDao.getOrderPartsWithNumberPartsInStock(orderId);
            for (OrderReplacementPart part : orderParts) {
                int numberParts = part.getInStock() - part.getNumberParts();
                replacementPartDao.updateInStock(part.getPartId(), numberParts);
            }
            OrderDao orderDao = (OrderDao) request.getServletContext().getAttribute("OrderDao");
            orderDao.setOrderStatus(OrderStatus.REPAIR_PROCESS, orderId);
        }
        return sessionUser.getUserType().getDefaultResult();
    }
}
