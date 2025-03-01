package org.sidorov.workroom.command.admin;

import org.sidorov.workroom.command.ActionCommand;
import org.sidorov.workroom.command.result.Result;
import org.sidorov.workroom.datalayer.User;
import org.sidorov.workroom.datalayer.data.UserDao;
import org.sidorov.workroom.helper.UserAccessHelper;
import org.sidorov.workroom.logic.UserType;

import javax.servlet.http.HttpServletRequest;

public class UpdateUserCommand implements ActionCommand {

    @Override
    public Result execute(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        if (UserAccessHelper.checkUser(sessionUser, UserType.ADMIN)) {

            User user = new User();
            user.setId(Integer.parseInt(request.getParameter("userId")));
            user.setFirstName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));
            user.setPhone(request.getParameter("phoneNumber"));
            user.setLogin(request.getParameter("login"));

            UserDao userDao = (UserDao) request.getServletContext().getAttribute("UserDao");
            userDao.updateUser(user);
        }
        return sessionUser.getUserType().getDefaultResult();
    }
}
