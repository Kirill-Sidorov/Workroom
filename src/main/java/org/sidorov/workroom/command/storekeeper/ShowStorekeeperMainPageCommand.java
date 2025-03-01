package org.sidorov.workroom.command.storekeeper;

import org.sidorov.workroom.command.ActionCommand;
import org.sidorov.workroom.command.result.ForwardResult;
import org.sidorov.workroom.command.result.Result;
import org.sidorov.workroom.datalayer.User;
import org.sidorov.workroom.datalayer.data.OrderDao;
import org.sidorov.workroom.datalayer.data.ReplacementPartDao;
import org.sidorov.workroom.helper.UserAccessHelper;
import org.sidorov.workroom.logic.OrderStatus;
import org.sidorov.workroom.logic.UserType;
import org.sidorov.workroom.resource.AppManager;

import javax.servlet.http.HttpServletRequest;

public class ShowStorekeeperMainPageCommand implements ActionCommand {

    private final Result result = new ForwardResult(AppManager.getProperty("page.storekeeperMain"));

    @Override
    public Result execute(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        if (UserAccessHelper.checkUser(sessionUser, false, UserType.STOREKEEPER)) {
            OrderDao orderDao = (OrderDao) request.getServletContext().getAttribute("OrderDao");
            ReplacementPartDao replacementPartDao = (ReplacementPartDao) request.getServletContext().getAttribute("ReplacementPartDao");
            request.setAttribute("replacementParts", replacementPartDao.getReplacementPartsList());
            request.setAttribute("orderWaitParts", orderDao.getListOrdersByStatus(OrderStatus.WAIT_REPLACEMENT_PARTS.getId()));
            return result;
        }
        return sessionUser.getUserType().getDefaultResult();
    }
}
