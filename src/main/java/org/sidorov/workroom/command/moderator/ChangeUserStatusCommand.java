package org.sidorov.workroom.command.moderator;

import org.sidorov.workroom.command.ActionCommand;
import org.sidorov.workroom.command.result.Result;
import org.sidorov.workroom.datalayer.User;
import org.sidorov.workroom.datalayer.data.UserDao;
import org.sidorov.workroom.helper.UserAccessHelper;
import org.sidorov.workroom.logic.UserType;

import javax.servlet.http.HttpServletRequest;

public class ChangeUserStatusCommand implements ActionCommand {

    @Override
    public Result execute(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        if (UserAccessHelper.checkUser(sessionUser, UserType.MODERATOR)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String status = request.getParameter("status");
            if ("unlocked".equals(status)) {
                status = "locked";
            } else {
                status = "unlocked";
            }
            UserDao userDao = (UserDao) request.getServletContext().getAttribute("UserDao");
            userDao.setStatus(status, id);
        }
        return sessionUser.getUserType().getDefaultResult();
    }
}
