package org.sidorov.workroom.command.storekeeper;

import org.sidorov.workroom.command.ActionCommand;
import org.sidorov.workroom.command.result.Result;
import org.sidorov.workroom.datalayer.User;
import org.sidorov.workroom.datalayer.data.ReplacementPartDao;
import org.sidorov.workroom.helper.UserAccessHelper;
import org.sidorov.workroom.logic.UserType;

import javax.servlet.http.HttpServletRequest;

public class DeleteReplacementPartCommand implements ActionCommand {

    @Override
    public Result execute(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        if (UserAccessHelper.checkUser(sessionUser, UserType.STOREKEEPER)) {
            int id = Integer.parseInt(request.getParameter("id"));
            ReplacementPartDao replacementPartDao = (ReplacementPartDao) request.getServletContext().getAttribute("ReplacementPartDao");
            replacementPartDao.deletePart(id);
        }
        return sessionUser.getUserType().getDefaultResult();
    }
}
