package org.sidorov.workroom.command.admin;

import org.sidorov.workroom.command.ActionCommand;
import org.sidorov.workroom.command.result.Result;
import org.sidorov.workroom.datalayer.User;
import org.sidorov.workroom.datalayer.data.UserDao;
import org.sidorov.workroom.helper.UserAccessHelper;
import org.sidorov.workroom.logic.UserType;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements ActionCommand {

    @Override
    public Result execute(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        if (UserAccessHelper.checkUser(sessionUser, UserType.ADMIN)) {
            int id = Integer.parseInt(request.getParameter("id"));
            UserDao userDao = (UserDao) request.getServletContext().getAttribute("UserDao");
            User userForDelete = userDao.getUserById(id);
            if ("locked".equals(userForDelete.getStatus())) {
                userDao.deleteUserById(id);
            }
        }
        return sessionUser.getUserType().getDefaultResult();
    }
}
