package org.sidorov.workroom.command.customer;

import org.sidorov.workroom.command.ActionCommand;
import org.sidorov.workroom.command.result.ForwardResult;
import org.sidorov.workroom.command.result.Result;
import org.sidorov.workroom.datalayer.User;
import org.sidorov.workroom.helper.UserAccessHelper;
import org.sidorov.workroom.logic.UserType;
import org.sidorov.workroom.resource.AppManager;

import javax.servlet.http.HttpServletRequest;

public class ShowOrderCreationPageCommand implements ActionCommand {

    private final Result result = new ForwardResult(AppManager.getProperty("page.customerOrderCreation"));

    @Override
    public Result execute(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        if (UserAccessHelper.checkUser(sessionUser, UserType.CUSTOMER)) {
            return result;
        }
        return sessionUser.getUserType().getDefaultResult();
    }
}
