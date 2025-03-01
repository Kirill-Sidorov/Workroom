package org.sidorov.workroom.command.main;

import org.sidorov.workroom.command.ActionCommand;
import org.sidorov.workroom.command.result.ForwardResult;
import org.sidorov.workroom.command.result.Result;
import org.sidorov.workroom.datalayer.User;
import org.sidorov.workroom.datalayer.data.UserDao;
import org.sidorov.workroom.resource.AppManager;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {

    private final Result result = new ForwardResult(AppManager.getProperty("page.login"));

    @Override
    public Result execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            UserDao userDao = (UserDao) request.getServletContext().getAttribute("UserDao");
            userDao.setAuthorized(false, user.getId());
            request.getSession().invalidate();
        }
        return result;
    }
}
