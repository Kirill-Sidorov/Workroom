package org.sidorov.workroom.command.main;

import org.mindrot.jbcrypt.BCrypt;
import org.sidorov.workroom.command.ActionCommand;
import org.sidorov.workroom.command.result.RedirectResult;
import org.sidorov.workroom.command.result.Result;
import org.sidorov.workroom.datalayer.User;
import org.sidorov.workroom.datalayer.data.UserDao;
import org.sidorov.workroom.resource.AppManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand {

    private static final String ERROR_LOGIN_MASSAGE = "errorLoginMessage";
    private static final String LOGIN_INPUT = "loginInput";

    private final Result result = new RedirectResult("workroom?command=show_login_page");

    @Override
    public Result execute(HttpServletRequest request) {

        String loginInput = request.getParameter("login");
        String passwordInput = request.getParameter("password");

        UserDao userDao = (UserDao) request.getServletContext().getAttribute("UserDao");
        User user = userDao.getUserByLogin(loginInput);

        HttpSession session = request.getSession();
        session.removeAttribute(ERROR_LOGIN_MASSAGE);
        session.removeAttribute(LOGIN_INPUT);

        if (user == null || !BCrypt.checkpw(passwordInput, user.getPassword())) {
            session.setAttribute(ERROR_LOGIN_MASSAGE, AppManager.getProperty("message.loginError"));
            session.setAttribute(LOGIN_INPUT, loginInput);
            return result;
        }

        session.setAttribute("user", user);
        userDao.setAuthorized(true, user.getId());

        return user.getUserType().getDefaultResult();
    }
}
